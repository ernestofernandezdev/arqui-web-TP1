package org.example;

import org.example.dao.DAOFactory.DAOFactory;
import org.example.dao.cliente.ClienteDAO;
import org.example.dao.entidades.Cliente;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.DERBY_DRIVER);
        if (daoFactory == null) {
            System.out.println("No se puede crear el DAOFactory");
            return;
        }
        ClienteDAO clienteDAO = daoFactory.getClienteDAO();
        List<Cliente> clientes = clienteDAO.listarPorMasFacturados();
        clientes.forEach(System.out::println);
    }
}