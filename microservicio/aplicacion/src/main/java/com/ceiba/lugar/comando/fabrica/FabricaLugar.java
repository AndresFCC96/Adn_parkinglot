package com.ceiba.lugar.comando.fabrica;

import com.ceiba.lugar.comando.ComandoLugar;
import com.ceiba.lugar.modelo.entidad.Lugar;
import org.springframework.stereotype.Component;

@Component
public class FabricaLugar {
    public Lugar crear(ComandoLugar comandoLugar){
        return new Lugar(
                comandoLugar.getIdLugar(),
                comandoLugar.getEstado()
        );
    }
}
