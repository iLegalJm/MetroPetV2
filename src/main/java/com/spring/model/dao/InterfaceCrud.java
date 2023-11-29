package com.spring.model.dao;

import java.util.List;

public interface InterfaceCrud<T> {
	public List<T> listar(String texto);
    public boolean insertar(T obj);
    public boolean actualizar(T obj);
    public int total();
    public boolean existe(String texto);   
}
