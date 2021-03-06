package com.ceiba.vehiculo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.vehiculo.controlador.ConsultaControladorVehiculo;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
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
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorVehiculo.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorVehiculoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DaoVehiculo daoVehiculo;

    private boolean encontrarVehiculos(){
        List<DtoVehiculo> listaVehiculo = daoVehiculo.listarTodosLosVehiculos();
        if (!listaVehiculo.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    @Test
    @DisplayName("Debe listar los vehiculos")
    void debeListarLosVehiculos() throws Exception{
        mockMvc.perform(get("/vehiculo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idVehiculo", is(1)))
                .andExpect(jsonPath("$[0].placa", is("AVC123")));

        assertTrue(encontrarVehiculos());
    }
}
