package org.educa.dao;

import org.educa.entity.ClienteEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface ClienteDAO {

    /**
     * Finds a client by its DNI
     *
     * @param connection the {@link Connection} to the database
     * @param dni        the {@link String} DNI of the client
     * @return the {@link ClienteEntity} found or null if it does not exist
     * @throws SQLException if a database error occurs
     */
    ClienteEntity findByDNI(Connection connection, String dni) throws SQLException;

    /**
     * Saves a new client and sets its generated id
     *
     * @param connection the {@link Connection} to the database
     * @param cliente    the {@link ClienteEntity} to save
     * @throws SQLException if a database error occurs
     */
    void saveCliente(Connection connection, ClienteEntity cliente) throws SQLException;
}
