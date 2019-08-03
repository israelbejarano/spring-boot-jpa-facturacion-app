package com.ideas.springboot.app;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The Class MvcConfig. Clase para configurar el directorio de subida de imagenes
 * @author Israel Bejarano
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Adds the resource handlers.
	 *
	 * @param registry the registry
	 */
	/*@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
		log.info("resourcePath: " + resourcePath);
		registry.addResourceHandler("/uploads/**")
		.addResourceLocations(resourcePath);
	}*/
}