package org.educa.service;

import org.educa.dao.AeropuertoDAO;
import org.educa.dao.AeropuertoDAOImpl;
import org.educa.entity.AeropuertoEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AeropuertoService {

    private final AeropuertoDAO aeropuertoDAO = new AeropuertoDAOImpl();

    /**
     * Finds all the airports
     *
     * @return the {@link List} of {@link AeropuertoEntity} found
     * @throws SQLException if a database error occurs
     */
    public List<AeropuertoEntity> findAll() throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            return aeropuertoDAO.findAll(connection);
        }
    }
}
