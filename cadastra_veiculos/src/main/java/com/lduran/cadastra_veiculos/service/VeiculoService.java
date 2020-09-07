package com.lduran.cadastra_veiculos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lduran.cadastra_veiculos.model.QtdAno;
import com.lduran.cadastra_veiculos.model.QtdMarca;
import com.lduran.cadastra_veiculos.model.Veiculo;
import com.lduran.cadastra_veiculos.repository.VeiculoRepository;
import com.lduran.cadastra_veiculos.repository.filter.VeiculoFilter;

/**
 * Service that implements methods related to a transaction.
 *
 * @author Lindineu Duran
 * @since 23/08/2020
 */
@Service
public class VeiculoService
{
	@Autowired
	private VeiculoRepository rep;

	public ResponseEntity<Veiculo> salvar(Veiculo veiculo)
	{
		try
		{
			Veiculo salvo = this.rep.save(veiculo);

			return ResponseEntity.ok().body(salvo);
		}
		catch (DataIntegrityViolationException e)
		{
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<Veiculo> atualizar(long id, Veiculo veiculo)
	{
		return this.rep.findById(id).map(record ->
		{
			record.setChapa(veiculo.getChapa());
			record.setMarca(veiculo.getMarca());
			record.setAno(veiculo.getAno());
			record.setVendido(veiculo.isVendido());
			record.setDescricao(veiculo.getDescricao());
			record.setUpdated(veiculo.getUpdated());

			Veiculo atualizado = this.rep.save(record);
			return ResponseEntity.ok().body(atualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	public void excluir(long id)
	{
		this.rep.deleteById(id);
	}

	public void excluirTodos()
	{
		this.rep.deleteAll();
	}

	public List<Veiculo> listarTodos()
	{
		return this.rep.findAll();
	}

	public ResponseEntity<Veiculo> buscaVeiculo(long id)
	{
		return this.rep.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	public List<Veiculo> filtrar(VeiculoFilter filtro)
	{
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();

		return this.rep.findByDescricaoContaining(descricao);
	}

	public List<QtdMarca> filtrarQtdMarca()
	{
		return this.rep.findAll().stream().collect(Collectors.groupingBy(Veiculo::getMarca, Collectors.counting()))
				.entrySet().stream().map(e -> new QtdMarca(e.getKey(), e.getValue())).collect(Collectors.toList());
	}

	public List<QtdAno> filtrarQtdAno()
	{
		List<QtdAno> lista = this.rep.findAll().stream()
				.collect(Collectors.groupingBy(Veiculo::getDecada, Collectors.counting())).entrySet().stream()
				.map(e -> new QtdAno(e.getKey(), e.getValue())).collect(Collectors.toList());

		return lista;
	}

	public List<Veiculo> filtrarNaoVendido()
	{
		return this.rep.findByVendido(false);
	}
}
