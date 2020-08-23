package com.lduran.cadastroveiculoscomfront.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lduran.cadastroveiculoscomfront.model.Veiculo;
import com.lduran.cadastroveiculoscomfront.model.Vendido;
import com.lduran.cadastroveiculoscomfront.repository.Veiculos;
import com.lduran.cadastroveiculoscomfront.repository.filter.VeiculoFilter;

@Service
public class VeiculoService
{
	@Autowired
	private Veiculos veiculos;

	public void salvar(Veiculo veiculo)
	{
		try
		{
			veiculos.save(veiculo);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long codigo)
	{
		veiculos.deleteById(codigo);
	}

	public String vender(Long codigo)
	{
		Optional<Veiculo> veiculo = veiculos.findById(codigo);
		veiculo.get().setVendido(Vendido.SIM);
		veiculos.save(veiculo.get());

		return Vendido.SIM.getDescricao();
	}

	public List<Veiculo> filtrar(VeiculoFilter filtro)
	{
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();

		return veiculos.findByDescricaoContaining(descricao);
	}
}
