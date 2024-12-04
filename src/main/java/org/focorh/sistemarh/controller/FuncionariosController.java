package org.focorh.sistemarh.controller;

import java.util.List;
import java.util.Optional;
import org.focorh.sistemarh.model.Funcionarios;
import org.focorh.sistemarh.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FuncionariosController {
	
	@Autowired
	private FuncionariosRepository funcionariosRepository;
	
	@GetMapping
	public ResponseEntity <List<Funcionarios>> getAll(){
		return ResponseEntity.ok(funcionariosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Funcionarios> getById(@PathVariable Long id) {
		return funcionariosRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity <Funcionarios> post(@Valid @RequestBody Funcionarios funcionarios){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(funcionariosRepository.save(funcionarios));
	}
	
	@PutMapping
	public ResponseEntity <Funcionarios> put(@Valid @RequestBody Funcionarios funcionarios){
		return funcionariosRepository.findById(funcionarios.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK)
						.body(funcionariosRepository.save(funcionarios)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional <Funcionarios> funcionarios = funcionariosRepository.findById(id);
		
		if(funcionarios.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		funcionariosRepository.deleteById(id);
	}
	
}
