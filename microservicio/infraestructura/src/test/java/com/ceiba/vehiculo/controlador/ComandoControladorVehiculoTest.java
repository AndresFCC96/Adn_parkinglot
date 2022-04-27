package com.ceiba.vehiculo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import com.ceiba.vehiculo.servicio.testbuilder.ComandoVehiculoTestDataBuilder;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
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

    @Autowired
    private DaoVehiculo daoVehiculo;

    private boolean validarExistenciaVehiculo(Long id){
        for (DtoVehiculo vehiculo : daoVehiculo.listarTodosLosVehiculos()) {
            if (vehiculo.getIdVehiculo().equals(id)) {
                return true;
            }
        }
        return false;
    }
    private DtoVehiculo encontrarVehiculoPorId(Long id){
        DtoVehiculo vehiculo = daoVehiculo.buscarVehiculoPorId(id);
        if (vehiculo == null){
            return null;
        }else{
            return vehiculo;
        }
    }

    private DtoVehiculo encontrarVehiculoPorPlaca(String placa){
        List<DtoVehiculo> listaVehiculo = daoVehiculo.listaVehiculoPorPlaca(placa);
        if (!listaVehiculo.isEmpty()){
            return listaVehiculo.get(0);
        }else{
            return null;
        }
    }

    @Test
    @DisplayName("Deberia crear un vehiculo")
    void deberiaCrearUnVehiculo() throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conIdEspacio(2L).build();
        // act - assert
        mockMvc.perform(post("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isOk())
                .andExpect(content().json("{valor: 2}"));

        mockMvc.perform(get("/vehiculo")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        assertEquals(vehiculo.getLugarVehiculo(), encontrarVehiculoPorId(2L).getLugarVehiculo());
    }

    @Test
    void deberiaFallarAlCrearUnVehiculoConPlacaRepetida() throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conPlaca("AVC123").build();
        // act - assert
        mockMvc.perform(post("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad', 'mensaje': 'El vehiculo ya existe en el sistema'}"));

        //Se valida que la placa ya exista dentro del sistema
        assertEquals(vehiculo.getPlaca(), encontrarVehiculoPorPlaca(vehiculo.getPlaca()).getPlaca());
    }
    @Test
    void deberiaFallarAlCrearUnVehiculoEnUnEspacioOcupado()throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conId(1L).conIdEspacio(1L).build();
        // act - assert
        mockMvc.perform(post("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad', 'mensaje': 'El lugar ya esta ocupado'}"));
        //Se valida que el espacio ya este ocupado por un vehiculo
        assertEquals(vehiculo.getLugarVehiculo(), encontrarVehiculoPorId(vehiculo.getIdVehiculo()).getLugarVehiculo());
    }
    @Test
    void deberiaFallarAlCrearUnVehiculoEnUnEspacioQueNoExiste()throws Exception{
        Long idLugar = 10L;
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conIdEspacio(idLugar).build();
        // act - assert
        mockMvc.perform(post("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad', 'mensaje': 'El lugar no existe en el sistema'}"));


    }
    @Test
    @DisplayName("Deberia actualizar un vehiculo")
    void deberiaActualizarUnVehiculo() throws Exception{
        // arrange
        ComandoVehiculo vehiculo = new ComandoVehiculoTestDataBuilder().conId(1L).conIdEspacio(1L).conPlaca("ACF512").build();
        // act - assert
        //Validar existencia del vehiculo antes de actualizar
        assertEquals(vehiculo.getIdVehiculo(), encontrarVehiculoPorId(vehiculo.getIdVehiculo()).getIdVehiculo());
        //Se procede al put
        mockMvc.perform(put("/vehiculo/{id}",vehiculo.getIdVehiculo())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehiculo)))
                .andExpect(status().isOk());

        assertEquals(vehiculo.getIdVehiculo(), encontrarVehiculoPorId(vehiculo.getIdVehiculo()).getIdVehiculo());
        assertEquals(vehiculo.getLugarVehiculo(), encontrarVehiculoPorId(vehiculo.getIdVehiculo()).getLugarVehiculo());
        assertEquals(vehiculo.getPlaca(), encontrarVehiculoPorId(vehiculo.getIdVehiculo()).getPlaca());
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
        //Se valida que no exista un vehiculo con ese Id
        assertEquals(false, validarExistenciaVehiculo(12L));

    }

    @Test
    @DisplayName("Debe eliminar un vehiculo")
    void debeEliminarUnVehiculo() throws Exception{
        // arrange
        Long id = 1L;
        // act - assert
        //Validar que el vehiculo exista
        assertEquals(id, encontrarVehiculoPorId(id).getIdVehiculo());
        mockMvc.perform(delete("/vehiculo/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        assertFalse(validarExistenciaVehiculo(1L));
    }
}
