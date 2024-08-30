package org.example.dao.producto;

import org.example.dao.entidades.Producto;

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
    public Producto obtenerMasRecaudador() throws SQLException {
        String obtenerMasRecaudadorQuery =
                "select p.* from producto p " +
                        "where p.idProducto = (" +
                        "select fp.idProducto from factura_producto fp " +
                        "group by fp.idProducto " +
                        "order by sum(fp.cantidad*p.valor) DESC " +
                        "fetch first 1 rows only" +
                        ")";

        ResultSet rs = this.connection.prepareStatement(obtenerMasRecaudadorQuery).executeQuery();
        Producto producto = null;
        if (rs.next()) {
            producto = new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("nombre"),
                    rs.getFloat("valor"));
        }
        this.connection.commit();
        return producto;
    }
}
