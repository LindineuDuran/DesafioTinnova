package com.lduran.resultadoeleicao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Eleicao
{
	private int totalEleitores;
	private int votosValidos;
	private int votosBrancos;
	private int votosNulos;
	
	/**
	 * @param votosValidos
	 * @param votosBrancos
	 * @param votosNulo
	 */
	public Eleicao(int votosValidos, int votosBrancos, int votosNulo)
	{
		super();
		this.votosValidos = votosValidos;
		this.votosBrancos = votosBrancos;
		this.votosNulos = votosNulo;
		
		this.setTotalEleitores(this.votosValidos + this.votosBrancos + this.votosNulos);
	}
	
	/**
	 * Método base para cálculo de percentuais
	 * @param votos
	 * @param totalEleitores
	 * @return
	 */
	private double calcPercent(int votos, int totalEleitores)
	{
		return (double)votos / (double)totalEleitores;
	}
	
	/**
	 * Obtêm percentual de votos válidos
	 * @return
	 */
	public double getPercentValidos()
	{
		return calcPercent(this.votosValidos, this.getTotalEleitores());
	}
	
	/**
	 * Obtêm percentual de votos brancos
	 * @return
	 */
	public double getPercentBrancos()
	{
		return calcPercent(this.votosBrancos, this.getTotalEleitores());
	}
	
	/**
	 * Obtêm percentual de votos brancos
	 * @return
	 */
	public double getPercentNulos()
	{
		return calcPercent(this.votosNulos, this.getTotalEleitores());
	}

	public int getTotalEleitores()
	{
		return totalEleitores;
	}

	public void setTotalEleitores(int totalEleitores)
	{
		this.totalEleitores = totalEleitores;
	}
}
