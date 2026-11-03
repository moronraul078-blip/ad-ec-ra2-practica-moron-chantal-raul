package org.educa.dao;

import org.educa.entity.ClienteEntity;

import java.sql.Connection;
import java.sql.SQLException;

public interface ClienteDAO {
    ClienteEntity findByDNI(Connection connection, String dni) throws SQLException;

    void saveCliente(Connection connection, ClienteEntity cliente) throws SQLException;
}
