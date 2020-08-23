package com.lduran.cadastra_veiculos.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lduran.cadastra_veiculos.model.Veiculo;
import com.lduran.cadastra_veiculos.service.VeiculoService;

import lombok.var;

/**
 * SpringBoot RestController that creates all service endpoints related to the vehicle.
 * 
 * @author Lindineu Duran
 * @since 23/08/2020
 */
@RestController
public class VeiculoController
{
	private static final Logger logger = Logger.getLogger(VeiculoController.class);
	
	@Autowired
	private VeiculoService veiculoService;
	
	/**
	 * Method that list all vehicles
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @return ResponseEntity - 200, if has transactions or 404 if hasn't.
	 */
	@GetMapping(path = "/veiculos")
	public ResponseEntity<List<Veiculo>> find()
	{
		if(veiculoService.find().isEmpty())
		{
			return ResponseEntity.notFound().build(); 
		}
		
		logger.info(veiculoService.find());
		
		return ResponseEntity.ok(veiculoService.find());
	}
	
	/**
	 * Method that deletes all existing vehicles.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @return ResponseEntity - 204, if delete with success or 205 if hasn't.
	 */
	@DeleteMapping(path = "/veiculos")
	public ResponseEntity<Boolean> delete()
	{
		try
		{
			veiculoService.delete();
			return ResponseEntity.noContent().build();
		}
		catch(Exception e)
		{
			logger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	/**
	 * Method that creates a vehicle.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param vehicle
	 * 
	 * @return Returns an empty body with one of the following:
	 * 201 – in case of success
	 * 400 – if the JSON is invalid
	 * 422 – if any of the fields are not parsable or the transaction date is in the future
	 */
	@PostMapping(path = "/veiculos")
	@ResponseBody
	public ResponseEntity<Veiculo> create(@RequestBody JSONObject veiculo)
	{
		try
		{
			if(veiculoService.isJSONValid(veiculo.toString()))
			{
				Veiculo veiculoCreated = veiculoService.create(veiculo);
				var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(veiculoCreated.getChapa()).build().toUri();
				
				veiculoService.add(veiculoCreated);
				return ResponseEntity.created(uri).body(null);
			}
			else
			{
				return ResponseEntity.badRequest().body(null);
			}
		}
		catch(Exception e)
		{
			logger.error("JSON fields are not parsable. " + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	/**
	 * Method that creates a vehicle.
	 * 
	 * @author Lindineu Duran
	 * @since 23/08/2020
	 * 
	 * @param transaction
	 * 
	 * @return Returns an empty body with one of the following:
	 * 201 – in case of success
	 * 400 – if the JSON is invalid
	 * 422 – if any of the fields are not parsable or the transaction date is in the future
	 */
	@PutMapping(path = "/veiculos/{id}", produces = { "application/json" })
	public ResponseEntity<Veiculo> update(@PathVariable("id") long id, @RequestBody JSONObject veiculo)
	{
		try
		{
			if(veiculoService.isJSONValid(veiculo.toString()))
			{
				Veiculo veiculoToUpdate = veiculoService.findById(id);
				if(veiculoToUpdate == null)
				{
					logger.error("The transaction not found.");
					return ResponseEntity.notFound().build(); 
				}
				else
				{
					Veiculo veiculoUpdated = veiculoService.update(veiculoToUpdate, veiculo);
					return ResponseEntity.ok(veiculoUpdated);
				}
			}
			else
			{
				return ResponseEntity.badRequest().body(null);
			}
		}
		catch(Exception e)
		{
			logger.error("JSON fields are not parsable." + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
}
