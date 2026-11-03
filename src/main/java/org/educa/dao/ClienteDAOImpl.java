package org.educa.dao;

import org.educa.entity.ClienteEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl implements ClienteDAO {
    private static final String FIND_BY_DNI =
            "SELECT id_cliente, dni, nombre, primer_apellido, segundo_apellido, email, telefono "
                    + "FROM clientes WHERE dni = ?";
    @Override
    public ClienteEntity findByDNI(Connection connection, String dni) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement(FIND_BY_DNI)) {
            ps.setString(1, dni);
            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()){
                    return mapCliente(rs);
                }else {
                    return null;
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
