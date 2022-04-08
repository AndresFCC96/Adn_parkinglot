package com.ceiba.configuracion;

import com.ceiba.lugar.puerto.repository.RepositorioLugar;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.vehiculo.servicio.ServicioActualizarVehiculo;
import com.ceiba.vehiculo.servicio.ServicioCrearVehiculo;
import com.ceiba.vehiculo.servicio.ServicioEliminarVehiculo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearVehiculo servicioCrearVehiculo(RepositorioVehiculo repositorioVehiculo,
                                                       RepositorioLugar repositorioLugar) {
        return new ServicioCrearVehiculo(repositorioVehiculo, repositorioLugar);
    }

    @Bean
    public ServicioEliminarVehiculo servicioEliminarVehiculo(RepositorioVehiculo repositorioVehiculo) {
        return new ServicioEliminarVehiculo(repositorioVehiculo);
    }

    @Bean
    public ServicioActualizarVehiculo servicioActualizarVehiculo(RepositorioVehiculo repositorioVehiculo) {
        return new ServicioActualizarVehiculo(repositorioVehiculo);
    }
	

}
