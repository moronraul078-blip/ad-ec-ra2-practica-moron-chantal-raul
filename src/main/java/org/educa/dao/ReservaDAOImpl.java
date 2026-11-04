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

    private static final String SAVE =
            "INSERT INTO reservas (cod_reserva, id_cliente) "
                    + "VALUES (?, ?)";

    private static final String EXISTS_BY_COD_RESERVA =
            "SELECT 1 FROM reservas WHERE cod_reserva = ?";

    private static final String[] GENERATED_COLUMNS = {"id_reserva", "fec_reserva", "estado"};

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

    @Override
    public void save(Connection connection, ReservaEntity reserva) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SAVE, GENERATED_COLUMNS)) {
            ps.setString(1, reserva.getCodReserva());
            ps.setInt(2, reserva.getCliente().getIdCliente());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    reserva.setIdReserva(keys.getInt("id_reserva"));
                    reserva.setFecReserva(keys.getTimestamp("fec_reserva").toLocalDateTime());
                    reserva.setEstado(keys.getString("estado"));
                }
            }
        }
    }

    @Override
    public boolean existsByCodReserva(Connection connection, String codReserva) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(EXISTS_BY_COD_RESERVA)) {
            ps.setString(1, codReserva);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
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