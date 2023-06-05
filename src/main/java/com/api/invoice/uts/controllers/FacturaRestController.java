package com.api.invoice.uts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.invoice.uts.models.entities.Factura;
import com.api.invoice.uts.models.entities.Producto;
import com.api.invoice.uts.services.IclienteServices;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {

	@Autowired
	private IclienteServices clienteservices;
	
	@GetMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Factura show(@PathVariable Long id){
	return clienteservices.findFacturaById(id);
	}
	
	@GetMapping("/facturas")
	@ResponseStatus(HttpStatus.OK)
	public List<Factura> index(){
		return clienteservices.findFacturaAll();	
	}
	
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		clienteservices.deleteFacturaById(id);
	}
	
	@GetMapping("/facturas/filtrar-productos/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Producto> filtrarProductos(@PathVariable String term){
	return clienteservices.findProductoByNombre(term);
	}
	
	@PostMapping("/facturas")
	@ResponseStatus(HttpStatus.CREATED)
	public Factura create(@RequestBody Factura factura){
		return clienteservices.saveFactura(factura);
	}
	
	
	
}
