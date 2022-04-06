package com.adn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adn.service.VehiculoService;

@RestController
@RequestMapping("/api/vehiculo")
@CrossOrigin("*")
public class VehiculoController {
	
	@Autowired
	VehiculoService vehiculoService;
}
