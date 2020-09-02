package com.lduran.cadastra_veiculos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lduran.cadastra_veiculos.model.QtdMarca;
import com.lduran.cadastra_veiculos.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>
{
	public List<Veiculo> findByDescricaoContaining(String descricao);

	@Query("select new com.lduran.cadastra_veiculos.model.QtdMarca(marca, count(marca)) from Veiculo group by marca")
	public List<QtdMarca> distribPorMarca();
}