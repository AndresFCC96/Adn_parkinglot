package com.ceiba.vehiculo.servicio.testbuilder;

import com.ceiba.vehiculo.comando.ComandoVehiculo;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ComandoVehiculoTestDataBuilder {

    private Long idVehiculo;
    private String placa;
    private final String tipo;
    private Long lugarVehiculo;
    private final LocalDateTime fechaEntrada;
    private final LocalDateTime fechaSalida;

    public ComandoVehiculoTestDataBuilder() {
        final String carro = "C";
        this.placa = RandomStringUtils.random(6 , "asdfghjkl12345zxcvbg");
        this.lugarVehiculo = 1L;
        this.tipo = carro;
        this.fechaEntrada = LocalDateTime.of(LocalDate.of(2021, 11, 1), LocalTime.of(10, 7));
        this.fechaSalida = LocalDateTime.of(LocalDate.of(2021, 11, 1), LocalTime.of(16, 7));
    }
    public ComandoVehiculoTestDataBuilder conId(Long id){
        this.idVehiculo = id;
        return this;
    }
    public ComandoVehiculoTestDataBuilder conPlaca(String placa){
        this.placa = placa;
        return this;
    }
    public ComandoVehiculoTestDataBuilder conIdEspacio(Long idEspacio){
        this.lugarVehiculo = idEspacio;
        return this;
    }
    public ComandoVehiculo build(){
        return new ComandoVehiculo(
                idVehiculo,
                placa,
                tipo,
                lugarVehiculo,
                fechaEntrada,
                fechaSalida
        );
    }
}


