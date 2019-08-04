package com.ideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ideas.springboot.app.models.entity.Factura;

/**
 * The Interface IFacturaDao.
 * @author Israel Bejarano
 */
public interface IFacturaDao extends CrudRepository<Factura, Long>{

}