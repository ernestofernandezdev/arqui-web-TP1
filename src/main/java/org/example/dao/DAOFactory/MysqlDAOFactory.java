package org.example.dao.DAOFactory;

import org.example.dao.cliente.ClienteDAO;
import org.example.dao.cliente.ClienteDAOMysql;
import org.example.dao.factura.FacturaDAO;
import org.example.dao.factura.FacturaDAOMysql;
import org.example.dao.factura_producto.FacturaProductoDAO;
import org.example.dao.factura_producto.FacturaProductoDAOMysql;
import org.example.dao.producto.ProductoDAO;
import org.example.dao.producto.ProductoDAOMysql;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDAOFactory extends DAOFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/esquemaDB";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";
    private Connection connection;

    public MysqlDAOFactory() {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            this.connection.setAutoCommit(false);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ClienteDAO getClienteDAO() {
        return new ClienteDAOMysql(this.connection);
    }

    @Override
    public ProductoDAO getProductoDAO() {
        return new ProductoDAOMysql(this.connection);
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAOMysql(this.connection);
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() {
        return new FacturaProductoDAOMysql(this.connection);
    }
}
