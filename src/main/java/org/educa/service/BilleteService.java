package org.educa.service;

import org.educa.dao.BilleteDAO;
import org.educa.dao.BilleteDAOImpl;
import org.educa.entity.BilleteEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class BilleteService {

    private static final int EDAD_LIMITE_INFANTIL = 12;
    private static final int UMBRAL_RESERVAS_DESCUENTO = 3;
    private static final BigDecimal SIN_DESCUENTO = new BigDecimal("1.00");
    private static final BigDecimal FACTOR_INFANTIL = new BigDecimal("0.80");
    private static final BigDecimal FACTOR_RESERVAS = new BigDecimal("0.90");

    private final VueloService vueloService = new VueloService();
    private final BilleteDAO billeteDAO = new BilleteDAOImpl();

    /**
     * Calculates the final price of a ticket applying the child and frequent client discounts
     *
     * @param billete     the {@link BilleteEntity} whose price is calculated and set
     * @param numReservas the number of previous bookings of the client
     */
    public void calculatePrecio(BilleteEntity billete, int numReservas) {
        BigDecimal precioBase = this.vueloService.getPrecioBase(billete.getVuelo());
        BigDecimal precioConSuplemento = precioBase.add(billete.getAsiento().getSuplemento());
        BigDecimal precioConDescuentoEdad = precioConSuplemento.multiply(
                descuentoEdad(billete.getPasajero().getFecNac(), billete.getVuelo().getFecSalida()));
        BigDecimal precioFinal = precioConDescuentoEdad.multiply(descuentoReservas(numReservas));
        billete.setPrecio(precioFinal.setScale(2, RoundingMode.HALF_UP));
    }

    private BigDecimal descuentoEdad(LocalDate fecNac, LocalDateTime fecSalida) {
        BigDecimal descuento = SIN_DESCUENTO;
        if (Period.between(fecNac, fecSalida.toLocalDate()).getYears() < EDAD_LIMITE_INFANTIL) {
            descuento = FACTOR_INFANTIL;
        }
        return descuento;
    }

    private BigDecimal descuentoReservas(int numReservas) {
        BigDecimal descuento = SIN_DESCUENTO;
        if (numReservas > UMBRAL_RESERVAS_DESCUENTO) {
            descuento = FACTOR_RESERVAS;
        }
        return descuento;
    }

    /**
     * Saves a ticket using the connection of the current transaction
     *
     * @param connection the {@link Connection} of the current transaction
     * @param billete    the {@link BilleteEntity} to save
     * @throws SQLException if a database error occurs
     */
    public void save(Connection connection, BilleteEntity billete) throws SQLException {
        this.billeteDAO.save(connection, billete);
    }
}
