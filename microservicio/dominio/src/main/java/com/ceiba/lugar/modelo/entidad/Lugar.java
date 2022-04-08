package com.ceiba.lugar.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Lugar {

    private static final String SE_DEBE_INGRESAR_EL_ESTADO = "Se debe ingresar estado";

    private Long idLugar;
    private String estado;

    public Lugar(Long idLugar, String estado) {

        validarObligatorio(estado, SE_DEBE_INGRESAR_EL_ESTADO);

        this.idLugar = idLugar;
        this.estado = estado;
    }


}
