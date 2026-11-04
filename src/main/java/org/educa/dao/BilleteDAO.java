package org.educa.dao;

import org.educa.entity.BilleteEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface BilleteDAO {

    /**
     * Saves a new ticket
     *
     * @param connection the {@link Connection} of the current transaction
     * @param billete    the {@link BilleteEntity} to save
     * @throws SQLException if a database error occurs
     */
    void save(Connection connection, BilleteEntity billete) throws SQLException;
}
