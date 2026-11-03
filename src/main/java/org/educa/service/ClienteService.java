package org.educa.service;

import org.educa.dao.ClienteDAO;
import org.educa.dao.ClienteDAOImpl;
import org.educa.entity.ClienteEntity;
import org.educa.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAOImpl();

    public ClienteEntity findByDNI(String dni) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            return this.clienteDAO.findByDNI(connection, dni);
        }
    }

    public void saveCliente(ClienteEntity cliente) throws SQLException {
    }
}
