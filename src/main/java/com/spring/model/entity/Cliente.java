package com.spring.model.entity;

import java.util.Date;

//import java.sql.Date;

public class Cliente {
	private int id;
	private String nombres;
	private String apellidos;
	private String dni;
	private String telefono;
	private String direccion;
	private Date fecha_registro;
	private int user_id;

	public Cliente() {
		super();
	}

	public Cliente(int id, String nombres, String apellidos, String dni, String telefono, String direccion,
			Date fecha_registro, int user_id) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.fecha_registro = fecha_registro;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
}