package com.ceiba.lugar.adaptador.dao;


import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.lugar.modelo.dto.DtoLugar;
import com.ceiba.lugar.puerto.dao.DaoLugar;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoLugarPostgres implements DaoLugar {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "lugar", value = "listar")
    private static String sqlListar;

    @SqlStatement(namespace = "lugar", value = "listarTodo")
    private static String sqlListarTodo;

    @SqlStatement(namespace = "lugar", value = "buscarId")
    private static String sqlBuscarId;
    public DaoLugarPostgres(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public List<DtoLugar> listarTodosLosLugaresPorEstado(String estado) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("estado", estado);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, paramSource,new MapeoLugar());
    }

    @Override
    public List<DtoLugar> listarTodosLosLugares() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarTodo,new MapeoLugar());
    }

    @Override
    public DtoLugar buscarEspacioPorId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBuscarId, paramSource, new MapeoLugar()).stream().findFirst().get();
    }
}
