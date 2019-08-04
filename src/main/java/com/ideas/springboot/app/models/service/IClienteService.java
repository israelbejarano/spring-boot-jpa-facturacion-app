package com.ideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ideas.springboot.app.models.entity.Cliente;
import com.ideas.springboot.app.models.entity.Factura;
import com.ideas.springboot.app.models.entity.Producto;

/**
 * The Interface IClienteService.
 * @author Israel Bejarano
 */
public interface IClienteService {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Cliente> findAll();
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	public Page<Cliente> findAll(Pageable pageable);
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the cliente
	 */
	public Cliente findOne(Long id);
	
	/**
	 * Save.
	 *
	 * @param cliente the cliente
	 */
	public void save(Cliente cliente);

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id);
	
	/**
	 * Find by nombre.
	 *
	 * @param term the term
	 * @return the list
	 */
	public List<Producto> findByNombre(String term);
	
	/**
	 * Save factura.
	 *
	 * @param factura the factura
	 */
	public void saveFactura(Factura factura);
	
	/**
	 * Find producto by id.
	 *
	 * @param id the id
	 * @return the producto
	 */
	public Producto findProductoById(Long id);

}