package com.ceiba.vehiculo.puerto.dao;

import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;

import java.util.List;

public interface DaoVehiculo {

    /**
     * Permite al usuario conocer todos los vehiculos registrados
     * @return la lista de todos los vehiculos
     */
    List<DtoVehiculo> listarTodosLosVehiculos();

    /**
     * Permite al usuario buscar un vehiculo por id
     * @param idVehiculo
     * @return Un vehiculo en especifico(si existe alguno con el id)
     */
    DtoVehiculo buscarVehiculoPorId(Long idVehiculo);

    /**
     * Permite al usuario buscar todos los vehiculos relacionados con una placa
     * @param placa
     * @return Una lista de vehiculos relacionados con la placa ingresada
     */
    List<DtoVehiculo> listaVehiculoPorPlaca(String placa);

    /**
     * Permite buscar un vehiculo que ocupe un espacio en especifico
     * @param idEspacio
     * @return
     */
    DtoVehiculo buscarVehiculoEnUnEspacioEspecifico(Long idEspacio);
}
