package org.example.esquemaDB;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreadorDeTablasMysql extends CreadorDeTablas {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    public CreadorDeTablasMysql() throws SQLException {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void crearTablas() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);

        String crearEsquema = "CREATE DATABASE IF NOT EXISTS integrador1";

        String usarEsquema = "USE integrador1";

        String tableCliente = "CREATE TABLE cliente (" +
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR(500), " +
                "email VARCHAR(150), " +
                "PRIMARY KEY (idCliente))";

        String tableProducto = "CREATE TABLE producto (" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(45), " +
                "valor FLOAT, " +
                "PRIMARY KEY (idProducto))";

        String tableFactura = "CREATE TABLE factura (" +
                "idFactura INT NOT NULL, " +
                "idCliente INT, " +
                "PRIMARY KEY (idFactura), " +
                "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente))";

        String tableFacturaProducto = "CREATE TABLE factura_producto (" +
                "idFactura INT, " +
                "idProducto INT, " +
                "cantidad INT, " +
                "PRIMARY KEY (idFactura, idProducto), " +
                "FOREIGN KEY (idFactura) REFERENCES factura(idFactura), " +
                "FOREIGN KEY (idProducto) REFERENCES producto(idProducto))";

        connection.prepareStatement(crearEsquema).execute();
        connection.prepareStatement(usarEsquema).execute();
        connection.prepareStatement(tableCliente).execute();
        connection.prepareStatement(tableProducto).execute();
        connection.prepareStatement(tableFactura).execute();
        connection.prepareStatement(tableFacturaProducto).execute();
        connection.commit();
        connection.close();
    }
}
