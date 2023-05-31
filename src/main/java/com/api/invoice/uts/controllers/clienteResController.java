package com.api.invoice.uts.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.invoice.uts.models.entities.Region;
import com.api.invoice.uts.models.entities.cliente;
import com.api.invoice.uts.services.IclienteServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class clienteResController {

	@Autowired
	private IclienteServices clienteservices;
	
	@GetMapping("/clientes")
	public List<cliente> index(){
		return clienteservices.findAll();	
	}
	
	@GetMapping("/clientes/{id}")
	public cliente show(@PathVariable Long id){
	return clienteservices.findById(id);
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<?> create(@Valid @RequestBody cliente Cliente,BindingResult result){
		
		cliente clienteNew=null;
		
		Map<String,Object> response=new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors=result.getFieldErrors()
					            .stream()
					            .map(err->"el campo"+err.getField()+ " "+ err.getDefaultMessage())
					            .collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			clienteNew = clienteservices.save(Cliente);
		}
		catch(DataAccessException e){
			response.put("mensaje","error al realizar el inserto en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","el cliente a sido creado con exito");
		response.put("cliente", clienteNew );
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody cliente Cliente,BindingResult result,@PathVariable Long id){
	    cliente currentCliente=this.clienteservices.findById(id);
	    cliente updateCliente=null;
	    Map<String, Object> response=new HashMap<>();
	    if(result.hasErrors()) {
	        List<String> errors= result.getFieldErrors()
	                .stream()
	                .map(err -> "El campo " +err.getField() +" "+err.getDefaultMessage())
	                .collect(Collectors.toList());
	        response.put("errors",errors);
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
	    }
	    if(currentCliente==null){
	        response.put("mensaje", "Error: no se puede editar, el cliente ID: ".concat(id.toString())
	                .concat(" no existe en la base de datos"));
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	    }

	    try{
	        currentCliente.setNombre(Cliente.getNombre());
	        currentCliente.setApellidos(Cliente.getApellidos());
	        currentCliente.setEmail(Cliente.getEmail());
	        updateCliente=this.clienteservices.save(currentCliente);
	    }catch(DataAccessException e){
	        response.put("mensaje", "Error al actulizar en la base de datos");
	        response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    response.put("mensaje","El cliente ha sido actulizado con éxito!");
	    response.put("cliente", updateCliente);
	    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
		
	@DeleteMapping("/clientesd/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)		
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		Map<String, Object> response=new HashMap<>();	
		try {
		cliente Cliente=this.clienteservices.findById(id);
		 this.clienteservices.delet(Cliente);
		}
		catch(DataAccessException e){
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			 response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage())); 
			 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR) ; 
			 }
		response.put("mensaje", "El cliente eliminado con éxito");
	 return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	
	}
	
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteservices.findAllRegiones();
	}
	
}

