package com.lduran.cadastra_veiculos.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that implements the Vehicle structure.
 * 
 * @author Lindineu Duran
 * @since 23/08/2020
 * 
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Data
@Entity
public class Veiculo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	id;

	private String	chapa;
	private String	marca;
	private int 	ano;
	private String	descricao;
	private boolean	vendido;
	private Date	created;
	private Date	updated;
}
