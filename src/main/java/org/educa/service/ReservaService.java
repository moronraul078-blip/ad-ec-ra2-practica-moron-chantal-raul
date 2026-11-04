package org.educa.service;

import org.educa.dao.ReservaDAO;
import org.educa.dao.ReservaDAOImpl;
import org.educa.entity.BilleteEntity;
import org.educa.entity.ClienteEntity;
import org.educa.entity.ReservaEntity;
import org.educa.pool.ConnectionPool;
import org.educa.util.ReservaUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReservaService {

    private final ReservaDAO reservaDAO = new ReservaDAOImpl();
    private final PasajeroService pasajeroService = new PasajeroService();
    private final BilleteService billeteService = new BilleteService();

    /**
     * Finds all the bookings of a client
     *
     * @param cliente the {@link ClienteEntity} whose bookings are searched
     * @return the {@link List} of {@link ReservaEntity} found
     * @throws SQLException if a database error occurs
     */
    public List<ReservaEntity> findByCliente(ClienteEntity cliente) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            return this.reservaDAO.findByCliente(connection, cliente);
        }
    }

    /**
     * Saves a booking with its passengers and tickets in a single transaction
     *
     * @param reserva the {@link ReservaEntity} to save
     * @throws SQLException if a database error occurs, in which case nothing is saved
     */
    public void save(ReservaEntity reserva) throws SQLException {
        try (Connection connection = ConnectionPool.getDataSource().getConnection()) {
            connection.setAutoCommit(false);
            try {
                asignarCodigoUnico(connection, reserva);
                this.reservaDAO.save(connection, reserva);
                for (BilleteEntity billete : reserva.getBilletes()) {
                    billete.getPasajero().setIdReserva(reserva.getIdReserva());
                    this.pasajeroService.save(connection, billete.getPasajero());
                    this.billeteService.save(connection, billete);
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    private void asignarCodigoUnico(Connection connection, ReservaEntity reserva) throws SQLException {
        String codigo;
        do {
            codigo = ReservaUtils.generarCodigoReserva();
        } while (this.reservaDAO.existsByCodReserva(connection, codigo));
        reserva.setCodReserva(codigo);
    }
}
