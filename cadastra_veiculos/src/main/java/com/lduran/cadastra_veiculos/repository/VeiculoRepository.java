package com.lduran.cadastra_veiculos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lduran.cadastra_veiculos.model.QtdAno;
import com.lduran.cadastra_veiculos.model.QtdMarca;
import com.lduran.cadastra_veiculos.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>
{
	public List<Veiculo> findByDescricaoContaining(String descricao);

	@Query("select new com.lduran.cadastra_veiculos.model.QtdMarca(marca, count(marca)) from Veiculo group by marca")
	public List<QtdMarca> distribPorMarca();

	//	@Query(value = "select (substr(ano,1,3)||'0') as ano, count(ano) as quantidade from veiculo group by substr(ano,1,3)||'0'",
	//			nativeQuery = true)
	//	public List<QtdAno> distribPorAno();

	@Query(value = "select new com.lduran.cadastra_veiculos.model.QtdAno((substr(ano,1,3)||'0'), count(ano)) from Veiculo group by substr(ano,1,3)||'0'")
	public List<QtdAno> distribPorAno();
}