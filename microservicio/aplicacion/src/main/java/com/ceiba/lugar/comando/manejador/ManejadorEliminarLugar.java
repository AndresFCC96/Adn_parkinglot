package com.ceiba.lugar.comando.manejador;

import com.ceiba.lugar.servicio.ServicioEliminarLugar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarLugar {
    private final ServicioEliminarLugar servicioEliminarLugar;

    public ManejadorEliminarLugar(ServicioEliminarLugar servicioEliminarLugar){
        this.servicioEliminarLugar = servicioEliminarLugar;
    }

    public void ejecutar(Long id){
        this.servicioEliminarLugar.ejecutar(id);
    }
}
