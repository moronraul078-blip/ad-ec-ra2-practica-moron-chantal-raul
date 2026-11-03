package org.educa.dao;

import org.educa.entity.AeropuertoEntity;
import org.educa.entity.VueloEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloDAOImpl implements VueloDAO {

    private static final String FIND_BY_ORIGEN =
            "SELECT v.id_vuelo, v.num_vuelo, v.id_avion, v.fec_salida, v.fec_llegada, v.estado, "
                    + "d.id_aeropuerto, d.cod_iata, d.nombre, d.ciudad, d.pais "
                    + "FROM vuelos v "
                    + "JOIN aeropuertos d ON d.id_aeropuerto = v.id_destino "
                    + "WHERE v.id_origen = ? AND v.estado <> 'CANCELADO' "
                    + "ORDER BY v.fec_salida";

    @Override
    public List<VueloEntity> findByOrigen(Connection connection, AeropuertoEntity origen) throws SQLException {
        List<VueloEntity> vuelos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_ORIGEN)) {
            ps.setInt(1, origen.getIdAeropuerto());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vuelos.add(mapVuelo(rs, origen));
                }
            }
            return vuelos;
        }
    }

    private VueloEntity mapVuelo(ResultSet rs, AeropuertoEntity origen) throws SQLException {
        return new VueloEntity(
                rs.getInt("id_vuelo"),
                rs.getString("num_vuelo"),
                origen,
                mapDestino(rs),
                rs.getInt("id_avion"),
                rs.getTimestamp("fec_salida").toLocalDateTime(),
                rs.getTimestamp("fec_llegada").toLocalDateTime(),
                rs.getString("estado")
        );
    }

    private AeropuertoEntity mapDestino(ResultSet rs) throws SQLException {
        return new AeropuertoEntity(
                rs.getInt("id_aeropuerto"),
                rs.getString("cod_iata"),
                rs.getString("nombre"),
                rs.getString("ciudad"),
                rs.getString("pais")
        );
    }
}
