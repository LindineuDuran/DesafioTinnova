package com.lduran.cadastroveiculoscomfront.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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

	@EqualsAndHashCode.Exclude
	@NotNull(message = "Data do Cadastro é obrigatória!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date	created;

	@EqualsAndHashCode.Exclude
	@NotNull(message = "Data da Atualização é obrigatória!")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date	updated;

	public boolean foiVendido()
	{
		return Vendido.SIM.equals(this.vendido);
	}
}
