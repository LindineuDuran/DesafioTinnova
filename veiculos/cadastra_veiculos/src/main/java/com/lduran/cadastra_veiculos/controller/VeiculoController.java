package com.lduran.cadastra_veiculos.controller;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lduran.cadastra_veiculos.model.Marca;
import com.lduran.cadastra_veiculos.model.QtdAno;
import com.lduran.cadastra_veiculos.model.QtdMarca;
import com.lduran.cadastra_veiculos.model.Veiculo;
import com.lduran.cadastra_veiculos.repository.filter.VeiculoFilter;
import com.lduran.cadastra_veiculos.service.VeiculoService;

/**
 * SpringBoot RestController that creates all service endpoints related to the
 * vehicle.
 *
 * @author Lindineu Duran
 * @since 23/08/2020
 */
@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping({ "/veiculos" })
public class VeiculoController
{
	@Autowired
	private VeiculoService veiculoService;

	@GetMapping
	public List<Veiculo> findAll()
	{
		return this.veiculoService.listarTodos();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Veiculo> findById(@PathVariable long id)
	{
		return this.veiculoService.buscaVeiculo(id);
	}

	@GetMapping(path = { "/find" })
	public List<Veiculo> pesquisar(@ModelAttribute("filtro") VeiculoFilter filtro)
	{
		List<Veiculo> todosVeiculos = this.veiculoService.filtrar(filtro);

		return todosVeiculos;
	}

	@GetMapping(path = { "/distrib_marca" })
	public List<QtdMarca> pesquisarMarca()
	{
		List<QtdMarca> distribMarca = this.veiculoService.filtrarQtdMarca();

		return distribMarca;
	}

	@GetMapping(path = { "/distrib_ano" })
	public List<QtdAno> pesquisarAno()
	{
		List<QtdAno> distribMarca = this.veiculoService.filtrarQtdAno();

		return distribMarca;
	}

	@GetMapping(path = { "/nao_vendido" })
	public List<Veiculo> pesquisarNaoVendido()
	{
		List<Veiculo> todosVeiculos = this.veiculoService.filtrarNaoVendido();

		return todosVeiculos;
	}

	@PostMapping
	public ResponseEntity<Veiculo> create(@RequestBody Veiculo veiculo)
	{
		// data atual
		Date agora = new Date();
		veiculo.setCreated(agora);
		veiculo.setUpdated(agora);

		if (veiculo.getChapa() != null)
		{
			return this.veiculoService.salvar(veiculo);
		}
		else
		{
			Veiculo salvo = new Veiculo();
			return ResponseEntity.ok().body(salvo);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Veiculo> update(@PathVariable("id") long id, @RequestBody Veiculo veiculo)
	{
		// data atual
		Date agora = new Date();
		veiculo.setUpdated(agora);

		return this.veiculoService.atualizar(id, veiculo);
	}

	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable long id)
	{
		this.veiculoService.excluir(id);
	}

	@DeleteMapping
	public void deleteAll()
	{
		this.veiculoService.excluirTodos();
	}

	@PatchMapping(value = "/{id}")
	public @ResponseBody void saveContact(@PathVariable Long id, @RequestBody Map<Object, Object> fields)
	{
		// ResponseEntity<Veiculo> veiculo = this.veiculoService.buscaVeiculo(id);
		Veiculo veiculo = this.veiculoService.buscaVeiculo(id).getBody();

		// Map key is field name, v is value
		fields.forEach((k, v) ->
		{
			// use reflection to get field k on manager and set it to value v
			Field field = ReflectionUtils.findField(Veiculo.class, (String) k);

			// Se o atributo a ser alterado for "marca", pega o ENUM que corresponda ao
			// valor
			v = (String) k == "marca" ? Marca.valueOf(((String) v).toUpperCase()) : v;

			field.setAccessible(true);

			ReflectionUtils.setField(field, veiculo, v);
		});

		// data atual
		Date agora = new Date();
		veiculo.setUpdated(agora);

		this.veiculoService.atualizar(id, veiculo);
	}
}
