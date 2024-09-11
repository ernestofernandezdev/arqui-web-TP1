package org.example;

import org.example.dao.DAOFactory.DAOFactory;
import org.example.dao.cliente.ClienteDAO;
import org.example.dao.producto.ProductoDAO;
import org.example.dto.ClienteDTO;
import org.example.dto.ProductoDTO;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.MYSQL_DRIVER);
        ClienteDAO clienteDAO = daoFactory.getClienteDAO();
        ProductoDAO productoDAO = daoFactory.getProductoDAO();

        ProductoDTO producto = productoDAO.obtenerMasRecaudador();
        System.out.println(producto);
        List<ClienteDTO> clientes = clienteDAO.listarPorMasFacturados();
        clientes.forEach(System.out::println);
    }
}