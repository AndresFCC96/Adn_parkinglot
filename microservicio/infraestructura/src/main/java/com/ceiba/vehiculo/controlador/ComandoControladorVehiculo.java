package com.ceiba.vehiculo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorActualizarVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorCrearVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorEliminarVehiculo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculo")
@Api(tags = {"Controlador comando vehiculo "})
@CrossOrigin("*")
public class ComandoControladorVehiculo {

    private final ManejadorActualizarVehiculo manejadorActualizarVehiculo;
    private final ManejadorEliminarVehiculo manejadorEliminarVehiculo;
    private final ManejadorCrearVehiculo manejadorCrearVehiculo;

    @Autowired
    public ComandoControladorVehiculo(ManejadorActualizarVehiculo manejadorActualizarVehiculo,
                                      ManejadorEliminarVehiculo manejadorEliminarVehiculo,
                                      ManejadorCrearVehiculo manejadorCrearVehiculo) {
        this.manejadorActualizarVehiculo = manejadorActualizarVehiculo;
        this.manejadorEliminarVehiculo = manejadorEliminarVehiculo;
        this.manejadorCrearVehiculo = manejadorCrearVehiculo;
    }


    @PostMapping
    @ApiOperation("Crear vehiculo")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoVehiculo comandoVehiculo) {
        return manejadorCrearVehiculo.ejecutar(comandoVehiculo);
    }

    @DeleteMapping(value="/{id}")
    @ApiOperation("Eliminar vehiculo")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarVehiculo.ejecutar(id);
    }

    @PutMapping(value="/{id}")
    @ApiOperation("Actualizar vehiculo")
    public void actualizar(@RequestBody ComandoVehiculo comandoVehiculo,@PathVariable Long id) {
        comandoVehiculo.setIdVehiculo(id);
        manejadorActualizarVehiculo.ejecutar(comandoVehiculo);
    }
}
