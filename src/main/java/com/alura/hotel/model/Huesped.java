package com.alura.hotel.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "huespedes")
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "reserva_id")
    private Long reserva_id;
    
    /*@OneToMany(mappedBy = "huesped", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;*/

    //Setters
    
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setFechaNacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setReserva_id(Long reserva_id) {
		this.reserva_id = reserva_id;
	}

    //Getters
    
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public Long getReserva_id() {
		return reserva_id;
	}
    

    
}
