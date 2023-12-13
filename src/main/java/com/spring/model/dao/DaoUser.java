package com.spring.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.model.entity.User;

@Repository
public class DaoUser implements InterfaceCrud<User> {

	@Autowired
	private JdbcTemplate jt;

	@Override
	public List<User> listar(String texto) {
		String sql = "select id, usuario, nombre, apellidos, foto, fecha_registro from user";
		List<User> lista = jt.query(sql, new BeanPropertyRowMapper<User>(User.class));
		return lista;
	}

	@Override
	public boolean insertar(User obj) {
		try {
			String sql = "insert into user(usuario, nombre, apellidos, password, foto) values(?,?,?,?,?)";
			return jt.update(sql, obj.getUsuario(), obj.getNombre(), obj.getApellidos(), obj.getPassword(),
					obj.getFoto()) == 1 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean actualizar(User obj) {
		// String sql = "update user set usuario=?, nombre=?, apellidos=?, password=?,
		// foto=? where id=?";
		String sql = "update user set usuario=?, nombre=?, apellidos=?, foto=? where id=?";
		try {
			return jt.update(sql, obj.getUsuario(), obj.getNombre(), obj.getApellidos(), obj.getFoto(),
					obj.getId()) == 1
							? true
							: false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
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
			String sql = "select usuario, nombre, apellidos, foto, fecha_registro from user where usuario = ? and password = ?";
			@SuppressWarnings("deprecation")
			User user = jt.queryForObject(sql, new Object[] { usuario, password },
					new BeanPropertyRowMapper<User>(User.class));
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User buscar(int id) {
		String sql = "select id, usuario, nombre, apellidos, foto, fecha_registro from user where id = ?";
		return jt.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(User.class));
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "delete from user where id = ?";
		try {
			return jt.update(sql, id) == 1 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}
}
