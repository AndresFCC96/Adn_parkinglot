package com.ceiba.lugar.modelo.dto;

//import lombok.AllArgsConstructor;
//import lombok.Getter;

public class DtoLugar {
    private Long idLugar;
    private String estado;

    public Long getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(Long idLugar) {
        this.idLugar = idLugar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DtoLugar(Long idLugar, String estado) {
        this.idLugar = idLugar;
        this.estado = estado;
    }
}
