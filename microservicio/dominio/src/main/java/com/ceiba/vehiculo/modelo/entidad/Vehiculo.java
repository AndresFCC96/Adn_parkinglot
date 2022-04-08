package com.ceiba.vehiculo.modelo.entidad;

import com.ceiba.vehiculo.utils.CONSTANTES;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import static com.ceiba.dominio.ValidadorArgumento.*;

@Data
public class Vehiculo {

    private static final String SE_DEBE_INGRESAR_EL_ID_VEHICULO = "Se debe ingresar el id del vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_PLACA = "Se debe ingresar la placa";
    private static final String SE_DEBE_INGRESAR_EL_TIPO = "Se debe ingresar el tipo de vehiculo";
    private static final String SE_DEBE_INGRESAR_EL_LUGAR_DEL_VEHICULO = "Se debe ingresar el lugar del vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA = "Se debe ingresar la fecha de entrada";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA = "Se debe ingresar la fecha de salida";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_DEL_PARQUEO = "Se debe ingresar el valor total del parqueo";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_BASE= "Se debe ingresar el valor base por hora";


    private Long idVehiculo;
    private String placa;
    private String tipo;
    private Long lugarVehiculo;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Double valorParqueo;
    private Double valorBase;

    public Vehiculo(Long id_vehiculo, String placa, String tipo, Long lugar_vehiculo,
                    LocalDateTime fecha_entrada, LocalDateTime fecha_salida, Double valor_base) {

        validarObligatorio(id_vehiculo, SE_DEBE_INGRESAR_EL_ID_VEHICULO);
        validarObligatorio(placa, SE_DEBE_INGRESAR_LA_PLACA);
        validarObligatorio(tipo, SE_DEBE_INGRESAR_EL_TIPO);
        validarObligatorio(lugar_vehiculo, SE_DEBE_INGRESAR_EL_LUGAR_DEL_VEHICULO);
        validarObligatorio(fecha_entrada, SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA);
        validarObligatorio(fecha_salida, SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA);
        validarObligatorio(valor_base, SE_DEBE_INGRESAR_EL_VALOR_BASE);

        this.idVehiculo = id_vehiculo;
        this.placa = placa;
        this.tipo = tipo;
        this.lugarVehiculo = lugar_vehiculo;
        this.fechaEntrada = fecha_entrada;
        this.fechaSalida = fecha_salida;
        this.valorBase = valor_base;
    }

    public Integer calcularNumeroDeHoras(LocalDateTime fechaEntrada, LocalDateTime fechaSalida){
        return fechaSalida.getHour() - fechaEntrada.getHour();
    }

    public Double calcularTotal(Double precioBaseHora,
                                LocalDateTime fechaEntrada,
                                LocalDateTime fechaSalida,
                                String tipo) {
        Double total = 0.0;
        DayOfWeek diaDeLaSemana = fechaEntrada.getDayOfWeek();
        Integer totalHoras = calcularNumeroDeHoras(fechaEntrada, fechaSalida);
        if (diaDeLaSemana == DayOfWeek.SATURDAY || diaDeLaSemana == DayOfWeek.SUNDAY) {
            if (tipo == CONSTANTES.CARRO){
                return  (totalHoras * 5000.0);
            }else{
                return (totalHoras * 3000.0);
            }
        }else {
            if (tipo == CONSTANTES.CARRO){
                total = 3000.0 * totalHoras;
            }else{
                total = 1500.0 * totalHoras;
            }
        }
        return total;
    }
}
