package org.educa.dao;

import org.educa.entity.BilleteEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BilleteDAOImpl implements BilleteDAO {

    private static final String SAVE =
            "INSERT INTO billetes (id_pasajero, id_vuelo, id_asiento, precio) "
                    + "VALUES (?, ?, ?, ?)";

    private static final String[] GENERATED_ID = {"id_billete"};

    @Override
    public void save(Connection connection, BilleteEntity billete) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SAVE, GENERATED_ID)) {
            ps.setInt(1, billete.getPasajero().getIdPasajero());
            ps.setInt(2, billete.getVuelo().getIdVuelo());
            ps.setInt(3, billete.getAsiento().getIdAsiento());
            ps.setBigDecimal(4, billete.getPrecio());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    billete.setIdBillete(keys.getInt(1));
                }
            }
        }
    }
}
