package com.ceiba.lugar.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.servicio.testDataBuilder.LugarTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LugarTest {

    private static final String SE_DEBE_INGRESAR_EL_ESTADO = "Se debe ingresar estado";

    @Test
    @DisplayName("Deberia crear correctamente el lugar")
    void deberiaCrearCorrectamenteElLugar(){
        //Arrange
        // act
        Lugar lugar = new LugarTestDataBuilder().conId(1L).build();
        // assert
        assertEquals(1, lugar.getIdLugar());
        assertEquals("D", lugar.getEstado());
    }
    @Test
    void debeFallarSinEstado(){
        LugarTestDataBuilder lugarTestDataBuilder = new LugarTestDataBuilder().conEstado(null);

        BasePrueba.assertThrows(() -> {
                    lugarTestDataBuilder.build();
                }, ExcepcionValorObligatorio.class,
                SE_DEBE_INGRESAR_EL_ESTADO);
    }
}
