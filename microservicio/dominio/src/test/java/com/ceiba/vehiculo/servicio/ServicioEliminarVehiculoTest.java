package com.ceiba.vehiculo.servicio;

import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarVehiculoTest {
    @Test
    @DisplayName("Deberia eliminar el vehiculo llamando al repositorio")
    void deberiaElminarElEspacioLlamandoAlRepositorio(){
        // arrange
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        ServicioEliminarVehiculo servicioEliminarVehiculo  = new ServicioEliminarVehiculo(repositorioVehiculo);
        // act
        servicioEliminarVehiculo.ejecutar(1L);
        // assert
        Mockito.verify(repositorioVehiculo, Mockito.times(1)).eliminar(1L);
    }
}
