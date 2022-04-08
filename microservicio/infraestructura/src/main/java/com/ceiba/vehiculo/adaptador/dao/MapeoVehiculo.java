package com.ceiba.vehiculo.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.vehiculo.modelo.dto.DtoVehiculo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoVehiculo implements RowMapper<DtoVehiculo>, MapperResult {

    @Override
    public DtoVehiculo mapRow(ResultSet rs, int rowNum) throws SQLException{
        Long idVehiculo = rs.getLong("id_vehiculo");
        String placa = rs.getString("placa");
        String tipo = rs.getString("tipo");
        Long lugarVehiculo = rs.getLong("lugar_vehiculo");
        LocalDateTime fechaEntrada = extraerLocalDateTime(rs, "fecha_entrada");
        LocalDateTime fechaSalida = extraerLocalDateTime(rs, "fecha_salida");
        Double valorParqueo = rs.getDouble("valor_parqueo");
        Double valorBase = rs.getDouble("valor_base");

        return new DtoVehiculo(idVehiculo, placa, tipo, lugarVehiculo,  fechaEntrada, fechaSalida,
                valorParqueo, valorBase);

    }
}
