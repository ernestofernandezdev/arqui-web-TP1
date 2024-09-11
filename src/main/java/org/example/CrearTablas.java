package org.example;

import org.example.esquemaDB.CreadorDeTablas;

import java.sql.SQLException;

public class CrearTablas {
    public static void main(String[] args) {
        try {
            CreadorDeTablas creador = CreadorDeTablas.getCreadorDeTablas(CreadorDeTablas.MYSQL_DRIVER);
            if (creador == null) {
                throw new SQLException("El tipo de BD no existe.");
            }
            creador.crearTablas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
