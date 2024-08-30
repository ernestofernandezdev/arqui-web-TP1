package org.example.dao.factura_producto;

import java.sql.SQLException;

public interface FacturaProductoDAO {
    void agregar(int idFactura, int idProducto, int cantidad) throws SQLException;
}
