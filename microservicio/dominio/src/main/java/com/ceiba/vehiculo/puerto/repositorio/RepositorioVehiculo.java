package com.ceiba.vehiculo.puerto.repositorio;

import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

public interface RepositorioVehiculo {

    /**
     * Permite al usuario crear un nuevo vehiculo
     * @param vehiculo
     * @return El id del vehiculo creado
     */
    Long crearVehiculo(Vehiculo vehiculo);

    /**
     * Permite al usuario actualizar un vehiculo ya creado
     * @param vehiculo
     */
    void actualizarVehiculo(Vehiculo vehiculo);

    /**
     * Permite al usuario eliminar un vehiculo ya creado
     * @param idVehiculo
     */
    void eliminarVehiculo(Long idVehiculo);

    /**
     * Permite al usuario verificar si hay un vehiculo con la placa ingresada
     * @param placa
     * @return true en caso de que exista, de lo contrario false
     */
    boolean existeVehiculoConPlaca(String placa);

    /**
     * Permite al usuario verificar si hay un vehiculo con el id ingresado
     * @param idVehiculo
     * @return true en caso de que exista, de lo contrario false
     */
    boolean existeVehiculoConId(Long idVehiculo);

    /**
     * Permite al usuario verificar si hay un vehiculo en un lugar con el id ingresado
     * @param idLugar
     * @return true en caso de que exista, de lo contrario false
     */
    boolean existeVehiculoEnUnLugarConId(Long idLugar);
}
