package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VueloDAO {
    /**
     * Finds the non cancelled flights departing from an airport ordered by departure date
     *
     * @param connection the {@link Connection} to the database
     * @param origen     the {@link AeropuertoEntity} the flights depart from
     * @return the {@link List} of {@link VueloEntity} found
     * @throws SQLException if a database error occurs
     */
    List<VueloEntity> findByOrigen(Connection connection, AeropuertoEntity origen) throws SQLException;
}
