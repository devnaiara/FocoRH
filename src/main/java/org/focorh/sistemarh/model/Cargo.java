package org.focorh.sistemarh.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_cargo")
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 3, max = 100, message = "O atributo nome dve possuir pelo menos 3 caracteres e no máximo 100!")
	private String nome;
	
	private String descricao;
	
	@OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("cargo")
	private List<Funcionarios> funcionarios;
	
	private String nivel;

	@NotNull(message = "O atributo salário base é obrigatório!")
	@DecimalMin(value = "0.0", inclusive = false, message = "O salário base deve ser maior que 0.")
	@Digits(integer = 10, fraction = 2, message = "O salário base deve ter no máximo 10 dígitos inteiros e 2 decimais.")
	private BigDecimal salario_base;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public BigDecimal getSalario_base() {
		return salario_base;
	}

	public void setSalario_base(BigDecimal salario_base) {
		this.salario_base = salario_base;
	}

	public List<Funcionarios> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionarios> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
}
