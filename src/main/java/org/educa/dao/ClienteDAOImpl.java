package org.educa.dao;

import org.educa.entity.ClienteEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl implements ClienteDAO {

    private static final String FIND_BY_DNI =
            "SELECT id_cliente, dni, nombre, p_apellido, s_apellido, email, telefono "
                    + "FROM clientes WHERE dni = ?";

    private static final String SAVE_CLIENTE =
            "INSERT INTO clientes (dni, nombre, p_apellido, s_apellido, email, telefono) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String[] GENERATED_ID = {"id_cliente"};

    @Override
    public ClienteEntity findByDNI(Connection connection, String dni) throws SQLException {
        ClienteEntity cliente = null;
        try (PreparedStatement ps = connection.prepareStatement(FIND_BY_DNI)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = mapCliente(rs);
                }
                return cliente;
            }
        }
    }

    @Override
    public void saveCliente(Connection connection, ClienteEntity cliente) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SAVE_CLIENTE, GENERATED_ID)) {
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getPrimerApellido());
            ps.setString(4, cliente.getSegundoApellido());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getTelefono());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    cliente.setIdCliente(keys.getInt(1));
                }
            }
        }
    }

    private ClienteEntity mapCliente(ResultSet rs) throws SQLException {
        return new ClienteEntity(
                rs.getInt("id_cliente"),
                rs.getString("dni"),
                rs.getString("nombre"),
                rs.getString("p_apellido"),
                rs.getString("s_apellido"),
                rs.getString("email"),
                rs.getString("telefono"));
    }
}
