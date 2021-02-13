package com.lduran.cadastra_veiculos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QtdMarca
{
	private Marca marca;
	private long quantidade;
}
