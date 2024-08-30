package org.example.dao.DAOFactory;

import org.example.dao.cliente.ClienteDAO;
import org.example.dao.factura.FacturaDAO;
import org.example.dao.factura_producto.FacturaProductoDAO;
import org.example.dao.producto.ProductoDAO;

public abstract class DAOFactory {
    public static final int DERBY_DRIVER = 1;
    public static final int MYSQL_DRIVER = 2;

    public static DAOFactory getDaoFactory(int driver) {
        switch (driver) {
            case MYSQL_DRIVER: return new MysqlDAOFactory();
            case DERBY_DRIVER: return new DerbyDAOFactory();
            default: return null;
        }
    }

    public abstract ClienteDAO getClienteDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract FacturaProductoDAO getFacturaProductoDAO();

}
