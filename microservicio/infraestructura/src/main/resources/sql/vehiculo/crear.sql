insert into vehiculo (
            placa,
            lugar_vehiculo,
            tipo,
            fecha_entrada,
            fecha_salida,
            total_pagar)
values (:placa,
        :lugar_vehiculo,
        :tipo,
        :fechaEntrada,
        :fechaSalida,
        :totalPagar);

