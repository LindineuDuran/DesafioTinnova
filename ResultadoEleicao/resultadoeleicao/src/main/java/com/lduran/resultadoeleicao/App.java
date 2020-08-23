package com.lduran.resultadoeleicao;

import com.lduran.resultadoeleicao.model.Eleicao;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Eleicao eleicao = new Eleicao(800, 150, 50);
        
    	System.out.println( "Total de Eleitores " + eleicao.getTotalEleitores());
    	System.out.println( "Percentual de Votos Válidos " + eleicao.getPercentValidos());
    	System.out.println( "Percentual de Votos Brancos " + eleicao.getPercentBrancos());
    	System.out.println( "Percentual de Votos Nulos " + eleicao.getPercentNulos());
    }
}
