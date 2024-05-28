package com.hibernate.model;
/**
 *Esta clase proporciona metodos para manipular el objeto habitacion
 *@author Silvia
 *@version 1.0
 */

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Representa una habitacion en el sistema de gestion de hoteles.
 * Cada habitacion puede tener varios servicios asociados.
 */
@Entity
@Table (name="habitacion")
public class Habitacion {
	
	/**
     * El identificador unico para una habitacion.
     */
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO) 
	@Column (name="idHabitacion")
	private int idHabitacion;
	
	 /**
     * El numero de habitacion.
     */
	@Column (name="numeroHabitacion")
	private String numeroHabitacion;
	
	/**
     * El tipo de habitacion.
     */
	@Column (name="tipoHabitacion")
	private String tipoHabitacion;
	
	/**
     * El precio de la habitacion por noche.
     */
	@Column (name="precio")
	private double precio;
	

    /**
     * Los servicios asociados con esta habitacion.
     * Esta es una relacion muchos a muchos.
     */
	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(
			name = "servicio_habitacion", //Se crea automaticamente...
			joinColumns = @JoinColumn(name = "idHabitacion"),
            inverseJoinColumns = @JoinColumn(name = "idServicio")
			  )
	private List<Servicio> servicios=new ArrayList<Servicio>();
	
	 /**
     * Constructor por defecto de la clase Habitacion.
     */
	public Habitacion() {
	super();
	}
	
	/**
     * Constructor de la clase Habitacion con parametros.
     *
     * @param numeroHabitacion El numero de la habitacion.
     * @param tipoHabitacion El tipo de habitacion.
     * @param precio El precio de la habitacion por noche.
     */
	public Habitacion(String numeroHabitacion, String tipoHabitacion, double precio) {
		super();
		this.numeroHabitacion = numeroHabitacion;
		this.tipoHabitacion = tipoHabitacion;
		this.precio = precio;
	}

	 /**
     * Obtiene el identificador unico de la habitacion.
     * 
     * @return El ID de la habitacion.
     */
	public int getIdHabitacion() {
		return idHabitacion;
	}

	 /**
     * Establece el identificador unico de la habitacion.
     * 
     * @param idHabitacion El ID de la habitacion.
     */
	public void setIdHabitacion(int idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	 /**
     * Obtiene el numero de la habitacion.
     * 
     * @return El numero de la habitacion.
     */
	public String getNumeroHabitacion() {
		return numeroHabitacion;
	}

	   /**
     * Establece el numero de la habitacion.
     * 
     * @param numeroHabitacion El numero de la habitacion.
     */
	public void setNumeroHabitacion(String numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	/**
     * Obtiene el tipo de la habitacion.
     * 
     * @return El tipo de la habitacion.
     */
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	 /**
     * Establece el tipo de la habitacion.
     * 
     * @param tipoHabitacion El tipo de la habitacion.
     */
	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	  /**
     * Obtiene el precio de la habitacion por noche.
     * 
     * @return El precio de la habitacion por noche.
     */
	public double getPrecio() {
		return precio;
	}

	   /**
     * Establece el precio de la habitacion por noche.
     * 
     * @param precio El precio de la habitacion por noche.
     */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	   /**
     * Obtiene la lista de servicios asociados con esta habitacion.
     * 
     * @return La lista de servicios.
     */
	public List<Servicio> getServicios() {
		return servicios;
	}
	

    /**
     * Establece la lista de servicios asociados con esta habitacion.
     * 
     * @param servicios La lista de servicios.
     */
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
	
	/**
     * Anyade un servicio a la lista de servicios asociados con esta habitacion.
     *
     * @param s El servicio a anyadir.
     */
	public void anyadirServicio(Servicio s) {
		this.servicios.add(s);
		s.getHabitaciones().add(this);
	}
	
	   /**
     * Quita un servicio de la lista de servicios asociados con esta habitacion.
     *
     * @param s El servicio a quitar.
     */
	public void quitarServicio(Servicio s) {
		this.servicios.remove(s);
		s.getHabitaciones().remove(this);
	}
}
