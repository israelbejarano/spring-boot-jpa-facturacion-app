package com.ideas.springboot.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The Class MvcConfig. Clase para configurar el directorio externo de subida de imagenes
 * @author Israel Bejarano
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * Adds the resource handlers.
	 *
	 * @param registry the registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/uploads/**")
		.addResourceLocations("file:/C:/temp/uploads/");
		// si estas en linux o prefieres otro directorio cambiarlo aquí y 
		// tenerlo en cuenta en el controller tambien
		// IMPORTANTE tener creada la estructura de directorios en el sistema
	}
}