package com.ceiba.vehiculo.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoVehiculoPostgres implements DaoVehiculo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "vehiculo", value = "listar")
    private static String sqllistarTodosLosVehiculos;

    @SqlStatement(namespace = "vehiculo", value = "buscarId")
    private static String sqlBuscarVehiculoPorId;

    @SqlStatement(namespace = "vehiculo", value = "buscarPorPlaca")
    private static String sqlBuscarPorPlaca;

    @SqlStatement(namespace = "vehiculo", value = "buscarPorIdEspacio")
    private static String sqlBuscarIdEspacio;

    public DaoVehiculoPostgres(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoVehiculo> listarTodosLosVehiculos() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqllistarTodosLosVehiculos, new MapeoVehiculo());
    }

    @Override
    public DtoVehiculo buscarVehiculoPorId(Long idVehiculo) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_vehiculo", idVehiculo);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBuscarVehiculoPorId, paramSource, new MapeoVehiculo()).stream().findFirst().get();
    }

    @Override
    public List<DtoVehiculo> listaVehiculoPorPlaca(String placa) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("placa", placa);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBuscarPorPlaca, paramSource, new MapeoVehiculo());
    }

    @Override
    public DtoVehiculo buscarVehiculoEnUnEspacioEspecifico(Long lugarVehiculo) {
       MapSqlParameterSource parameterSource = new MapSqlParameterSource();
       parameterSource.addValue("lugar_vehiculo", lugarVehiculo);
       return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBuscarIdEspacio, parameterSource, new MapeoVehiculo()).stream().findFirst().get();
    }

}
