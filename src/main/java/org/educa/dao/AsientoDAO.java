package org.educa.dao;

import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AsientoDAO {

    /**
     * Finds the seats of the flight aircraft that have no ticket for that flight
     *
     * @param connection the {@link Connection} to the database
     * @param vuelo      the {@link VueloEntity} whose free seats are searched
     * @return the {@link List} of {@link AsientoEntity} found
     * @throws SQLException if a database error occurs
     */
    List<AsientoEntity> findDisponibles(Connection connection, VueloEntity vuelo) throws SQLException;
}
