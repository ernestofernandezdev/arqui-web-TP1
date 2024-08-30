package org.example.dao.factura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaDAODerby implements FacturaDAO {
    private Connection connection;

    public FacturaDAODerby(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregar(int idFactura, int idCliente) throws SQLException {
        String crearFacturaQuery = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";

        PreparedStatement ps = this.connection.prepareStatement(crearFacturaQuery);
        ps.setInt(1, idFactura);
        ps.setInt(2, idCliente);
        ps.executeUpdate();
        this.connection.commit();
        ps.close();
    }
}
