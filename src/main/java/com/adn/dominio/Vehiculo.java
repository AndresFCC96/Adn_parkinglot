package com.adn.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_vehiculo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_vehiculo;
	
	@Column(name = "tipo", length = 7)
	private String placa;
	
	@Column(name = "tipo", length = 1)
	private String tipo;
}
