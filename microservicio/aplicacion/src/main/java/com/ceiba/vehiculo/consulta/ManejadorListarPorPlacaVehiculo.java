package com.ceiba.vehiculo.consulta;

import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ManejadorListarPorPlacaVehiculo {
    private final DaoVehiculo daoVehiculo;

    public ManejadorListarPorPlacaVehiculo(DaoVehiculo daoVehiculo) {
        this.daoVehiculo = daoVehiculo;
    }

    public List<DtoVehiculo> ejecutar(String placa){ return this.daoVehiculo.listaVehiculoPorPlaca(placa);}
}
