package com.ceiba.lugar.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.lugar.modelo.entidad.Lugar;
import com.ceiba.lugar.puerto.repository.RepositorioLugar;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioLugarPostgres implements RepositorioLugar {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "lugar", value = "crear")
    private static String sqlCrearLugar;

    @SqlStatement(namespace = "lugar", value = "actualizar")
    private static String sqlActualizarLugar;

    @SqlStatement(namespace = "lugar", value = "eliminar")
    private static String sqlEliminarLugar;

    @SqlStatement(namespace = "lugar", value = "existePorId")
    private static String sqlExistePorIdLugar;

    @SqlStatement(namespace = "lugar", value = "actualizarEstado")
    private static String sqlActualizarEstadoLugar;

    public RepositorioLugarPostgres(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate){
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }
    
    @Override
    public Long crearLugar(Lugar lugar) {
        return this.customNamedParameterJdbcTemplate.crear(lugar, sqlCrearLugar);
    }

    @Override
    public void actualizarLugar(Lugar lugar) {
        this.customNamedParameterJdbcTemplate.actualizar(lugar, sqlActualizarLugar);
    }

    @Override
    public void actualizarLugarEstado(Long id, String estado) {
        Lugar lugar = new Lugar(id, estado);

        this.customNamedParameterJdbcTemplate.actualizar(lugar, sqlActualizarEstadoLugar);
    }

    @Override
    public void eliminarLugar(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_lugar", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarLugar, parameterSource);
    }

    @Override
    public boolean existeLugarPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_lugar" ,id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorIdLugar, parameterSource, Boolean.class);
    }
}
