package org.educa.service;

import org.educa.dao.VueloDAO;
import org.educa.dao.VueloDAOImpl;
import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;
import org.educa.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VueloService {

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
            return vueloDAO.findByOrigen(connection, origen);
        }
    }

    public BigDecimal getPrecioBase(VueloEntity vuelo) {
        return null;
    }
}
