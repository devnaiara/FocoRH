package org.focorh.sistemarh.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_funcionarios")
public class Funcionarios {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@ManyToOne
    @JsonIgnoreProperties("funcionarios")
	private Cargo cargo;
	
	@ManyToOne
    @JsonIgnoreProperties("funcionarios")
	private Departamento departamento;
	
	@UpdateTimestamp
	private LocalDateTime data_admissao;
	
	private LocalDateTime data_demissao;
	
	@NotBlank
	private String tipo_contrato;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public LocalDateTime getData_admissao() {
		return data_admissao;
	}

	public void setData_admissao(LocalDateTime data_admissao) {
		this.data_admissao = data_admissao;
	}

	public LocalDateTime getData_demissao() {
		return data_demissao;
	}

	public void setData_demissao(LocalDateTime data_demissao) {
		this.data_demissao = data_demissao;
	}

	public String getTipo_contrato() {
		return tipo_contrato;
	}

	public void setTipo_contrato(String tipo_contrato) {
		this.tipo_contrato = tipo_contrato;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}