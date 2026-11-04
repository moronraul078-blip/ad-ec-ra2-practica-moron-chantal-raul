package org.educa.service;

import org.educa.dao.VueloDAO;
import org.educa.dao.VueloDAOImpl;
import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;
import org.educa.pool.ConnectionPool;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

public class VueloService {

    private static final BigDecimal PRECIO_POR_MINUTO = new BigDecimal("0.75");
    private final VueloDAO vueloDAO = new VueloDAOImpl();

    /**
     * Finds the non cancelled flights departing from an airport
     *
     * @param origen the {@link AeropuertoEntity} the flights depart from
     * @return the {@link List} of {@link VueloEntity} found
     * @throws SQLException if a database error occurs
     */
    public List<VueloEntity> findByOrigen(AeropuertoEntity origen) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            return this.vueloDAO.findByOrigen(connection, origen);
        }
    }

    /**
     * Calculates the base price of a flight according to its duration
     *
     * @param vuelo the {@link VueloEntity} whose price is calculated
     * @return the {@link BigDecimal} base price rounded to two decimals
     */
    public BigDecimal getPrecioBase(VueloEntity vuelo) {
        return new BigDecimal(Duration.between(vuelo.getFecSalida(), vuelo.getFecLlegada()).toMinutes())
                .multiply(PRECIO_POR_MINUTO)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
