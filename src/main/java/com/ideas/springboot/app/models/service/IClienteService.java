package com.ideas.springboot.app.models.service;

import java.util.List;

import com.ideas.springboot.app.models.entity.Cliente;

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

}