update vehiculo
set placa = :placa,
    id_espacio = :idEspacio,
    tipo_vehiculo =:tipoVehiculo,
    fecha_entrada = :fechaEntrada,
    fecha_salida = :fechaSalida,
    total_pagar = :totalPagar
where id = :id