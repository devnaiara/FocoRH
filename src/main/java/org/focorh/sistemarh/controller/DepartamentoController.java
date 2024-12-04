package org.focorh.sistemarh.controller;

import org.focorh.sistemarh.model.Departamento;
import org.focorh.sistemarh.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@GetMapping
	public ResponseEntity<List<Departamento>> getAllDepartamento(){
		return ResponseEntity.ok(departamentoRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Departamento> postDepartamento(@RequestBody @Valid Departamento departamento){
		return ResponseEntity.status(HttpStatus.CREATED).body(departamentoRepository.save(departamento));
	} 
	
	@GetMapping("/{id}")
    public ResponseEntity<Departamento> getById(@PathVariable Long id){
        return departamentoRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
	
	@GetMapping("/nome/{nome}")
    public ResponseEntity<List<Departamento>> getByNome(@PathVariable 
    String nome){
        return ResponseEntity.ok(departamentoRepository
            .findAllByNomeContainingIgnoreCase(nome));
    }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Departamento> departamento = departamentoRepository.findById(id);
        
        if(departamento.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        departamentoRepository.deleteById(id);              
    }
	
	 @PutMapping
	    public ResponseEntity<Departamento> put(@Valid @RequestBody Departamento departamento){
	        return departamentoRepository.findById(departamento.getId())
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
	            .body(departamentoRepository.save(departamento)))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
}
