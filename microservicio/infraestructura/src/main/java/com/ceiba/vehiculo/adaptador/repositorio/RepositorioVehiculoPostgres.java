package com.ceiba.vehiculo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioVehiculoPostgres implements RepositorioVehiculo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="vehiculo", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="vehiculo", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="vehiculo", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="vehiculo", value="existe")
    private static String sqlExiste;

    @SqlStatement(namespace="vehiculo", value="existePorId")
    private static String sqlExistePorId;

    @SqlStatement(namespace = "vehiculo", value = "existePorIdEspacio")
    private static String sqlExiteporIdEspacio;

    public RepositorioVehiculoPostgres(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public Long crearVehiculo(Vehiculo vehiculo) {
        return this.customNamedParameterJdbcTemplate.crear(vehiculo, sqlCrear);
    }

    @Override
    public void actualizarVehiculo(Vehiculo vehiculo) {
        this.customNamedParameterJdbcTemplate.actualizar(vehiculo, sqlActualizar);
    }

    @Override
    public void eliminarVehiculo(Long id){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_vehiculo", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, parameterSource);
    }

    @Override
    public boolean existeVehiculoConPlaca(String placa) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("placa", placa);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public boolean existeVehiculoConId(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id_vehiculo", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePorId,paramSource, Boolean.class);
    }

    @Override
    public boolean existeVehiculoEnUnLugarConId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("lugar_vehiculo", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiteporIdEspacio, parameterSource, Boolean.class);

    }
}
