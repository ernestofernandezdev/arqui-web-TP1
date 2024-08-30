package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.dao.DAOFactory.DAOFactory;
import org.example.dao.cliente.ClienteDAO;
import org.example.dao.factura.FacturaDAO;
import org.example.dao.factura_producto.FacturaProductoDAO;
import org.example.dao.producto.ProductoDAO;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class CargarDatos {
    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getDaoFactory(DAOFactory.DERBY_DRIVER);
        if (daoFactory == null) {
            System.out.println("No se puede crear el DAOFactory");
            return;
        }
        ClienteDAO clienteDAO = daoFactory.getClienteDAO();
        ProductoDAO productoDAO = daoFactory.getProductoDAO();
        FacturaDAO facturaDAO = daoFactory.getFacturaDAO();
        FacturaProductoDAO facturaProductoDAO = daoFactory.getFacturaProductoDAO();

        CSVParser parser;

        // Agregamos los datos a la tabla producto
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(
                    new FileReader("src/main/resources/mockData/productos.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(CSVRecord row: parser) {
            try {
                productoDAO.agregar(
                        Integer.parseInt(row.get("idProducto")),
                        row.get("nombre"),
                        Float.parseFloat(row.get("valor"))
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Agregamos los datos a la tabla cliente
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(
                    new FileReader("src/main/resources/mockData/clientes.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(CSVRecord row: parser) {
            try {
                clienteDAO.agregar(
                        Integer.parseInt(row.get("idCliente")),
                        row.get("nombre"),
                        row.get("email")
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Agregamos los datos a la tabla factura
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(
                    new FileReader("src/main/resources/mockData/facturas.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(CSVRecord row: parser) {
            try {
                facturaDAO.agregar(
                        Integer.parseInt(row.get("idFactura")),
                        Integer.parseInt(row.get("idCliente"))
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // Agregamos los datos a la tabla factura_producto
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(
                    new FileReader("src/main/resources/mockData/facturas_productos.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(CSVRecord row: parser) {
            try {
                facturaProductoDAO.agregar(
                        Integer.parseInt(row.get("idFactura")),
                        Integer.parseInt(row.get("idProducto")),
                        Integer.parseInt(row.get("cantidad"))
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
