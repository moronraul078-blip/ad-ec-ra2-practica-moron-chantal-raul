package org.educa.service;

import org.educa.dao.ReservaDAO;
import org.educa.dao.ReservaDAOImpl;
import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservaService {

    private final ReservaDAO reservaDAO = new ReservaDAOImpl();

    /**
     * Finds all the bookings of a client
     *
     * @param cliente the {@link ClienteEntity} whose bookings are searched
     * @return the {@link List} of {@link ReservaEntity} found
     * @throws SQLException if a database error occurs
     */
    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            return reservaDAO.findByCliente(connection, cliente);
        }
    }

    public void save(ReservaEntity reserva) throws SQLException {
    }
}
