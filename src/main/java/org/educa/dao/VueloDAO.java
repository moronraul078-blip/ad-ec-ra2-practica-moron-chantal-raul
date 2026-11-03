package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface VueloDAO {
    List<VueloEntity> findByOrigen(Connection connection, AeropuertoEntity origen) throws SQLException;
}
