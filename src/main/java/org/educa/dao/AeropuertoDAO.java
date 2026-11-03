package org.educa.dao;

import org.educa.entity.AeropuertoEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AeropuertoDAO {

    /**
     * Finds all the airports ordered by id
     *
     * @param connection the {@link Connection} to the database
     * @return the {@link List} of {@link AeropuertoEntity} found
     * @throws SQLException if a database error occurs
     */
    List<AeropuertoEntity> findAll(Connection connection) throws SQLException;
}
