package org.focorh.sistemarh.repository;

import java.util.List;

import org.focorh.sistemarh.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long>{
	
	List<Cargo> findAllByNomeContainingIgnoreCase(String nome);
	
}
