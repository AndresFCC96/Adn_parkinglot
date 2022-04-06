package com.adn.dto;

import java.io.Serializable;
import java.util.Date;

import com.adn.dominio.Vehiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReciboDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id_recibo;
	private Date fecha_entrada;
	private Date fecha_salida;
	private int valor_parqueo;
	private Vehiculo vehiculo_id_vehiculo;
	
}
