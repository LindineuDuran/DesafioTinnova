package com.lduran.cadastra_veiculos.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lduran.cadastra_veiculos.factory.VeiculoFactory;
import com.lduran.cadastra_veiculos.factory.impl.VeiculoFactoryImpl;
import com.lduran.cadastra_veiculos.model.Veiculo;

import lombok.var;

/**
 * Service that implements methods related to a transaction.
 * 
 * @author Lindineu Duran
 * @since 23/08/2020
 */
@Service
public class VeiculoService
{
	private VeiculoFactory factory;
	private List<Veiculo> veiculos;
	
	/**
	 * Method to create VeiculoFactory
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 */
	public void createFactory()
	{
		if(factory == null) {
			factory = new VeiculoFactoryImpl();
		}
	}
	
	/**
	 * Method to create the vehicle list
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 */
	public void createVeiculoList()
	{
		if(veiculos == null)
		{
			veiculos = new ArrayList<>();
		}
	}
	
	/**
	 * Method that check if JSON is valid.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param jsonInString
	 * @return boolean
	 */
	public boolean isJSONValid(String jsonInString)
	{
	    try
	    {
	       return new ObjectMapper().readTree(jsonInString) != null;
	    }
	    catch (IOException e)
	    {
	       return false;
	    }
	}
	
	/**
	 * Method to parse the id field.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 * @return long
	 */
	private long parseId(JSONObject veiculo)
	{
		return Long.valueOf((int) veiculo.get("id"));
	}
	
	/**
	 * Method to parse the nome field.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 * @return long
	 */
	private String parseNome(JSONObject veiculo)
	{
		return (String) veiculo.get("nome");
	}
	
	/**
	 * Method to parse the marca field.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 * @return long
	 */
	private String parseMarca(JSONObject veiculo)
	{
		return (String) veiculo.get("marca");
	}
	
	/**
	 * Method to parse the ano field.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param transaction
	 * @return long
	 */
	private long parseAno(JSONObject transaction)
	{
		return Integer.valueOf((int) transaction.get("ano"));
	}
	
	/**
	 * Method to parse the descricao field.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 * @return long
	 */
	private String parseDescricao(JSONObject veiculo)
	{
		return (String) veiculo.get("descricao");
	}
	
	/**
	 * Method to parse the vendido field.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 * @return long
	 */
	private boolean parseVendido(JSONObject veiculo)
	{
		return (boolean) veiculo.get("vendido");
	}
	
	/**
	 * Method to parse the Created Date field.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 * @return LocalDateTime
	 */
	private LocalDateTime parseCreatedDate(JSONObject veiculo)
	{
		var createdDate = (var) veiculo.get("created");
		return ZonedDateTime.parse((CharSequence) createdDate).toLocalDateTime();
	}
	
	/**
	 * Method to parse the Updated Date field.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 * @return LocalDateTime
	 */
	private LocalDateTime parseUpdatedDate(JSONObject veiculo)
	{
		var updatedDate = (var) veiculo.get("updated");
		return ZonedDateTime.parse((CharSequence) updatedDate).toLocalDateTime();
	}
	
	/**
	 * Method to fullfil the Vehicle object
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param jsonVeiculo
	 * @param Veiculo
	 */
	private void setVeiculoValues(JSONObject jsonVeiculo, Veiculo veiculo)
	{
		String chapa = (String) jsonVeiculo.get("chapa");
		String marca = (String) jsonVeiculo.get("marca");
		int ano = (int) jsonVeiculo.get("ano");
		String descricao = (String) jsonVeiculo.get("descricao");
		boolean vendido = (boolean) jsonVeiculo.get("vendido");
		Date created = (Date) jsonVeiculo.get("created");		
		Date updated = (Date) jsonVeiculo.get("updated");
		
		veiculo.setChapa(chapa != null ? chapa : veiculo.getChapa());
		veiculo.setMarca(marca != null ? marca : veiculo.getMarca());
		veiculo.setAno(ano != 0 ? ano : veiculo.getAno());
		veiculo.setDescricao(descricao != null ? descricao : veiculo.getDescricao());
		veiculo.setVendido(vendido);
		veiculo.setCreated(created);
		veiculo.setUpdated(updated);
	}
	
	/**
	 * Method to create a veiculo
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param jsonVeiculo
	 * @return Veiculo
	 */
	public Veiculo create(JSONObject jsonVeiculo)
	{		
		createFactory();
		
		Veiculo veiculo = factory.createVeiculo();
		veiculo.setId(parseId(jsonVeiculo));
		setVeiculoValues(jsonVeiculo, veiculo);
		
		return veiculo;
	}
	
	/**
	 * Method to update a transaction
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 * @param jsonVeiculo
	 * 
	 * @return Veiculo
	 */
	public Veiculo update(Veiculo veiculo, JSONObject jsonVeiculo)
	{		
		setVeiculoValues(jsonVeiculo, veiculo);
		return veiculo;
	}

	/**
	 * Method that add a vehicle to the list of vehicles
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculo
	 */
	public void add(Veiculo veiculo)
	{
		createVeiculoList();
		veiculos.add(veiculo);
	}

	/**
	 * Method that get all vehicles
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param veiculos
	 * @return List
	 */
	public List<Veiculo> find()
	{
		createVeiculoList();
		return veiculos;
	}
	
	/**
	 * Method that get a vehicle by id
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param id
	 * @return Veiculo
	 */
	public Veiculo findById(long id)
	{
		return veiculos.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
	}
	
	/**
	 * Method that deletes the vehicles created
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 */
	public void delete()
	{
		veiculos.clear();
	}
	
	/**
	 * Method to clean objects
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 */
	public void clearObjects()
	{
		veiculos = null;
		factory = null;
	}
}
