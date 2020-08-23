package com.lduran.cadastroveiculoscomfront;

import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class CadastroveiculoscomfrontApplication
{
	@PostConstruct
	/**
	 * Spring Boot: Time Zone configuration using Hibernate
	 * https://aboullaite.me/spring-boot-time-zone-configuration-using-hibernate/
	 */
	void started()
	{
		TimeZone.setDefault(TimeZone.getTimeZone("TimeZone"));
	}

	public static void main(String[] args)
	{
		SpringApplication.run(CadastroveiculoscomfrontApplication.class, args);
	}

	public LocaleResolver localeResolver()
	{
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Configuration
	public static class MvcConfig extends WebMvcConfigurerAdapter
	{
		@Override
		public void addViewControllers(ViewControllerRegistry registry)
		{
			registry.addRedirectViewController("/", "/veiculos?descricao=");
		}
	}

}
