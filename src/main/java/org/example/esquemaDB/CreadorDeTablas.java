package org.example.esquemaDB;

import java.sql.SQLException;

public abstract class CreadorDeTablas {

    public static final int DERBY_DRIVER = 1;
    public static final int MYSQL_DRIVER = 2;

    public static CreadorDeTablas getCreadorDeTablas(int tipo) throws SQLException {
        switch (tipo) {
            case DERBY_DRIVER: return new CreadorDeTablasDerby();
            case MYSQL_DRIVER: return new CreadorDeTablasMysql();
            default: return null;
        }
    }

    public abstract void crearTablas() throws SQLException;

}
