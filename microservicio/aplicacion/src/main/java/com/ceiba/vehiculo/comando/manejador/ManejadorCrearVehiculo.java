package com.ceiba.vehiculo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.lugar.servicio.ServicioActualizarEstadoLugar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.comando.fabrica.FabricaVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.servicio.ServicioCrearVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearVehiculo  implements ManejadorComandoRespuesta<ComandoVehiculo, ComandoRespuesta<Long>> {

    private final FabricaVehiculo fabricaVehiculo;
    private final ServicioCrearVehiculo servicioCrearVehiculo;
    private final ServicioActualizarEstadoLugar servicioActualizarEstadoLugar;

    public ManejadorCrearVehiculo(FabricaVehiculo fabricaVehiculo,
                                  ServicioCrearVehiculo servicioCrearVehiculo,
                                  ServicioActualizarEstadoLugar servicioActualizarEstadoLugar) {
        this.fabricaVehiculo = fabricaVehiculo;
        this.servicioCrearVehiculo = servicioCrearVehiculo;
        this.servicioActualizarEstadoLugar = servicioActualizarEstadoLugar;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoVehiculo comandoVehiculo) {
        Vehiculo vehiculo = this.fabricaVehiculo.crear(comandoVehiculo);
        this.servicioActualizarEstadoLugar.ejecutar(comandoVehiculo.getLugarVehiculo(), "O");
        return new ComandoRespuesta<>(this.servicioCrearVehiculo.ejecutar(vehiculo));
    }
}
