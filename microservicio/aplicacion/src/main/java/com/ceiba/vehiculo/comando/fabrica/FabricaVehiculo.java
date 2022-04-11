package com.ceiba.vehiculo.comando.fabrica;

import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import org.springframework.stereotype.Component;

@Component
public class FabricaVehiculo {
    public Vehiculo crear(ComandoVehiculo comandoVehiculo){
        return new Vehiculo(
                comandoVehiculo.getIdVehiculo(),
                comandoVehiculo.getPlaca(),
                comandoVehiculo.getTipo(),
                comandoVehiculo.getLugarVehiculo(),
                comandoVehiculo.getFechaEntrada(),
                comandoVehiculo.getFechaSalida()
        );
    }
}
