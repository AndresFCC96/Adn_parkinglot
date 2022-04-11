package com.ceiba.lugar.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.lugar.comando.ComandoLugar;
import com.ceiba.lugar.comando.fabrica.FabricaLugar;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.servicio.ServicioCrearLugar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearLugar {

    private final FabricaLugar fabricaLugar;
    private final ServicioCrearLugar servicioCrearLugar;

    public ManejadorCrearLugar(FabricaLugar fabricaLugar, ServicioCrearLugar servicioCrearLugar) {
        this.fabricaLugar = fabricaLugar;
        this.servicioCrearLugar = servicioCrearLugar;
    }
    public ComandoRespuesta<Long> ejecutar(ComandoLugar comandoLugar) {
        Lugar lugar = this.fabricaLugar.crear(comandoLugar);
        return new ComandoRespuesta<>(this.servicioCrearLugar.ejecutar(lugar));
    }
}
