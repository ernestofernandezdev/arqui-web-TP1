package org.example.dao.cliente;
import org.example.dao.entidades.Cliente;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClienteDAOMysql implements ClienteDAO{
    private Connection connection;

    public ClienteDAOMysql(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void agregar(int idCliente, String nombre, String email) throws SQLException {
        String crearClienteQuery = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";

        PreparedStatement ps = this.connection.prepareStatement(crearClienteQuery);
        ps.setInt(1, idCliente);
        ps.setString(2, nombre);
        ps.setString(3, email);
        ps.executeUpdate();
        this.connection.commit();
        ps.close();
    }

    @Override
    public List<Cliente> listarPorMasFacturados() throws SQLException {
        String listarClientesQuery = "select c.idCliente, c.nombre, c.email, sum(fp.cantidad*p.valor) from cliente c " +
                "inner join factura f on c.idCliente=f.idCliente " +
                "inner join factura_producto fp on f.idFactura=fp.idFactura " +
                "inner join producto p on p.idProducto=fp.idProducto " +
                "group by c.idCliente, c.nombre, c.email " +
                "order by sum(fp.cantidad*p.valor) DESC";

        ResultSet rs = this.connection.prepareStatement(listarClientesQuery).executeQuery();
        List<Cliente> clientes = new LinkedList<>();
        while (rs.next()) {
            clientes.add(new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("email")));
        }
        this.connection.commit();

        return clientes;
    }

}
