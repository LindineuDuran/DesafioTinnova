package com.lduran.cadastra_veiculos.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lduran.cadastra_veiculos.model.Veiculo;
import com.lduran.cadastra_veiculos.repository.filter.VeiculoFilter;
import com.lduran.cadastra_veiculos.service.VeiculoService;

/**
 * SpringBoot RestController that creates all service endpoints related to the vehicle.
 * 
 * @author Lindineu Duran
 * @since 23/08/2020
 */
@RestController
@RequestMapping({ "/veiculos" })
public class VeiculoController
{
	@Autowired
	private VeiculoService veiculoService;

	@GetMapping
	public List findAll()
	{
		return this.veiculoService.listarTodos();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable long id)
	{
		return this.veiculoService.buscaVeiculo(id);
	}

	@GetMapping(path = { "/find" })
	public List pesquisar(@ModelAttribute("filtro") VeiculoFilter filtro)
	{
		List<Veiculo> todosVeiculos = this.veiculoService.filtrar(filtro);

		return todosVeiculos;
	}

	@PostMapping
	public String create(@RequestBody Veiculo veiculo)
	{
		return this.veiculoService.salvar(veiculo);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Veiculo veiculo)
	{
		return this.veiculoService.atualizar(id, veiculo);
	}

	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable long id)
	{
		this.veiculoService.excluir(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public @ResponseBody void saveContact(@PathVariable Long id, @RequestBody Map<Object, Object> fields)
	{
		// ResponseEntity<Veiculo> veiculo = this.veiculoService.buscaVeiculo(id);
		Veiculo veiculo = this.veiculoService.buscaVeiculo(id).getBody();

		// Map key is field name, v is value
		fields.forEach((k, v) ->
		{
			// use reflection to get field k on manager and set it to value v
			Field field = ReflectionUtils.findField(Veiculo.class, (String) k);

			field.setAccessible(true);

			ReflectionUtils.setField(field, veiculo, v);
		});

		this.veiculoService.atualizar(id, veiculo);
	}
}
