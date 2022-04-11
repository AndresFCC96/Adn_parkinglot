package com.ceiba.lugar.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.puerto.repository.RepositorioLugar;

public class ServicioCrearLugar {

    private static final String EL_LUGAR_YA_EXISTE_EN_EL_SISTEMA = "El lugar ya existe en el sistema";

    private final RepositorioLugar repositorioLugar;

    public ServicioCrearLugar(RepositorioLugar repositorioLugar) {

        this.repositorioLugar = repositorioLugar;
    }

    public Long ejecutar(Lugar lugar) {
        validarExistenciaPrevia(lugar);
        return this.repositorioLugar.crearLugar(lugar);
    }

    private void validarExistenciaPrevia(Lugar lugar) {
        if(lugar.getIdLugar() != null){
            boolean existe = this.repositorioLugar.existeLugarPorId(lugar.getIdLugar());
            if(existe) {
                throw new ExcepcionDuplicidad(EL_LUGAR_YA_EXISTE_EN_EL_SISTEMA);
            }
        }

    }
}
