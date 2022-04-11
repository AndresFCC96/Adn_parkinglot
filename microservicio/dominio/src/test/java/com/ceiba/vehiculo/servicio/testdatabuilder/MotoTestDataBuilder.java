package com.ceiba.vehiculo.servicio.testdatabuilder;

import com.ceiba.utils.CONSTANTES;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class MotoTestDataBuilder {

    private Long idVehiculo;
    private String placa;
    private String tipo;
    private Long lugarVehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Double valorParqueo;

    public MotoTestDataBuilder() {

        this.placa = "XYZ456";
        this.idVehiculo = 1L;
        this.tipo = CONSTANTES.MOTO;
        this.fechaEntrada = LocalDateTime.of(LocalDate.of(2022, 4, 4), LocalTime.of(13, 32));
        this.fechaSalida = LocalDateTime.of(LocalDate.of(2022, 4, 4), LocalTime.of(15, 40));
    }

    public MotoTestDataBuilder conPlaca(String placa){
        this.placa = placa;
        return this;
    }
    public MotoTestDataBuilder conId(Long idVehiculo){
        this.idVehiculo = idVehiculo;
        return this;
    }
    public MotoTestDataBuilder conIdLugar(Long lugarVehiculo){
        this.lugarVehiculo = lugarVehiculo;
        return this;
    }
    public MotoTestDataBuilder conTipoVehiculo(String tipo){
        this.tipo = tipo;
        return this;
    }
    public MotoTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada){
        this.fechaEntrada = fechaEntrada;
        return this;
    }
    public MotoTestDataBuilder conFechaSalida(LocalDateTime fechaSalida){
        this.fechaSalida = fechaSalida;
        return this;
    }
    public MotoTestDataBuilder conTotalPagar(Double valorParqueo){
        this.valorParqueo = valorParqueo;
        return this;
    }
    public Vehiculo build(){
        return new Vehiculo(idVehiculo, placa, tipo, lugarVehiculo, fechaEntrada, fechaSalida);
    }
}
