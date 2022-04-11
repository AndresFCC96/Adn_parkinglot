package com.ceiba.vehiculo.controlador;

import com.ceiba.utils.CONSTANTES;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ComandoVehiculoTestDataBuilder {

    private Long idVehiculo;
    private String placa;
    private String tipo;
    private Long lugarVehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;


    public ComandoVehiculoTestDataBuilder() {
        this.placa = RandomStringUtils.random(6 , "asdfghjkl12345zxcvbg");
        this.lugarVehiculo = 2L;
        this.tipo = CONSTANTES.CARRO;
        this.fechaEntrada = LocalDateTime.of(LocalDate.of(2021, 11, 1), LocalTime.of(10, 06));
        this.fechaSalida = LocalDateTime.of(LocalDate.of(2021, 11, 1), LocalTime.of(16, 06));
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


