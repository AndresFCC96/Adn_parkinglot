package com.ceiba.vehiculo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorVehiculo.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorVehiculoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deberia crear un vehiculo")
    void deberiaCrearUnVehiculo() throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().build();
        // act - assert
        mockMvc.perform(post("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isOk())
                .andExpect(content().json("{valor: 2}"));
    }

    @Test
    void deberiaFallarAlCrearUnVehiculoConPlacaRepetida() throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conPlaca("ASD123").build();
        // act - assert
        mockMvc.perform(post("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad', 'mensaje': 'El vehiculo ya existe en el sistema'}"));
    }
    @Test
    void deberiaFallarAlCrearUnVehiculoEnUnEspacioOcupado()throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conIdEspacio(1L).build();
        // act - assert
        mockMvc.perform(post("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad', 'mensaje': 'El espacio ya esta ocupado'}"));

    }
    @Test
    void deberiaFallarAlCrearUnVehiculoEnUnEspacioQueNoExiste()throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conIdEspacio(14L).build();
        // act - assert
        mockMvc.perform(post("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionSinDatos', 'mensaje': 'El espacio no existe en el sistema'}"));

    }
    @Test
    @DisplayName("Deberia actualizar un vehiculo")
    void deberiaActualizarUnVehiculo() throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conId(1L).build();
        // act - assert
        mockMvc.perform(put("/vehiculo/{id}",vehiculo.getIdVehiculo())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isOk());
    }
    @Test
    void deberiaFallarAlActualizarUnVehiculoQueTOdaviaNoExiste()throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conId(12L).build();
        // act - assert
        mockMvc.perform(put("/vehiculo/{id}",vehiculo.getIdVehiculo())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad', 'mensaje': 'El vehiculo no existe en el sistema'}"));

    }

    @Test
    @DisplayName("Debe eliminar un vehiculo")
    void debeEliminarUnVehiculo() throws Exception{
        // arrange
        Long id = 1L;
        // act - assert
        mockMvc.perform(delete("/vehiculo/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
