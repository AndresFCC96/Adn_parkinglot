package com.ceiba.lugar.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.lugar.modelo.dto.DtoLugar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoLugar implements RowMapper<DtoLugar>, MapperResult {

    @Override
    public DtoLugar mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long idLugar = rs.getLong("id_lugar");
        String estado = rs.getString("estado");

        return new DtoLugar(idLugar, estado);
    }
}
