package com.ideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ideas.springboot.app.models.dao.IClienteDao;
import com.ideas.springboot.app.models.dao.IProductoDao;
import com.ideas.springboot.app.models.entity.Cliente;
import com.ideas.springboot.app.models.entity.Producto;

/**
 * The Class ClienteServiceImpl.
 * @author Israel Bejarano
 */
@Service
public class ClienteServiceImpl implements IClienteService {
	
	/** The cliente dao. */
	@Autowired
	private IClienteDao clienteDao;
	
	/** The producto dao. */
	@Autowired
	private IProductoDao productoDao;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	/**
	 * Find all.
	 *
	 * @param pageable the pageable
	 * @return the page
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
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
		return clienteDao.findById(id).orElse(null);
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
		clienteDao.deleteById(id);
	}

	/**
	 * Find by nombre.
	 *
	 * @param term the term
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombre(term);
	}
}