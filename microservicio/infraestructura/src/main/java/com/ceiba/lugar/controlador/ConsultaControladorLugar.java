package com.ceiba.lugar.controlador;

import com.ceiba.lugar.consulta.ManejadorBuscarPorIdLugar;
import com.ceiba.lugar.consulta.ManejadorListarLugar;
import com.ceiba.lugar.consulta.ManejadorListarTodosLugar;
import com.ceiba.lugar.modelo.dto.DtoLugar;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lugar")
@Api(tags = {"Controlador consulta lugar"})
public class ConsultaControladorLugar {
    private final ManejadorListarLugar manejadorListarLugar;
    private final ManejadorListarTodosLugar manejadorListarTodosLugar;
    private final ManejadorBuscarPorIdLugar manejadorBuscarPorIdLugar;

    public ConsultaControladorLugar(ManejadorListarLugar manejadorListarLugar,
                                    ManejadorListarTodosLugar manejadorListarTodosLugar,
                                    ManejadorBuscarPorIdLugar manejadorBuscarPorIdLugar) {
        this.manejadorListarLugar = manejadorListarLugar;
        this.manejadorListarTodosLugar = manejadorListarTodosLugar;
        this.manejadorBuscarPorIdLugar = manejadorBuscarPorIdLugar;
    }

    @GetMapping("/{estado}")
    @ApiOperation("Listar lugares por estado")
    public List<DtoLugar> listarPorEstado(@PathVariable String estado){ return this.manejadorListarLugar.ejecutar(estado);}

    @GetMapping
    @ApiOperation("Listar lugares")
    public List<DtoLugar> listar(){ return this.manejadorListarTodosLugar.ejecutar();}

    @GetMapping("/buscar/{id}")
    @ApiOperation("Buscar lugar por id")
    public DtoLugar buscarPorId(@PathVariable Long id){return this.manejadorBuscarPorIdLugar.ejecutar(id);}
}
