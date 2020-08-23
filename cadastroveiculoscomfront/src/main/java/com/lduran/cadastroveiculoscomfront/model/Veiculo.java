package com.lduran.cadastroveiculoscomfront.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Veiculo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long	codigo;
	
	private String	chapa;
	private String	marca;
	private int 	ano;
	private String	descricao;
	
	@EqualsAndHashCode.Exclude
	@Enumerated(EnumType.STRING)
	private Vendido	vendido;
	
	private Date	created;
	private Date	updated;

	public boolean foiVendido()
	{
		return Vendido.SIM.equals(this.vendido);
	}
}
