package com.ceiba.lugar.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.lugar.modelo.dto.DtoLugar;
import com.ceiba.lugar.puerto.dao.DaoLugar;
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
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorLugar.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoConsultaLugarTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DaoLugar daoLugar;

    private boolean encontrarLugares(){
        List<DtoLugar> listaLugar = daoLugar.listarTodosLosLugares();
        if (listaLugar.isEmpty()){
            return false;
        }else{
            return true;
        }
    };
    private boolean encontrarLugaresPorEstado(String estado){
        List<DtoLugar> listaLugar = daoLugar.listarTodosLosLugaresPorEstado(estado);
        if (listaLugar.isEmpty()){
            return false;
        }else{
            return true;
        }
    };
    @Test
    @DisplayName("Deberia listar lugares")
    void deberiListarLugares() throws Exception{
        mockMvc.perform(get("/lugar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertTrue(encontrarLugares());
    }
    @Test
    @DisplayName("Deberia listar lugares disponibles")
    void deberiListarLugaresPorEstado() throws Exception{
        mockMvc.perform(get("/lugar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertTrue(encontrarLugares());
    }
}
