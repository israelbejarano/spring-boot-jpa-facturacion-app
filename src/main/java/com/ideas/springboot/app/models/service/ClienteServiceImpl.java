package com.ideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideas.springboot.app.models.dao.IClienteDao;
import com.ideas.springboot.app.models.entity.Cliente;

/**
 * The Class ClienteServiceImpl.
 * @author Israel Bejarano
 */
@Service
public class ClienteServiceImpl implements IClienteService {
	
	/** The cliente dao. */
	@Autowired
	private IClienteDao clienteDao;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the cliente
	 */
	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return clienteDao.findOne(id);
	}

	/**
	 * Save.
	 *
	 * @param cliente the cliente
	 */
	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.delete(id);
	}
}