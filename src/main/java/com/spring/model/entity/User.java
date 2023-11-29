package com.spring.model.entity;

import java.sql.Date;

public class User {
	private int id;
	private String usuario;
	private String nombre;
	private String apellidos;
	private String password;
	private Date fecha_registro;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String usuario, String nombre, String apellidos, String password) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
	}
	
	public User(int id, String usuario, String nombre, String apellidos, String password, Date fecha_registro) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.password = password;
		this.fecha_registro = fecha_registro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
	
}
