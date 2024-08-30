package org.example.dao.factura;

import java.sql.SQLException;

public interface FacturaDAO {
    void agregar(int idFactura, int idCliente) throws SQLException;
}
