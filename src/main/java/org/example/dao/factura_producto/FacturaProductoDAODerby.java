package org.example.dao.factura_producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FacturaProductoDAODerby implements FacturaProductoDAO {
    private Connection connection;

    public FacturaProductoDAODerby(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregar(int idFactura, int idProducto, int cantidad) throws SQLException {
        String crearClienteQuery = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";

        PreparedStatement ps = this.connection.prepareStatement(crearClienteQuery);
        ps.setInt(1, idFactura);
        ps.setInt(2, idProducto);
        ps.setInt(3, cantidad);
        ps.executeUpdate();
        this.connection.commit();
        ps.close();
    }
}
