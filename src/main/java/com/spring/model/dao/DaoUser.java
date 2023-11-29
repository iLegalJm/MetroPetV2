package com.spring.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.model.entity.User;

@Repository
public class DaoUser implements InterfaceCrud<User>{
	
	@Autowired
	private JdbcTemplate jt;
	
	@Override
	public List<User> listar(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertar(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(User obj) {
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
	
	public User login(String usuario, String password) {
		try {
			String sql = "select usuario, nombre, apellidos, fecha_registro  from user where usuario = ? and password = ?";
			@SuppressWarnings("deprecation")
			User user = jt.queryForObject(sql, new Object[] {usuario, password}, new BeanPropertyRowMapper<User>(User.class));
			return user;
		} catch (Exception e) {
			return null;
		}
	}
}
