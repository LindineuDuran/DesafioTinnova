package com.lduran.cadastra_veiculos.model;

public class QtdAno
{
	private String ano;
	private long quantidade;


	/**
	 * 
	 */
	public QtdAno()
	{
	}

	/**
	 * @param marca
	 * @param quantidade
	 */
	public QtdAno(String ano, long quantidade)
	{
		this.ano = ano;
		this.quantidade = quantidade;
	}

	/**
	 * @return the marca
	 */
	public String getAno()
	{
		return this.ano;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setAno(String ano)
	{
		this.ano = ano;
	}

	/**
	 * @return the quantidade
	 */
	public long getQuantidade()
	{
		return this.quantidade;
	}

	/**
	 * @param quantidade the quantidade to set
	 */
	public void setQuantidade(long quantidade)
	{
		this.quantidade = quantidade;
	}
}
