package com.spring.model.entity;

public class Veterinario {
	private int id;
	private String nombres;
	private String apellidos;
	private String telefono;
	public Veterinario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Veterinario(int id, String nombres, String apellidos, String telefono) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
