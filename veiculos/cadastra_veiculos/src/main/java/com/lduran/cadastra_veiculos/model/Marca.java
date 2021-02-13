package com.lduran.cadastra_veiculos.model;

public enum Marca
{
	CHEVROLET("Chevrolet"),
	FORD("Ford"),
	HONDA("Honda"),
	VOLKSWAGEN("VolksWagen");

	private String descricao;

	private Marca(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return this.descricao;
	}
}
