package com.ceiba.lugar.servicio.testbuilder;

import com.ceiba.lugar.comando.ComandoLugar;
import com.ceiba.utils.CONSTANTES;

public class ComandoLugarTestDataBuilder {

    private Long id;
    private String estado;

    public ComandoLugarTestDataBuilder(){
        this.estado = CONSTANTES.DISPONIBLE;
    }
    public ComandoLugarTestDataBuilder conNombre(String nombre){
        return this;
    }
    public ComandoLugarTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }
    public ComandoLugar build(){
        return new ComandoLugar(id, estado);
    }
}
