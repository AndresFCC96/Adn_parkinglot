package com.ceiba.vehiculo.comando.manejador;

import com.ceiba.lugar.servicio.ServicioActualizarEstadoLugar;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import com.ceiba.vehiculo.servicio.ServicioEliminarVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarVehiculo implements ManejadorComando<Long> {

    private final ServicioEliminarVehiculo servicioEliminarVehiculo;
    private final ServicioActualizarEstadoLugar servicioActualizarEstadoLugar;
    private final DaoVehiculo daoVehiculo;

    public ManejadorEliminarVehiculo(ServicioEliminarVehiculo servicioEliminarVehiculo,
                                     ServicioActualizarEstadoLugar servicioActualizarEstadoLugar,
                                     DaoVehiculo daoVehiculo) {
        this.servicioEliminarVehiculo = servicioEliminarVehiculo;
        this.servicioActualizarEstadoLugar = servicioActualizarEstadoLugar;
        this.daoVehiculo = daoVehiculo;
    }

    public void ejecutar(Long id) {
        DtoVehiculo vehiculo = daoVehiculo.buscarVehiculoPorId(id);
        this.servicioActualizarEstadoLugar.ejecutar(vehiculo.getLugarVehiculo(), "D");
        this.servicioEliminarVehiculo.ejecutar(id);}

}
