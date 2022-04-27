package com.ceiba.vehiculo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.lugar.puerto.repository.RepositorioLugar;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.vehiculo.servicio.testdatabuilder.CarroTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServicioCrearVehiculoTest {

    private static final String EL_VEHICULO_YA_EXISTE_EN_EL_SISTEMA = "El vehiculo ya existe en el sistema";
    private static final String EL_ESPACIO_NO_EXISTE_EN_EL_SITEMA = "El lugar no existe en el sistema";
    private static final String EL_ESPACIO_YA_ESTA_OCUPADO = "El lugar ya esta ocupado";

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia del vehiculo")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDelVehiculo(){
        //Arrange
        Vehiculo vehiculo = new CarroTestDataBuilder().build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        Mockito.when(repositorioVehiculo.existeVehiculoConPlaca(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioLugar.existeLugarPorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioVehiculo.existeVehiculoEnUnLugarConId(Mockito.anyLong())).thenReturn(false);
        ServicioCrearVehiculo servicioCrearVehiculo = new ServicioCrearVehiculo(repositorioVehiculo, repositorioLugar);
        //Assert
        BasePrueba.assertThrows(()->servicioCrearVehiculo.ejecutar(vehiculo), ExcepcionDuplicidad.class, EL_VEHICULO_YA_EXISTE_EN_EL_SISTEMA);
    }
    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la existencia del espacio")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDelEspacio(){
        //Arrange
        Vehiculo vehiculo = new CarroTestDataBuilder().build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        Mockito.when(repositorioVehiculo.existeVehiculoConPlaca(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioLugar.existeLugarPorId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioVehiculo.existeVehiculoEnUnLugarConId(Mockito.anyLong())).thenReturn(false);
        ServicioCrearVehiculo servicioCrearVehiculo = new ServicioCrearVehiculo(repositorioVehiculo, repositorioLugar);
        //Assert
        BasePrueba.assertThrows(()->servicioCrearVehiculo.ejecutar(vehiculo), ExcepcionSinDatos.class, EL_ESPACIO_NO_EXISTE_EN_EL_SITEMA);
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando se valide la exististencia de un id espacio")
    void deberiaLanzarUnaExcepcionCuandoSeValideLaExistenciaDeUnIdEspacio(){
        //Arrange
        Vehiculo vehiculo = new CarroTestDataBuilder().build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        Mockito.when(repositorioVehiculo.existeVehiculoConPlaca(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioLugar.existeLugarPorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioVehiculo.existeVehiculoEnUnLugarConId(Mockito.anyLong())).thenReturn(true);
        ServicioCrearVehiculo servicioCrearVehiculo = new ServicioCrearVehiculo(repositorioVehiculo, repositorioLugar);
        //Assert
        BasePrueba.assertThrows(()->servicioCrearVehiculo.ejecutar(vehiculo), ExcepcionDuplicidad.class, EL_ESPACIO_YA_ESTA_OCUPADO);
    }

    @Test
    @DisplayName("Deberia crear el vehiculo de manera correcta")
    void deberiaCrearElVehiculoDeManeraCorrecta(){
        //Arrange
        Vehiculo vehiculo = new CarroTestDataBuilder().build();
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        Mockito.when(repositorioVehiculo.existeVehiculoConPlaca(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioLugar.existeLugarPorId(Mockito.anyLong())).thenReturn(true);
        Mockito.when(repositorioVehiculo.existeVehiculoEnUnLugarConId(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioVehiculo.crearVehiculo(vehiculo)).thenReturn(15L);
        ServicioCrearVehiculo servicioCrearVehiculo = new ServicioCrearVehiculo(repositorioVehiculo, repositorioLugar);
        //Act
        Long id =servicioCrearVehiculo.ejecutar(vehiculo);
        //Assert
        assertEquals(15, id);
        Mockito.verify(repositorioVehiculo, Mockito.times(1)).crearVehiculo(vehiculo);
    }


}
