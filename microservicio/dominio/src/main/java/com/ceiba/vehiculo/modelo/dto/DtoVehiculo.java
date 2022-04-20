package com.ceiba.vehiculo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoVehiculo {
    private Long idVehiculo;
    private String placa;
    private String tipo;
    private Long lugarVehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Double valorParqueo;
}
