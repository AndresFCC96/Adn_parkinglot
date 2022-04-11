package com.ceiba.vehiculo.modelo.entidad;

import com.ceiba.utils.CONSTANTES;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Data
public class Vehiculo {

    private static final String SE_DEBE_INGRESAR_EL_ID_VEHICULO = "Se debe ingresar el id del vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_PLACA = "Se debe ingresar la placa";
    private static final String SE_DEBE_INGRESAR_EL_TIPO = "Se debe ingresar el tipo de vehiculo";
    private static final String SE_DEBE_INGRESAR_EL_LUGAR_DEL_VEHICULO = "Se debe ingresar el lugar del vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA = "Se debe ingresar la fecha de entrada";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA = "Se debe ingresar la fecha de salida";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_DEL_PARQUEO = "Se debe ingresar el valor total del parqueo";


    private Long idVehiculo;
    private String placa;
    private String tipo;
    private Long lugarVehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Double valorParqueo;

    public Vehiculo(Long id_vehiculo, String placa, String tipo, Long lugar_vehiculo,
                    LocalDateTime fecha_entrada, LocalDateTime fecha_salida) {

        validarObligatorio(id_vehiculo, SE_DEBE_INGRESAR_EL_ID_VEHICULO);
        validarObligatorio(placa, SE_DEBE_INGRESAR_LA_PLACA);
        validarObligatorio(tipo, SE_DEBE_INGRESAR_EL_TIPO);
        validarObligatorio(lugar_vehiculo, SE_DEBE_INGRESAR_EL_LUGAR_DEL_VEHICULO);
        validarObligatorio(fecha_entrada, SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA);
        validarObligatorio(fecha_salida, SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA);

        this.idVehiculo = id_vehiculo;
        this.placa = placa;
        this.tipo = tipo;
        this.lugarVehiculo = lugar_vehiculo;
        this.fechaEntrada = fecha_entrada;
        this.fechaSalida = fecha_salida;
    }

    public Integer calcularNumeroDeHoras(LocalDateTime fechaEntrada, LocalDateTime fechaSalida){
        return fechaSalida.getHour() - fechaEntrada.getHour();
    }

    public Double calcularTotal(LocalDateTime fechaEntrada,
                                LocalDateTime fechaSalida,
                                String tipo) {

        Double total = 0.0;
        DayOfWeek diaDeLaSemana = fechaEntrada.getDayOfWeek();
        Integer totalHoras = calcularNumeroDeHoras(fechaEntrada, fechaSalida);
        if (diaDeLaSemana == DayOfWeek.SATURDAY || diaDeLaSemana == DayOfWeek.SUNDAY) {
            if (tipo.equals(CONSTANTES.CARRO)){
                return  (totalHoras * CONSTANTES.TARIFA_EXTRA_CARRO);
            }else{
                return (totalHoras * CONSTANTES.TARIFA_EXTRA_MOTO);
            }
        }else {
            if (tipo.equals(CONSTANTES.CARRO)){
                total = CONSTANTES.TARIFA_BASE_CARRO * totalHoras;
            }else{
                total = CONSTANTES.TARIFA_BASE_MOTO * totalHoras;
            }
        }
        return total;
    }
}
