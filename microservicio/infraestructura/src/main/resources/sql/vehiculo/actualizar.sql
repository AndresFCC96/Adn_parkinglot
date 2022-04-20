update vehiculo
set placa = :placa,
    lugar_vehiculo = :lugarVehiculo,
    tipo =:tipo,
    fecha_entrada = :fechaEntrada,
    fecha_salida = :fechaSalida,
    valor_parqueo = :valorParqueo
where id_vehiculo = :idVehiculo