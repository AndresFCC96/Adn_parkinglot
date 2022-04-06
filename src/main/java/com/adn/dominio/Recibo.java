package com.adn.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recibo")
public class Recibo implements Serializable{

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
