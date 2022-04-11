package com.ceiba.lugar.servicio.testDataBuilder;

import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.utils.CONSTANTES;

public class LugarTestDataBuilder {

    private Long id;
    private String estado;

    public LugarTestDataBuilder(){
        this.estado = CONSTANTES.DISPONIBLE;

    }
    public LugarTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public LugarTestDataBuilder conEstado(String estado){
        this.estado = estado;
        return this;
    }

    public Lugar build(){
        return new Lugar(id, estado);
    }


}
