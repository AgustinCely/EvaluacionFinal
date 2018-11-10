package com.mitocode.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Table(name = "signos_vitales")
public class SignosVitales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idsignosvitales;
	
	@Column(name = "pulso", nullable = false)
	private int pulso;
	
	@Column(name ="temperatura", nullable = false)
	private int temperatura;
	
	@Column(name="ritmo_respiratorio",nullable = false,length = 15)
	private String ritmo_respiratorio;
	
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente;
	
	@JsonSerialize(using = ToStringSerializer.class)
	private LocalDateTime fecha;

	public int getIdsignosvitales() {
		return idsignosvitales;
	}

	public void setIdsignosvitales(int idsignosvitales) {
		this.idsignosvitales = idsignosvitales;
	}

	public int getPulso() {
		return pulso;
	}

	public void setPulso(int pulso) {
		this.pulso = pulso;
	}

	public int getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(int temperatura) {
		this.temperatura = temperatura;
	}

	public String getRitmo_respiratorio() {
		return ritmo_respiratorio;
	}

	public void setRitmo_respiratorio(String ritmo_respiratorio) {
		this.ritmo_respiratorio = ritmo_respiratorio;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	
}
