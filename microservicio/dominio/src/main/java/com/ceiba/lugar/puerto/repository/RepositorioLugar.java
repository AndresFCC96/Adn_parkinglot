package com.ceiba.lugar.puerto.repository;

import com.ceiba.lugar.modelo.entidad.Lugar;

public interface RepositorioLugar {

    /**
     * Permite crear un lugar
     * @param lugar
     * @return el id generado
     */
    Long crearLugar(Lugar lugar);

    /**
     * Permite actualizar un lugar
     * @param lugar
     */
    void actualizarLugar(Lugar lugar);

    /**
     * Permite actualizar el estado del lugar con el id
     * @param id
     * @param estado
     */
    void actualizarLugarEstado(Long id, String estado);

    /**
     * Permite eliminar un lugar
     * @param id
     */
    void eliminarLugar(Long id);

    /**
     * Permite validar si existe un lugar con un id
     * @param id
     * @return Un lugar si existe con el id
     */
    boolean existeLugarPorId(Long id);
}
