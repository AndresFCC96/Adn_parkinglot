package com.ceiba.lugar.servicio.testbuilder;

import com.ceiba.lugar.comando.ComandoLugar;

public class ComandoLugarTestDataBuilder {

    private Long id;
    private final String estado;

    public ComandoLugarTestDataBuilder(){
        this.estado = "D";
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
