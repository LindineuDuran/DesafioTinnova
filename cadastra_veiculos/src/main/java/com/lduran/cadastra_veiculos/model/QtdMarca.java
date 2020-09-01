package com.lduran.cadastra_veiculos.model;

public class QtdMarca
{
	private String marca;
	private long quantidade;

	/**
	 * @param marca
	 * @param quantidade
	 */
	public QtdMarca(String marca, long quantidade)
	{
		this.marca = marca;
		this.quantidade = quantidade;
	}

	/**
	 * @return the marca
	 */
	public String getMarca()
	{
		return this.marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca)
	{
		this.marca = marca;
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
