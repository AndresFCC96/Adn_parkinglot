package com.ceiba.lugar.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.lugar.comando.ComandoLugar;
import com.ceiba.lugar.comando.manejador.ManejadorActualizarLugar;
import com.ceiba.lugar.comando.manejador.ManejadorCrearLugar;
import com.ceiba.lugar.comando.manejador.ManejadorEliminarLugar;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lugar")
@Api(tags = {"Controlador comando usuario"})
@CrossOrigin("*")
public class ComandoControladorLugar {

    private final ManejadorCrearLugar manejadorCrearLugar;
    private final ManejadorActualizarLugar manejadorActualizarLugar;
    private final ManejadorEliminarLugar manejadorEliminarLugar;

    @Autowired

    public ComandoControladorLugar(ManejadorCrearLugar manejadorCrearLugar,
                                   ManejadorActualizarLugar manejadorActualizarLugar,
                                   ManejadorEliminarLugar manejadorEliminarLugar) {
        this.manejadorCrearLugar = manejadorCrearLugar;
        this.manejadorActualizarLugar = manejadorActualizarLugar;
        this.manejadorEliminarLugar = manejadorEliminarLugar;
    }

    @PostMapping
    @ApiOperation("Crear lugar")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoLugar comandoLugar){
        return manejadorCrearLugar.ejecutar(comandoLugar);
    }

    @PutMapping(value = "{id}")
    @ApiOperation("Actualizar lugar")
    public void actualizar(@RequestBody ComandoLugar comandoLugar, @PathVariable Long id){
        comandoLugar.setIdLugar(id);
        manejadorActualizarLugar.ejecutar(comandoLugar);
    }

    @DeleteMapping(value = "{id}")
    @ApiOperation("Eliminar lugar")
    public void eliminar(@PathVariable Long id){
        manejadorEliminarLugar.ejecutar(id);
    }

}
