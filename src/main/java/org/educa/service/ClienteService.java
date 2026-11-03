package org.educa.service;

import org.educa.dao.ClienteDAO;
import org.educa.dao.ClienteDAOImpl;
import org.educa.entity.ClienteEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAOImpl();

    /**
     * Finds a client by its DNI
     *
     * @param dni the {@link String} DNI of the client
     * @return the {@link ClienteEntity} found or null if it does not exist
     * @throws SQLException if a database error occurs
     */
    public ClienteEntity findByDNI(String dni) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            return this.clienteDAO.findByDNI(connection, dni);
        }
    }

    /**
     * Saves a new client and sets its generated id
     *
     * @param cliente the {@link ClienteEntity} to save
     * @throws SQLException if a database error occurs
     */
    public void saveCliente(ClienteEntity cliente) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            this.clienteDAO.saveCliente(connection, cliente);
        }
    }
}
