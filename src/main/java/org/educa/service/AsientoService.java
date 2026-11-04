package org.educa.service;

import org.educa.dao.AsientoDAO;
import org.educa.dao.AsientoDAOImpl;
import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;
import org.educa.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AsientoService {

    private static final BigDecimal SUPLEMENTO_TURISTA = new BigDecimal("0.00");
    private static final BigDecimal SUPLEMENTO_PREMIUM_ECONOMY = new BigDecimal("35.00");
    private static final BigDecimal SUPLEMENTO_BUSINESS = new BigDecimal("120.00");
    private static final BigDecimal SUPLEMENTO_FIRST = new BigDecimal("250.00");

    private final AsientoDAO asientoDAO = new AsientoDAOImpl();

    /**
     * Finds the free seats of a flight with their class supplement
     *
     * @param vuelo the {@link VueloEntity} whose free seats are searched
     * @return the {@link List} of {@link AsientoEntity} without a ticket for that flight
     * @throws SQLException if a database error occurs
     */
    public List<AsientoEntity> findDisponibles(VueloEntity vuelo) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            List<AsientoEntity> asientos = this.asientoDAO.findDisponibles(connection, vuelo);
            for (AsientoEntity asiento : asientos) {
                asiento.setSuplemento(calcularSuplemento(asiento.getClase()));
            }
            return asientos;
        }
    }

    private BigDecimal calcularSuplemento(String clase) {
        return switch (clase) {
            case "TURISTA" -> SUPLEMENTO_TURISTA;
            case "PREMIUM_ECONOMY" -> SUPLEMENTO_PREMIUM_ECONOMY;
            case "BUSINESS" -> SUPLEMENTO_BUSINESS;
            case "FIRST" -> SUPLEMENTO_FIRST;
            default -> throw new IllegalStateException("Clase de asiento desconocida: " + clase);
        };
    }
}