package com.ceiba.lugar.servicio;

import com.ceiba.lugar.puerto.repository.RepositorioLugar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarLugarTest {

    @Test
    @DisplayName("Deberia eliminar el espacio llamando al repositorio")
    void deberiaElminarElLugarLlamandoAlRepositorio(){
        // arrange
        RepositorioLugar repositorioLugar = Mockito.mock(RepositorioLugar.class);
        ServicioEliminarLugar servicioEliminarLugar  = new ServicioEliminarLugar(repositorioLugar);
        // act
        servicioEliminarLugar.ejecutar(1L);
        // assert
        Mockito.verify(repositorioLugar, Mockito.times(1)).eliminarLugar(1L);
    }
}
