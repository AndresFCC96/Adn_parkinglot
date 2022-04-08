package com.ceiba.lugar.puerto.dao;

import com.ceiba.lugar.modelo.dto.DtoLugar;

import java.util.List;

public interface DaoLugar {

    /**
     * Permite listar todos los espacios por estado
     * @param estado
     * @return Una lista con todos los espacios que tengan dicho espacio.
     */
    List<DtoLugar> listarTodosLosLugaresPorEstado(String estado);

    /**
     * Permite listar todos los lugares
     * @return Una lista con todos los lugares esten o no ocupados.
     */
    List<DtoLugar> listarTodosLosLugares();

    /**
     * Permite buscar un lugar con el id ingresado
     * @param idLugar
     * @return Un lugar con el id ingresado si existe.
     */
    DtoLugar buscarEspacioPorId(Long idLugar);
}
