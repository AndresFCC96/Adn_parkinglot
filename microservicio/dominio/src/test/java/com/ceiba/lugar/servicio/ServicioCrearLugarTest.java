package com.ceiba.lugar.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.puerto.repository.RepositorioLugar;
import com.ceiba.lugar.servicio.testDataBuilder.LugarTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearLugarTest {

    private static final String EL_LUGAR_YA_EXISTE_EN_EL_SISTEMA = "El lugar ya existe en el sistema";

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia del lugar")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDelLugar(){
        // arrange
        Lugar lugar = new LugarTestDataBuilder().build();
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        Mockito.when(repositorioLugar.existeLugarPorId(Mockito.anyLong())).thenReturn(true);
        ServicioCrearLugar servicioCrearLugar = new ServicioCrearLugar(repositorioLugar);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearLugar.ejecutar(lugar), ExcepcionDuplicidad.class, EL_LUGAR_YA_EXISTE_EN_EL_SISTEMA);
    }

    @Test
    @DisplayName("Deberia crear el lugar de maner carrecta")
    void debriaCrearElLugarDeManeraCorrecta(){
        // arrange
        Lugar lugar = new LugarTestDataBuilder().build();
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        Mockito.when(repositorioLugar.existeLugarPorId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioLugar.crearLugar(lugar)).thenReturn(10L);
        ServicioCrearLugar servicioCrearLugar = new ServicioCrearLugar(repositorioLugar);
        // act
        Long id = servicioCrearLugar.ejecutar(lugar);
        // assert
        assertEquals(10L, id);
        Mockito.verify(repositorioLugar, Mockito.times(1)).crearLugar(lugar);
    }
}
