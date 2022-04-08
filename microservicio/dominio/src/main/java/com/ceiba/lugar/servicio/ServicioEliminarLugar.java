package com.ceiba.lugar.servicio;

import com.ceiba.lugar.puerto.repository.RepositorioLugar;

public class ServicioEliminarLugar {

    private final RepositorioLugar repositorioLugar;

    public ServicioEliminarLugar(RepositorioLugar repositorioLugar){
        this.repositorioLugar = repositorioLugar;
    }

    public void ejecutar(Long id){ this.repositorioLugar.eliminarLugar(id);}
    
}
