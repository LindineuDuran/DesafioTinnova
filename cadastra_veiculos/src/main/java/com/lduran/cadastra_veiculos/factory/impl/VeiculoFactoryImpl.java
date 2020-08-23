package com.lduran.cadastra_veiculos.factory.impl;

import com.lduran.cadastra_veiculos.factory.VeiculoFactory;
import com.lduran.cadastra_veiculos.model.Veiculo;

/**
 * Factory class for the Vehicle entity.
 * 
 * @author Lindineu Duran
 * @since 23/08/2020
 */
public class VeiculoFactoryImpl implements VeiculoFactory
{
	@Override
	public Veiculo createVeiculo()
	{
		return new Veiculo();
	}
}
