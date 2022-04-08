package com.ceiba.vehiculo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.lugar.puerto.repository.RepositorioLugar;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;

public class ServicioCrearVehiculo {

    private static final String EL_VEHICULO_YA_EXISTE_EN_EL_SISTEMA = "El vehiculo ya existe en el sistema";
    private static final String EL_ESPACIO_NO_EXISTE_EN_EL_SITEMA = "El lugar no existe en el sistema";
    private static final String EL_ESPACIO_YA_ESTA_OCUPADO = "El lugar ya esta ocupado";

    private final RepositorioVehiculo repositorioVehiculo;
    private final RepositorioLugar repositorioLugar;


    public ServicioCrearVehiculo(RepositorioVehiculo repositorioVehiculo,
                                 RepositorioLugar repositorioLugar) {
        this.repositorioVehiculo = repositorioVehiculo;
        this.repositorioLugar = repositorioLugar;
    }

    public Long ejecutar(Vehiculo vehiculo) {
        validarExistenciaPrevia(vehiculo);
        validarExistenciaPreviaLugar(vehiculo.getLugarVehiculo());
        validarExistenciaPreviaIdLugar(vehiculo.getLugarVehiculo());
        return this.repositorioVehiculo.crearVehiculo(vehiculo);
    }

    private void validarExistenciaPrevia(Vehiculo vehiculo) {
        boolean existe = this.repositorioVehiculo.existeVehiculoConPlaca(vehiculo.getPlaca());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_VEHICULO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
    private void validarExistenciaPreviaLugar(Long id){
        boolean existe = this.repositorioLugar.existeLugarPorId(id);
        if(!existe){
            throw new ExcepcionSinDatos(EL_ESPACIO_NO_EXISTE_EN_EL_SITEMA);
        }
    }

    private void validarExistenciaPreviaIdLugar(Long id){
        boolean existe = this.repositorioVehiculo.existeVehiculoEnUnLugarConId(id);
        if(existe){
            throw new ExcepcionDuplicidad(EL_ESPACIO_YA_ESTA_OCUPADO);
        }
    }
}
