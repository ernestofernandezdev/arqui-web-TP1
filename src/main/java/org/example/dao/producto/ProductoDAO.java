package org.example.dao.producto;

import org.example.dto.ProductoDTO;

import java.sql.SQLException;

public interface ProductoDAO {
    void agregar(int idProducto, String nombre, double valor) throws SQLException;
    ProductoDTO obtenerMasRecaudador() throws SQLException;
}
