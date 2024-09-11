package org.example.dao.producto;

import org.example.dao.entidades.Producto;
import org.example.dto.ProductoDTO;

import java.sql.*;

public class ProductoDAOMysql implements ProductoDAO{
    private Connection connection;

    public ProductoDAOMysql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregar(int idProducto, String nombre, double valor) throws SQLException {
        String crearProductoQuery = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";

        PreparedStatement ps = this.connection.prepareStatement(crearProductoQuery);
        ps.setInt(1, idProducto);
        ps.setString(2, nombre);
        ps.setDouble(3, valor);
        ps.executeUpdate();
        this.connection.commit();
        ps.close();
    }

    @Override
    public ProductoDTO obtenerMasRecaudador() throws SQLException {
        String obtenerMasRecaudadorQuery =
                "select p.*, sum(fp.cantidad*p.valor) as recaudado from producto p " +
                        "inner join factura_producto fp " +
                        "on fp.idProducto = p.idProducto " +
                        "group by fp.idProducto " +
                        "order by sum(fp.cantidad*p.valor) DESC " +
                        "limit 3";

        ResultSet rs = this.connection.prepareStatement(obtenerMasRecaudadorQuery).executeQuery();
        ProductoDTO producto = null;
        if (rs.next()) {
            producto = new ProductoDTO(
                    rs.getString("nombre"),
                    rs.getFloat("valor"),
                    rs.getFloat("recaudado"));
        }
        this.connection.commit();
        return producto;
    }
}
