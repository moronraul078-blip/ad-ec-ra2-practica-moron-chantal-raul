package org.educa.service;

import org.educa.dao.PasajeroDAO;
import org.educa.dao.PasajeroDAOImpl;
import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class PasajeroService {

    private final PasajeroDAO pasajeroDAO = new PasajeroDAOImpl();

    /**
     * Saves a passenger using the connection of the current transaction
     *
     * @param connection the {@link Connection} of the current transaction
     * @param pasajero   the {@link PasajeroEntity} to save
     * @throws SQLException if a database error occurs
     */
    public void save(Connection connection, PasajeroEntity pasajero) throws SQLException {
        this.pasajeroDAO.save(connection, pasajero);
    }
}
