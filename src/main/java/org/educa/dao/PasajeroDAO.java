package org.educa.dao;

import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface PasajeroDAO {

    /**
     * Saves a new passenger and sets its generated id
     *
     * @param connection the {@link Connection} of the current transaction
     * @param pasajero   the {@link PasajeroEntity} to save
     * @throws SQLException if a database error occurs
     */
    void save(Connection connection, PasajeroEntity pasajero) throws SQLException;
}
