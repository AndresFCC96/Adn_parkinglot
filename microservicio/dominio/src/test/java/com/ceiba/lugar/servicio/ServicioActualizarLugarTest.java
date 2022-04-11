package com.ceiba.lugar.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.puerto.repository.RepositorioLugar;
import com.ceiba.lugar.servicio.ServicioActualizarLugar;
import com.ceiba.lugar.servicio.testDataBuilder.LugarTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioActualizarLugarTest {

    private static final String EL_LUGAR_NO_EXISTE_EN_EL_SISTEMA = "El lugar no existe en el sistema";

    @Test
    @DisplayName("Debera validar la existencia previa del lugar")
    void debraValidarLaExistenciaPreviaDelLugar(){
        //Arrange
        Lugar lugar = new LugarTestDataBuilder().conId(1L).build();
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        Mockito.when(repositorioLugar.existeLugarPorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarLugar servicioActualizarLugar = new ServicioActualizarLugar(repositorioLugar);
        //Act - assert
        BasePrueba.assertThrows(() -> servicioActualizarLugar.ejecutar(lugar), ExcepcionDuplicidad.class, EL_LUGAR_NO_EXISTE_EN_EL_SISTEMA);

    }


    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio(){
        // arrange
        Lugar lugar = new LugarTestDataBuilder().conId(1L).build();
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        Mockito.when(repositorioLugar.existeLugarPorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarLugar servicioActualizarLugar = new ServicioActualizarLugar(repositorioLugar);
        // act
        servicioActualizarLugar.ejecutar(lugar);
        // assert
        Mockito.verify(repositorioLugar, Mockito.times(1)).actualizarLugar(lugar);
    }
}
