package com.lduran.cadastra_veiculos.factory;

import com.lduran.cadastra_veiculos.model.Veiculo;

/**
 * Interface that provides method for manipulate a vehicle.
 * 
 * @author Lindineu Duran
 * @since 23/08/2020
 */
public interface VeiculoFactory
{
	Veiculo createVeiculo();
}
