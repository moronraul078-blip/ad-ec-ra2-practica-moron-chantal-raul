package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReservaDAO {

    /**
     * Finds all the bookings of a client ordered by date
     *
     * @param connection the {@link Connection} to the database
     * @param cliente    the {@link ClienteEntity} whose bookings are searched
     * @return the {@link List} of {@link ReservaEntity} found
     * @throws SQLException if a database error occurs
     */
    List<ReservaEntity> findByCliente(Connection connection, ClienteEntity cliente) throws SQLException;

    /**
     * Saves a new booking and sets its generated id
     *
     * @param connection the {@link Connection} of the current transaction
     * @param reserva    the {@link ReservaEntity} to save
     * @throws SQLException if a database error occurs
     */
    void save(Connection connection, ReservaEntity reserva) throws SQLException;

    /**
     * Checks if a booking with the given code already exists
     *
     * @param connection the {@link Connection} to the database
     * @param codReserva the {@link String} booking code to check
     * @return true if the code is already used, false otherwise
     * @throws SQLException if a database error occurs
     */
    boolean existsByCodReserva(Connection connection, String codReserva) throws SQLException;
}
