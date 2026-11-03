package org.educa.dao;

import org.educa.entity.AeropuertoEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AeropuertoDAOImpl implements AeropuertoDAO {

    private static final String FIND_ALL = "SELECT id_aeropuerto, cod_iata, nombre, ciudad, pais "
            + "FROM aeropuertos";

    @Override
    public List<AeropuertoEntity> findAll(Connection connection) throws SQLException {
        List<AeropuertoEntity> aeropuertos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                aeropuertos.add(mapAeropuerto(rs));
            }
            return aeropuertos;
        }
    }

    private AeropuertoEntity mapAeropuerto(ResultSet rs) throws SQLException {
        return new AeropuertoEntity(
                rs.getInt("id_aeropuerto"),
                rs.getString("cod_iata"),
                rs.getString("nombre"),
                rs.getString("ciudad"),
                rs.getString("pais")
        );
    }
}
