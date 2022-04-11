package com.ceiba.vehiculo.servicio.testdatabuilder;

import com.ceiba.utils.CONSTANTES;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CarroTestDataBuilder {

    private Long idVehiculo;
    private String placa;
    private String tipo;
    private Long lugarVehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Double valorParqueo;

    public CarroTestDataBuilder() {

        this.placa = "XYZ456";
        this.idVehiculo = 1L;
        this.tipo = CONSTANTES.CARRO;
        this.fechaEntrada = LocalDateTime.of(LocalDate.of(2022, 4, 4), LocalTime.of(13, 32));
        this.fechaSalida = LocalDateTime.of(LocalDate.of(2022, 4, 4), LocalTime.of(15, 40));
    }

    public CarroTestDataBuilder conPlaca(String placa){
        this.placa = placa;
        return this;
    }
    public CarroTestDataBuilder conId(Long idVehiculo){
        this.idVehiculo = idVehiculo;
        return this;
    }
    public CarroTestDataBuilder conIdLugar(Long lugarVehiculo){
        this.lugarVehiculo = lugarVehiculo;
        return this;
    }
    public CarroTestDataBuilder conTipoVehiculo(String tipo){
        this.tipo = tipo;
        return this;
    }
    public CarroTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada){
        this.fechaEntrada = fechaEntrada;
        return this;
    }
    public CarroTestDataBuilder conFechaSalida(LocalDateTime fechaSalida){
        this.fechaSalida = fechaSalida;
        return this;
    }
    public CarroTestDataBuilder conTotalPagar(Double valorParqueo){
        this.valorParqueo = valorParqueo;
        return this;
    }
    public Vehiculo build(){
        return new Vehiculo(idVehiculo, placa, tipo, lugarVehiculo, fechaEntrada, fechaSalida);
    }

}
