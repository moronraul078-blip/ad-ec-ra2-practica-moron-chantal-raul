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
}
