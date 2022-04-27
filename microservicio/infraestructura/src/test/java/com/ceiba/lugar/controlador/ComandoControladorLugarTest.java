package com.ceiba.lugar.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.lugar.comando.ComandoLugar;
import com.ceiba.lugar.modelo.dto.DtoLugar;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.puerto.dao.DaoLugar;
import com.ceiba.lugar.servicio.testbuilder.ComandoLugarTestDataBuilder;
import com.ceiba.vehiculo.controlador.ComandoControladorVehiculo;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorLugar.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorLugarTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DaoLugar daoLugar;

    private boolean encontrarLugarPorId(Long id){
        for (DtoLugar lugar : daoLugar.listarTodosLosLugares()){
            if(lugar.getIdLugar() == id){
                return true;
            }
        }
        return false;
    };
    private DtoLugar retornarLugar(Long id){
        DtoLugar lugar = daoLugar.buscarEspacioPorId(id);
        if (lugar != null){
            return lugar;
        }else{
            return null;
        }
    };
    @Test
    @DisplayName("Deberias crear un lugar")
    void deberiaCrearUnLugar() throws Exception{
        //  arrange
        ComandoLugar lugar = new ComandoLugarTestDataBuilder().conId(3L).build();
        // act - assert
        //Se valida que no exista el lugar
        assertEquals(false, encontrarLugarPorId(lugar.getIdLugar()));
        mockMvc.perform(post("/lugar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lugar)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 3}"));
        //Se valida que se haya creado
        assertEquals(true, encontrarLugarPorId(lugar.getIdLugar()));
    }
    @Test
    @DisplayName("Deberia actualizar un lugar")
    void deberiaActualizarUnEspacio() throws Exception{
        // arrange
        Long id = 1L;
        ComandoLugar lugar = new ComandoLugarTestDataBuilder().conId(id).build();
        // act - assert
        //Consultamos que no exista el lugar
        assertEquals(true, encontrarLugarPorId(lugar.getIdLugar()));
        mockMvc.perform(put("/lugar/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lugar)))
                .andExpect(status().isOk());

        assertEquals(lugar.getIdLugar(),retornarLugar(id).getIdLugar());
    }
    @Test
    void deberiaFallarAlActualizarUnEspacioQueNoExiste() throws Exception{
        Long id = 23L;
        ComandoLugar lugar = new ComandoLugarTestDataBuilder().build();
        // act - assert
        mockMvc.perform(put("/lugar/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lugar)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'nombreExcepcion':'ExcepcionDuplicidad', 'mensaje': 'El lugar no existe en el sistema'}"));
        assertEquals(false, encontrarLugarPorId(id));
    }
    @Test
    @DisplayName("Debe eliminar un lugar")
    void debeEliminarUnEspacio() throws Exception{
        // arrange
        Long id = 2L;
        // act - assert
        mockMvc.perform(delete("/lugar/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/lugar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
        assertEquals(false, encontrarLugarPorId(id));
    }

}
