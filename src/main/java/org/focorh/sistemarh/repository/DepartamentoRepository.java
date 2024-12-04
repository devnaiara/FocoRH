package org.focorh.sistemarh.repository;

import java.util.List;

import org.focorh.sistemarh.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

	   public List<Departamento> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);	
}
