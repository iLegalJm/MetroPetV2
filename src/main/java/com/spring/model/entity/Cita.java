package com.spring.model.entity;

import java.sql.Date;
import java.sql.Time;

public class Cita {
	private int id;
	private Date fecha;
	private Time hora;
	private int mascota_id;
	private int veterinario_id;
	private String mascota_nombre;
	private String veterinario_nombre;
	private String cliente_nombre;

	public Cita() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cita(int id, Date fecha, Time hora, int mascota_id, int veterinario_id) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.mascota_id = mascota_id;
		this.veterinario_id = veterinario_id;
	}

	public Cita(int id, Date fecha, Time hora, int mascota_id, int veterinario_id, String mascota_nombre,
			String veterinario_nombre, String cliente_nombre) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.mascota_id = mascota_id;
		this.veterinario_id = veterinario_id;
		this.mascota_nombre = mascota_nombre;
		this.veterinario_nombre = veterinario_nombre;
		this.cliente_nombre = cliente_nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public int getMascota_id() {
		return mascota_id;
	}

	public void setMascota_id(int mascota_id) {
		this.mascota_id = mascota_id;
	}

	public int getVeterinario_id() {
		return veterinario_id;
	}

	public void setVeterinario_id(int veterinario_id) {
		this.veterinario_id = veterinario_id;
	}

	public String getMascota_nombre() {
		return mascota_nombre;
	}

	public void setMascota_nombre(String mascota_nombre) {
		this.mascota_nombre = mascota_nombre;
	}

	public String getVeterinario_nombre() {
		return veterinario_nombre;
	}

	public void setVeterinario_nombre(String veterinario_nombre) {
		this.veterinario_nombre = veterinario_nombre;
	}

	public String getCliente_nombre() {
		return cliente_nombre;
	}

	public void setCliente_nombre(String cliente_nombre) {
		this.cliente_nombre = cliente_nombre;
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", mascota_id=" + mascota_id
				+ ", veterinario_id=" + veterinario_id + ", mascota_nombre=" + mascota_nombre + ", veterinario_nombre="
				+ veterinario_nombre + ", cliente_nombre=" + cliente_nombre + "]";
	}

}
