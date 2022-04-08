package com.ceiba.lugar.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.lugar.puerto.repository.RepositorioLugar;

public class ServicioActualizarEstadoLugar {

    private static final String EL_LUGAR_NO_EXISTE_EN_EL_SISTEMA = "El lugar no existe en el sistema";

    private final RepositorioLugar repositorioLugar;

    public ServicioActualizarEstadoLugar(RepositorioLugar repositorioLugar) {
        this.repositorioLugar = repositorioLugar;
    }
    public void ejecutar(Long id,  String estado){
        validarExistenciaPrevia(id);
        this.repositorioLugar.actualizarLugarEstado(id, estado);
    }

    private void validarExistenciaPrevia(Long id) {
        boolean existe = this.repositorioLugar.existeLugarPorId(id);
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_LUGAR_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
