package com.ceiba.vehiculo.consulta;

import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarVehiculo {

    private final DaoVehiculo daoVehiculo;

    public ManejadorListarVehiculo(DaoVehiculo daoVehiculo) {
        this.daoVehiculo = daoVehiculo;
    }

    public List<DtoVehiculo> ejecutar(){ return this.daoVehiculo.listarTodosLosVehiculos();}
}
