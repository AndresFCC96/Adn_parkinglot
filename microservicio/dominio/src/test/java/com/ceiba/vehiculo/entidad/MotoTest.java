package com.ceiba.vehiculo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.servicio.testdatabuilder.CarroTestDataBuilder;
import com.ceiba.vehiculo.utils.CONSTANTES;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MotoTest {
    private static final String SE_DEBE_INGRESAR_EL_ID_VEHICULO = "Se debe ingresar el id del vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_PLACA = "Se debe ingresar la placa";
    private static final String SE_DEBE_INGRESAR_EL_TIPO = "Se debe ingresar el tipo de vehiculo";
    private static final String SE_DEBE_INGRESAR_EL_LUGAR_DEL_VEHICULO = "Se debe ingresar el lugar del vehiculo";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA = "Se debe ingresar la fecha de entrada";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA = "Se debe ingresar la fecha de salida";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_DEL_PARQUEO = "Se debe ingresar el valor total del parqueo";
    private static final String SE_DEBE_INGRESAR_EL_VALOR_BASE= "Se debe ingresar el valor base por hora";


    @Test
    @DisplayName("Deberia crear correctamente el vehiculo")
    void deberiaCrearCorrectamenteElVehiculo(){
        //Arrange
        LocalDateTime fechaEntrada = LocalDateTime.of(LocalDate.of(2022, 4, 2), LocalTime.of(13, 32));
        LocalDateTime fechaSalida = LocalDateTime.of(LocalDate.of(2022, 4, 2), LocalTime.of(15, 40));
        // Act
        Vehiculo vehiculo = new CarroTestDataBuilder().conId(1L).conPlaca("ABC123").build();
        //Assert
        assertEquals(1, vehiculo.getIdVehiculo());
        assertEquals("ABC123", vehiculo.getPlaca());
        assertEquals(fechaEntrada, vehiculo.getFechaEntrada());
        assertEquals(fechaSalida, vehiculo.getFechaSalida());
        assertEquals(3000, vehiculo.getValorParqueo());
        assertEquals(1500, vehiculo.getValorBase());
        assertEquals(1, vehiculo.getLugarVehiculo());
        assertEquals(CONSTANTES.MOTO, vehiculo.getTipo());
    }
    @Test
    void debeCalcularElTotalAPagarEnHorarioNormalCarro(){
        LocalDateTime fechaEntrada = LocalDateTime.of(LocalDate.of(2022, 2, 4), LocalTime.of(4, 32));
        LocalDateTime fechaSalida = LocalDateTime.of(LocalDate.of(2022, 2, 4), LocalTime.of(13, 40));

        Vehiculo vehiculo = new CarroTestDataBuilder().conId(1L)
                .conTipoVehiculo(CONSTANTES.CARRO)
                .conFechaEntrada(fechaEntrada)
                .conFechaSalida(fechaSalida).build();

        assertEquals(13500 ,vehiculo.getValorParqueo());

    }
    @Test
    void debeCalcularElTotalAPagarEnDiasFestivos(){
        LocalDateTime fechaEntrada = LocalDateTime.of(LocalDate.of(2022, 2, 2), LocalTime.of(19, 32));
        LocalDateTime fechaSalida = LocalDateTime.of(LocalDate.of(2022, 2, 2), LocalTime.of(21, 40));

        Vehiculo vehiculo = new CarroTestDataBuilder().conId(1L).conFechaEntrada(fechaEntrada).conFechaSalida(fechaSalida).build();

        assertEquals(6000 ,vehiculo.getValorParqueo());
    }
    @Test
    void debeCalcularElTotalAPagar(){
        LocalDateTime fechaEntrada = LocalDateTime.of(LocalDate.of(2022, 4, 4), LocalTime.of(17, 32));
        LocalDateTime fechaSalida = LocalDateTime.of(LocalDate.of(2022, 4, 4), LocalTime.of(19, 40));

        Vehiculo vehiculo = new CarroTestDataBuilder().conId(1L).conFechaEntrada(fechaEntrada).conFechaSalida(fechaSalida).build();

        assertEquals(3000 ,vehiculo.getValorParqueo());
    }
    @Test
    void debeFallarSinId(){
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conId(null);
        BasePrueba.assertThrows(() -> {
            carroTestDataBuilder.build();
        }, ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_EL_ID_VEHICULO);
    }
    @Test
    void debeFallarSinPlaca(){
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conId(1L).conPlaca(null);

        BasePrueba.assertThrows(() -> {
                    carroTestDataBuilder.build();
                }, ExcepcionValorObligatorio.class,
                SE_DEBE_INGRESAR_LA_PLACA);
    }
    @Test
    void debeFallarSinLugarVehiculo(){
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conId(1L).conIdLugar(null);
        BasePrueba.assertThrows(() -> {
            carroTestDataBuilder.build();
        },ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_EL_LUGAR_DEL_VEHICULO);
    }
    @Test
    void debeFallarSinTipoVehiculo(){
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conId(1L).conTipoVehiculo(null);
        BasePrueba.assertThrows(() -> {
            carroTestDataBuilder.build();
        },ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_EL_TIPO);
    }
    @Test
    void debeFallarSinFechaEntrada(){
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conId(1L).conFechaEntrada(null);
        BasePrueba.assertThrows(() -> {
            carroTestDataBuilder.build();
        },ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_LA_FECHA_DE_ENTRADA);
    }
    @Test
    void debeFallarSinPrecioBaseHora(){
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conId(1L).conPrecioBaseHora(null);
        BasePrueba.assertThrows(() -> {
            carroTestDataBuilder.build();
        },ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_EL_VALOR_BASE);
    }
    @Test
    void debeFallarSinFechaDeSalida(){
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conId(1L).conFechaSalida(null);
        BasePrueba.assertThrows(() -> {
            carroTestDataBuilder.build();
        },ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_LA_FECHA_DE_SALIDA);
    }
    @Test
    void debeFallarSinValorDelParqueo(){
        CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conId(1L).conFechaSalida(null);
        BasePrueba.assertThrows(() -> {
            carroTestDataBuilder.build();
        },ExcepcionValorObligatorio.class, SE_DEBE_INGRESAR_EL_VALOR_DEL_PARQUEO);
    }
}
