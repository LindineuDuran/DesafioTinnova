package com.lduran.cadastroveiculoscomfront.model;

public enum Vendido
{
	SIM("Sim"),
	NAO("Não");

	private String descricao;

	private Vendido(String descricao)
	{
		this.descricao = descricao;
	}

	public String getDescricao()
	{
		return descricao;
	}
}