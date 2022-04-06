package com.adn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adn.service.ReciboService;

@RestController
@RequestMapping("/api/recibo")
@CrossOrigin("*")
public class ReciboController {
	
	@Autowired
	ReciboService reciboService;
}
