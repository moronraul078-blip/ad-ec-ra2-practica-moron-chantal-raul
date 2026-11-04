package org.educa.dao;

import org.educa.entity.PasajeroEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasajeroDAOImpl implements PasajeroDAO {

    private static final String SAVE =
            "INSERT INTO pasajeros (id_reserva, dni, nombre, p_apellido, s_apellido, email, telefono, fec_nac) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String[] GENERATED_ID = {"id_pasajero"};

    @Override
    public void save(Connection connection, PasajeroEntity pasajero) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SAVE, GENERATED_ID)) {
            ps.setInt(1, pasajero.getIdReserva());
            ps.setString(2, pasajero.getDni());
            ps.setString(3, pasajero.getNombre());
            ps.setString(4, pasajero.getPrimerApellido());
            ps.setString(5, pasajero.getSegundoApellido());
            ps.setString(6, pasajero.getEmail());
            ps.setString(7, pasajero.getTelefono());
            ps.setObject(8, pasajero.getFecNac());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    pasajero.setIdPasajero(keys.getInt(1));
                }
            }
        }
    }
}
