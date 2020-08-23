package com.lduran.cadastroveiculoscomfront.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lduran.cadastroveiculoscomfront.model.Veiculo;

public interface Veiculos extends JpaRepository<Veiculo, Long>
{
	public List<Veiculo> findByDescricaoContaining(String descricao);
}