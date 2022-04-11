package com.ceiba.lugar.comando.manejador;

import com.ceiba.lugar.comando.ComandoLugar;
import com.ceiba.lugar.comando.fabrica.FabricaLugar;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.servicio.ServicioActualizarLugar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarLugar {

    private final FabricaLugar fabricaLugar;
    private final ServicioActualizarLugar servicioActualizarLugar;

    public ManejadorActualizarLugar(FabricaLugar fabricaLugar, ServicioActualizarLugar servicioActualizarLugar){
        this.fabricaLugar = fabricaLugar;
        this.servicioActualizarLugar = servicioActualizarLugar;
    }
    public void ejecutar(ComandoLugar comandoLugar){
        Lugar lugar = this.fabricaLugar.crear(comandoLugar);
        this.servicioActualizarLugar.ejecutar(lugar);
    }
}
