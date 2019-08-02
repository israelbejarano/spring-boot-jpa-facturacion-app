package com.ideas.springboot.app.models.dao;

import java.util.List;

import com.ideas.springboot.app.models.entity.Cliente;

/**
 * The Interface IClienteDao.
 * @author Israel Bejarano
 */
public interface IClienteDao {
	
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