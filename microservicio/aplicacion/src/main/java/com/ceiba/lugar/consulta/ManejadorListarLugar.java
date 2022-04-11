package com.ceiba.lugar.consulta;

import com.ceiba.lugar.modelo.dto.DtoLugar;
import com.ceiba.lugar.puerto.dao.DaoLugar;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarLugar {

    private final DaoLugar daoLugar;

    public ManejadorListarLugar(DaoLugar daoLugar){ this.daoLugar = daoLugar;}

    public List<DtoLugar> ejecutar(String estado){ return this.daoLugar.listarTodosLosLugaresPorEstado(estado);}
}
