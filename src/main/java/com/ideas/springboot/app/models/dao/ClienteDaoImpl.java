package com.ideas.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ideas.springboot.app.models.entity.Cliente;

/**
 * The Class ClienteDaoImpl.
 * @author Israel Bejarano
 */
@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {
	
	/** The em. */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	/**
	 * Save.
	 *
	 * @param cliente the cliente
	 */
	@Override
	@Transactional
	public void save(Cliente cliente) {
		if(cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente);
		} else {
			em.persist(cliente);			
		}
	}

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the cliente
	 */
	@Override
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}
}