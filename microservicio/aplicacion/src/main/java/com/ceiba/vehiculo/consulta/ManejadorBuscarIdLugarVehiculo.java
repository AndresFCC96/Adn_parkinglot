package com.ceiba.vehiculo.consulta;

import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorBuscarIdLugarVehiculo {
    private final DaoVehiculo daoVehiculo;

    public ManejadorBuscarIdLugarVehiculo(DaoVehiculo daoVehiculo) {
        this.daoVehiculo = daoVehiculo;
    }

    public DtoVehiculo ejecutar(Long idEspacio){ return this.daoVehiculo.buscarVehiculoEnUnEspacioEspecifico(idEspacio); }
}
