package com.lduran.cadastra_veiculos.model;

public enum Vendido
{
	NAO("Não"),
	SIM("Sim");

	private String descricao;

	private Vendido(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return this.descricao;
	}
}