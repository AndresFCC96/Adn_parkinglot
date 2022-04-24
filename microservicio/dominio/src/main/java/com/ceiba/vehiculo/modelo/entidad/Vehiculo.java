package com.ceiba.vehiculo.modelo.entidad;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Data
public class Vehiculo {

    private static final String SE_DEBE_INGRESAR_LA_PLACA = "Se debe ingresar la placa";
    private static final String SE_DEBE_INGRESAR_EL_TIPO = "Se debe ingresar el tipo de vehiculo";
    private static final String SE_DEBE_INGRESAR_EL_LUGAR_DEL_VEHICULO = "Se debe ingresar el lugar del vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA = "Se debe ingresar la fecha de entrada";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA = "Se debe ingresar la fecha de salida";


    private Long idVehiculo;
    private String placa;
    private String tipo;
    private Long lugarVehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Double valorParqueo;

    private static String carro = "C";

    public Vehiculo(Long idVehiculo, String placa, String tipo, Long lugarVehiculo,
                    LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {

        validarObligatorio(placa, SE_DEBE_INGRESAR_LA_PLACA);
        validarObligatorio(tipo, SE_DEBE_INGRESAR_EL_TIPO);
        validarObligatorio(lugarVehiculo, SE_DEBE_INGRESAR_EL_LUGAR_DEL_VEHICULO);
        validarObligatorio(fechaEntrada, SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA);
        validarObligatorio(fechaSalida, SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA);

        this.idVehiculo = idVehiculo;
        this.placa = placa;
        this.tipo = tipo;
        this.lugarVehiculo = lugarVehiculo;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valorParqueo = calcularTotal(this.fechaEntrada, this.fechaSalida, this.tipo);
    }

    public Integer calcularNumeroDeHoras(LocalDateTime fechaEntrada, LocalDateTime fechaSalida){
        return fechaSalida.getHour() - fechaEntrada.getHour();
    }

    public Double calcularTotal(LocalDateTime fechaEntrada,
                                LocalDateTime fechaSalida,
                                String tipo) {

        Double total;
        DayOfWeek diaDeLaSemana = fechaEntrada.getDayOfWeek();
        Integer totalHoras = calcularNumeroDeHoras(fechaEntrada, fechaSalida);
        if (diaDeLaSemana == DayOfWeek.SATURDAY || diaDeLaSemana == DayOfWeek.SUNDAY) {
            if (tipo.equals(carro)){
                return  (totalHoras * 5000.0);
            }else{
                return (totalHoras * 3000.0);
            }
        }else {
            if (tipo.equals(carro)){
                total = 3000.0 * totalHoras;
            }else{
                total = 1500.0 * totalHoras;
            }
        }
        return total;
    }
}
