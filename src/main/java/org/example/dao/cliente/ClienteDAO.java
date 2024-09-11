package org.example.dao.cliente;


import org.example.dto.ClienteDTO;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
    void agregar(int idCliente, String nombre, String email) throws SQLException;
    List<ClienteDTO> listarPorMasFacturados() throws SQLException;
}
