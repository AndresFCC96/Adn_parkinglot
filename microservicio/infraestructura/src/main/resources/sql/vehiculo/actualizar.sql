update vehiculo
set placa = :placa,
    lugar_vehiculo = :idEspacio,
    tipo =:tipo,
    fecha_entrada = :fechaEntrada,
    fecha_salida = :fechaSalida,
    total_pagar = :totalPagar
where id_vehiculo = :id_vehiculo