package com.ceiba.lugar.consulta;

import com.ceiba.lugar.modelo.dto.DtoLugar;
import com.ceiba.lugar.puerto.dao.DaoLugar;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ManejadorListarTodosLugar {
    private DaoLugar daoLugar;

    public ManejadorListarTodosLugar(DaoLugar daoLugar) {
        this.daoLugar = daoLugar;
    }

    public List<DtoLugar> ejecutar(){return this.daoLugar.listarTodosLosLugares();}
}
