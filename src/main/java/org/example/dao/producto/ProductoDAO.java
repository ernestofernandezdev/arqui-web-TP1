package org.example.dao.producto;

import org.example.dao.entidades.Producto;

import java.sql.SQLException;

public interface ProductoDAO {
    void agregar(int idProducto, String nombre, double valor) throws SQLException;
    Producto obtenerMasRecaudador() throws SQLException;
}
