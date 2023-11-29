package com.spring.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.model.entity.Cliente;

@Repository
public class DaoCliente implements InterfaceCrud<Cliente> {

	@Autowired
	private JdbcTemplate jt;

	@Override
	public List<Cliente> listar(String texto) {
		String sql = "select * from cliente";
		List<Cliente> lista = jt.query(sql, BeanPropertyRowMapper.newInstance(Cliente.class));
		return lista;
	}

	@Override
	public boolean insertar(Cliente obj) {
		try {
			String sql = "insert into cliente(nombres, apellidos, dni, telefono, direccion, user_id) values (?,?,?,?,?, 1)";
			return jt.update(sql, obj.getNombres(), obj.getApellidos(), obj.getDni(), obj.getTelefono(),
					obj.getDireccion()) == 1 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean actualizar(Cliente obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int total() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean existe(String texto) {
		// TODO Auto-generated method stub
		return false;
	}

}
