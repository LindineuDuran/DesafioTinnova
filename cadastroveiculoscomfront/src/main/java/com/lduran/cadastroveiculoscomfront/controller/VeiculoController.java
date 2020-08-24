package com.lduran.cadastroveiculoscomfront.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lduran.cadastroveiculoscomfront.model.Veiculo;
import com.lduran.cadastroveiculoscomfront.model.Vendido;
import com.lduran.cadastroveiculoscomfront.repository.filter.VeiculoFilter;
import com.lduran.cadastroveiculoscomfront.service.VeiculoService;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController
{
	private String CADASTRO_VIEW = "CadastroVeiculo";

	@Autowired
	private VeiculoService veiculoService;

	@RequestMapping("/novo")
	public ModelAndView novo()
	{
		ModelAndView mv = new ModelAndView(this.CADASTRO_VIEW);
		mv.addObject(new Veiculo());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Veiculo veiculo, Errors errors, RedirectAttributes attributes)
	{
		if (errors.hasErrors())
		{
			return this.CADASTRO_VIEW;
		}
		try
		{
			this.veiculoService.salvar(veiculo);
			attributes.addFlashAttribute("mensagem", "Veículo salvo com sucesso!");

			return "redirect:/veiculos";
		}
		catch (IllegalArgumentException e)
		{
			errors.rejectValue("created", null, e.getMessage());
			return this.CADASTRO_VIEW;
		}
	}

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") VeiculoFilter filtro)
	{
		List<Veiculo> todosVeiculos = this.veiculoService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("PesquisaVeiculos");
		mv.addObject("veiculos", todosVeiculos);

		return mv;
	}

	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Optional<Veiculo> veiculo)
	{
		ModelAndView mv = new ModelAndView(this.CADASTRO_VIEW);
		mv.addObject("veiculo", veiculo);
		return mv;
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.POST)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes)
	{
		this.veiculoService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Veículo excluído com sucesso!");

		return "redirect:/veiculos";
	}

	@RequestMapping(value = "/{codigo}/vender", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo)
	{
		return this.veiculoService.vender(codigo);
	}

	@ModelAttribute("todosVendidoVeiculo")
	public List<Vendido> todosVendidoVeiculo()
	{
		return Arrays.asList(Vendido.values());
	}
}
