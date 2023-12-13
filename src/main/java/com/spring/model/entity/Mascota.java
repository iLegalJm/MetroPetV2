package com.spring.model.entity;

public class Mascota {	
	private int id;
	private String nombre;
	private String raza;
	private int edad;
	private double peso;
	private int cliente_id;
	private String cliente_nombre;
	private String cliente_apellidos;

	public Mascota() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mascota(int id, String nombre, String raza, int edad, double peso, int cliente_id) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.raza = raza;
		this.edad = edad;
		this.peso = peso;
		this.cliente_id = cliente_id;
	}

	public Mascota(int id, String nombre, String raza, int edad, double peso, int cliente_id, String cliente_nombre,
			String cliente_apellidos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.raza = raza;
		this.edad = edad;
		this.peso = peso;
		this.cliente_id = cliente_id;
		this.cliente_nombre = cliente_nombre;
		this.cliente_apellidos = cliente_apellidos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getCliente_nombre() {
		return cliente_nombre;
	}

	public void setCliente_nombre(String cliente_nombre) {
		this.cliente_nombre = cliente_nombre;
	}

	public String getCliente_apellidos() {
		return cliente_apellidos;
	}

	public void setCliente_apellidos(String cliente_apellidos) {
		this.cliente_apellidos = cliente_apellidos;
	}
}
