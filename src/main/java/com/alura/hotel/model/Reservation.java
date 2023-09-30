package com.alura.hotel.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservas")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_entrada")
    private Date fechaEntrada;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_salida")
    private Date fechaSalida;

    @Column(name = "valor")
    private double valor;

    @Column(name = "forma_pago")
    private String formaPago;
    
    /*@ManyToOne
    @JoinColumn(name = "huesped_id")
    private Huesped huesped;*/

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Long getId() {
		return id;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public double getValor() {
		return valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	

    
}
