package com.ceiba.lugar.comando;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComandoLugar {
    private Long idLugar;
    private String estado;
}
