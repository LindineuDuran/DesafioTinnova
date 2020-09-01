package com.lduran.cadastra_veiculos.service;

import java.util.List;
import java.util.Map;
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

	public String salvar(Veiculo veiculo)
	{
		try
		{
			this.rep.save(veiculo);

			return "Veículo salvo com sucesso!";
		}
		catch (DataIntegrityViolationException e)
		{
			return "Formato de data inválido";
		}
	}

	public ResponseEntity<Veiculo> atualizar(long id, Veiculo veiculo)
	{
		return this.rep.findById(id).map(record ->
		{
			record.setChapa(veiculo.getChapa());
			record.setMarca(veiculo.getMarca());
			record.setAno(veiculo.getAno());
			record.setDescricao(veiculo.getDescricao());

			Veiculo updated = this.rep.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	public void excluir(long id)
	{
		this.rep.deleteById(id);
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
		return this.rep.distribPorMarca();
	}

	public List<QtdAno> filtrarQtdAno()
	{
		Map<Integer, Long> map = this.rep.findAll().stream().collect(Collectors.groupingBy(Veiculo::getDecada, Collectors.counting()));
		List<QtdAno> lista = map.entrySet().stream().map(e -> new QtdAno(Integer.toString(e.getKey()), e.getValue())).collect(Collectors.toList()); 

		return lista;
	}
}
