package com.ceiba.lugar.consulta;

import com.ceiba.lugar.modelo.dto.DtoLugar;
import com.ceiba.lugar.puerto.dao.DaoLugar;
import org.springframework.stereotype.Component;

@Component
public class ManejadorBuscarPorIdLugar {
    private final DaoLugar daoLugar;

    public ManejadorBuscarPorIdLugar(DaoLugar daoLugar) {
        this.daoLugar = daoLugar;
    }
    public DtoLugar ejecutar(Long id){ return this.daoLugar.buscarEspacioPorId(id);}
}
