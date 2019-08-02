package com.ideas.springboot.app.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.ideas.springboot.app.models.entity.Cliente;

/**
 * The Interface IClienteDao.
 * @author Israel Bejarano
 */
public interface IClienteDao extends CrudRepository<Cliente, Long>{
	
}