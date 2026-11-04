package org.educa.dao;

import org.educa.entity.AsientoEntity;
import org.educa.entity.VueloEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsientoDAOImpl implements AsientoDAO {

    private static final String FIND_DISPONIBLES =
            "SELECT a.id_asiento, a.id_avion, a.cod_asiento, a.clase "
                    + "FROM asientos a "
                    + "LEFT JOIN billetes b ON b.id_asiento = a.id_asiento AND b.id_vuelo = ? "
                    + "WHERE a.id_avion = ? AND b.id_billete IS NULL "
                    + "ORDER BY a.cod_asiento";

    @Override
    public List<AsientoEntity> findDisponibles(Connection connection, VueloEntity vuelo) throws SQLException {
        List<AsientoEntity> asientos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_DISPONIBLES)) {
            ps.setInt(1, vuelo.getIdVuelo());
            ps.setInt(2, vuelo.getIdAvion());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    asientos.add(mapAsiento(rs));
                }
            }
            return asientos;
        }
    }

    private AsientoEntity mapAsiento(ResultSet rs) throws SQLException {
        return new AsientoEntity(
                rs.getInt("id_asiento"),
                rs.getInt("id_avion"),
                rs.getString("cod_asiento"),
                rs.getString("clase"),
                null
        );
    }
}
