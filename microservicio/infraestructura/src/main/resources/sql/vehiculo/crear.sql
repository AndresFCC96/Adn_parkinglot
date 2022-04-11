insert into vehiculo (
            placa,
            id_espacio,
            tipo_vehiculo,
            fecha_entrada,
            fecha_salida,
            total_pagar)
values (:placa,
        :idEspacio,
        :tipoVehiculo,
        :fechaEntrada,
        :fechaSalida,
        :totalPagar);

