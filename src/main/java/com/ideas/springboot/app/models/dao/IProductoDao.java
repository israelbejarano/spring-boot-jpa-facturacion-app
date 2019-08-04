package com.ideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ideas.springboot.app.models.entity.Producto;

/**
 * The Interface IProductoDao.
 * @author Israel Bejarano
 */
public interface IProductoDao extends CrudRepository<Producto, Long> {
	
	/**
	 * Find by nombre.
	 *
	 * @param term the term
	 * @return the list
	 */
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);
}