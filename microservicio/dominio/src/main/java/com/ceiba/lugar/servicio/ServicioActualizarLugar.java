package com.ceiba.lugar.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.puerto.repository.RepositorioLugar;

public class ServicioActualizarLugar {

    private static final String EL_LUGAR_NO_EXISTE_EN_EL_SISTEMA = "El lugar no existe en el sistema";

    private final RepositorioLugar repositorioLugar;

    public ServicioActualizarLugar(RepositorioLugar repositorioLugar) {
        this.repositorioLugar = repositorioLugar;
    }

    public void  ejecutar(Lugar lugar) {
        validarExistenciaPrevia(lugar);
        this.repositorioLugar.actualizarLugar(lugar);
    }

    private void validarExistenciaPrevia(Lugar lugar) {
        boolean existe = this.repositorioLugar.existeLugarPorId(lugar.getIdLugar());
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_LUGAR_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
