package com.ceiba.vehiculo.controlador;

import com.ceiba.vehiculo.consulta.ManejadorBuscarIdLugarVehiculo;
import com.ceiba.vehiculo.consulta.ManejadorListarPorPlacaVehiculo;
import com.ceiba.vehiculo.consulta.ManejadorListarVehiculo;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculo")
@Api(tags = {"Controlador consulta vehiculo"})
@CrossOrigin("*")
public class ConsultaControladorVehiculo {

    private final ManejadorListarVehiculo manejadorListarVehiculo;
    private final ManejadorListarPorPlacaVehiculo manejadorListarPorPlacaVehiculo;
    private final ManejadorBuscarIdLugarVehiculo manejadorBuscarIdEspacioVehiculo;

    public ConsultaControladorVehiculo(ManejadorListarVehiculo manejadorListarVehiculo,
                                       ManejadorListarPorPlacaVehiculo manejadorListarPorPlacaVehiculo,
                                       ManejadorBuscarIdLugarVehiculo manejadorBuscarIdEspacioVehiculo) {
        this.manejadorListarVehiculo = manejadorListarVehiculo;
        this.manejadorListarPorPlacaVehiculo = manejadorListarPorPlacaVehiculo;
        this.manejadorBuscarIdEspacioVehiculo = manejadorBuscarIdEspacioVehiculo;
    }

    @GetMapping
    @ApiOperation("Listar vehiculo")
    public List<DtoVehiculo> list(){ return this.manejadorListarVehiculo.ejecutar();}

    @GetMapping("/{placa}")
    @ApiOperation("Listar vehiculos por placa")
    public List<DtoVehiculo> lisatrPorPlaca(@PathVariable String placa){
        return this.manejadorListarPorPlacaVehiculo.ejecutar(placa);
    }

    @GetMapping("/idEspacio/{idEspacio}")
    @ApiOperation("Buscar vehiculo por id espacio")
    public DtoVehiculo buscarPorIdEspacio(@PathVariable Long idEspacio){
        return this.manejadorBuscarIdEspacioVehiculo.ejecutar(idEspacio);
    }
}
