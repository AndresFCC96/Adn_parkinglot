package com.ceiba.vehiculo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoVehiculo {
    private Long id_vehiculo;
    private String placa;
    private String tipo;
    private Long lugar_vehiculo;
    private LocalDateTime fecha_entrada;
    private LocalDateTime fecha_salida;
    private Double valor_parqueo;
}
