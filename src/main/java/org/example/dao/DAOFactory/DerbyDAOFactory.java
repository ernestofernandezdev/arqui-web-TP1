package org.example.dao.DAOFactory;

import org.example.dao.cliente.ClienteDAO;
import org.example.dao.cliente.ClienteDAODerby;
import org.example.dao.factura.FacturaDAO;
import org.example.dao.factura.FacturaDAODerby;
import org.example.dao.factura_producto.FacturaProductoDAO;
import org.example.dao.factura_producto.FacturaProductoDAODerby;
import org.example.dao.producto.ProductoDAODerby;
import org.example.dao.producto.ProductoDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDAOFactory extends DAOFactory {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:esquemaDB;create=true";
    private Connection connection;

    public DerbyDAOFactory() {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            this.connection = DriverManager.getConnection(URL);
            this.connection.setAutoCommit(false);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAODerby(this.connection);
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new ProductoDAODerby(this.connection);
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAODerby(this.connection);
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() {
        return new FacturaProductoDAODerby(this.connection);
    }
}
