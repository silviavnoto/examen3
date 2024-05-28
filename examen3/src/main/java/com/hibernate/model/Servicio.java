package com.hibernate.model;
/**
 *Esta clase proporciona metodos para manipular el objeto servicio
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
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

/**
 * Representa una entidad de servicio.
 * Esta clase esta mapeada a la tabla servicio en la base de datos.
 */

@Entity
@Table (name="servicio")
public class Servicio {
	
	 /**
     * El identificador unico para un servicio.
     */
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO) 
	@Column (name="idServicio")
	private int idServicio;
	
	 /**
     * El nombre del servicio.
     */
	@Column (name="nombreServicio")
	private String nombreServicio;
	
	/**
     * La lista de habitaciones asociadas con este servicio.
     * Esta es una relacion muchos a muchos.
     */
	@ManyToMany (fetch = FetchType.EAGER)   //(cascade = CascadeType.ALL)
	@JoinTable(
			name="servicio_habitacion",  //Se crea automaticamente...
			joinColumns = @JoinColumn(name = "idServicio"),
		    inverseJoinColumns = @JoinColumn(name = "idHabitacion")
			  )
	
	private List<Habitacion> habitaciones=new ArrayList<Habitacion>();
	
	/**
     * Constructor vacio por defecto.
     */
	public Servicio() {
		super();
	}

	 /**
     * Constructor con el nombre del servicio.
     *
     * @param nombreServicio el nombre del servicio
     */
	public Servicio(String nombreServicio) {
		super();
		this.nombreServicio = nombreServicio;
	}

	 /**
     * Obtiene el identificador único del servicio.
     *
     * @return el ID del servicio
     */
	public int getIdServicio() {
		return idServicio;
	}

	 /**
     * Establece el identificador único del servicio.
     *
     * @param idServicio el ID del servicio
     */
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	/**
     * Obtiene el nombre del servicio.
     *
     * @return el nombre del servicio
     */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
     * Establece el nombre del servicio.
     *
     * @param nombreServicio el nombre del servicio
     */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
    /**
     * Obtiene la lista de habitaciones asociadas con este servicio.
     *
     * @return la lista de habitaciones
     */

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}
	 /**
     * Establece la lista de habitaciones asociadas con este servicio.
     *
     * @param habitaciones la lista de habitaciones
     */
	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}
	/**
     * Añade una habitacion al servicio.
     *
     * @param h la habitacion a anyadir
     */
	public void anyadirHabitacion(Habitacion h) {
		this.habitaciones.add(h);
		h.getServicios().add(this);
	}
	/**
     * Elimina una habitacion del servicio.
     *
     * @param h la habitación a eliminar
     */
	public void quitarHabitacion(Habitacion h) {
		this.habitaciones.remove(h);
		h.getServicios().remove(this);
	}
}
