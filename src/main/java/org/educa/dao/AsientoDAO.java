package org.educa.dao;

import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AsientoDAO {
    List<AsientoEntity> findDisponibles(Connection connection, VueloEntity vuelo) throws SQLException;
}
