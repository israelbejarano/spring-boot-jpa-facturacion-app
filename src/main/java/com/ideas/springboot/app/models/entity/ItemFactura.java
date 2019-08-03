package com.ideas.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class ItemFactura.
 * @author Israel Bejarano
 */
@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** The cantidad. */
	private Integer cantidad;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad the new cantidad
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
	 * Calcular importe.
	 *
	 * @return the long
	 */
	public Long calcularImporte() {
		return cantidad.longValue();
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
}