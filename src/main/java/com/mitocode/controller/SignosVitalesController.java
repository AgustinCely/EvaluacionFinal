package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.model.SignosVitales;
import com.mitocode.service.ISignosVitalesService;

@RestController
@RequestMapping("/signosvitales")
public class SignosVitalesController {

	@Autowired
	private ISignosVitalesService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SignosVitales>> list(){
		List<SignosVitales> signos = new ArrayList<>();
		signos = service.listar();
		return new ResponseEntity<List<SignosVitales>>(signos,HttpStatus.OK);
	}
	
	@GetMapping(value="/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<SignosVitales>> listarPageable(Pageable pageable){
		Page<SignosVitales> signosVitales;
		signosVitales = service.listarPageable(pageable);
		return new ResponseEntity<Page<SignosVitales>>(signosVitales, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<SignosVitales> listarId(@PathVariable("id") Integer id) {
		SignosVitales sig = service.listarId(id);
		if (sig == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		Resource<SignosVitales> resource = new Resource<SignosVitales>(sig);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));
		resource.add(linkTo.withRel("signosvitales-resource"));
		
		return resource;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody SignosVitales signosVitales){
		SignosVitales pac = new SignosVitales();
		pac = service.registrar(signosVitales);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdsignosvitales()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody SignosVitales signosVitales) {		
		service.modificar(signosVitales);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		SignosVitales sig = service.listarId(id);
		if (sig == null) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			service.eliminar(id);
		}
	}
}
