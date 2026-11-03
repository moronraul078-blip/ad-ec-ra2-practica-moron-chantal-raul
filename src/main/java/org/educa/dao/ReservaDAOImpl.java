package org.educa.dao;

import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAOImpl implements ReservaDAO {

    private static final String FIND_BY_CLIENTE =
            "SELECT id_reserva, cod_reserva, fec_reserva, estado "
                    + "FROM reservas WHERE id_cliente = ? ORDER BY fec_reserva";

    @Override
    public List<ReservaEntity> findByCliente(Connection connection, ClienteEntity cliente) throws SQLException {
        List<ReservaEntity> reservas = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_CLIENTE)) {
            ps.setInt(1, cliente.getIdCliente());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reservas.add(mapReserva(rs, cliente));
                }
            }
            return reservas;
        }
    }

    private ReservaEntity mapReserva(ResultSet rs, ClienteEntity cliente) throws SQLException {
        ReservaEntity reserva = new ReservaEntity();
        reserva.setIdReserva(rs.getInt("id_reserva"));
        reserva.setCodReserva(rs.getString("cod_reserva"));
        reserva.setCliente(cliente);
        reserva.setFecReserva(rs.getTimestamp("fec_reserva").toLocalDateTime());
        reserva.setEstado(rs.getString("estado"));
        return reserva;
    }
}
