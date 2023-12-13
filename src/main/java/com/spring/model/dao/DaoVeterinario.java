package com.spring.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.model.entity.Veterinario;

@Repository
public class DaoVeterinario implements InterfaceCrud<Veterinario> {

	@Autowired
	private JdbcTemplate jt;

	@Override
	public List<Veterinario> listar(String texto) {
		String sql = "select * from veterinario";
		List<Veterinario> lista = jt.query(sql, BeanPropertyRowMapper.newInstance(Veterinario.class));
		return lista;
	}

	@Override
	public boolean insertar(Veterinario obj) {
		try {
			String sql = "insert into veterinario(nombres,apellidos,telefono)"
					+ "values(?,?,?)";
			return jt.update(sql, obj.getNombres(), obj.getApellidos(),
					obj.getTelefono()) == 1 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean actualizar(Veterinario obj) {
		try {
			String sql = "update veterinario set nombres=?,apellidos=?,telefono=? where id=?";
			return jt.update(sql, obj.getNombres(), obj.getApellidos(),
					obj.getTelefono(), obj.getId()) == 1 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int total() {
		String sql = "select count(*) from veterinario";
		return jt.queryForObject(sql, Integer.class);
	}

	@Override
	public boolean existe(String texto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Veterinario buscar(int id) {
		String sql = "select * from veterinario where id = ?";
		return jt.queryForObject(sql, new Object[] { id },
				BeanPropertyRowMapper.newInstance(Veterinario.class));
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "delete from veterinario where id = ?";
		try {
			return jt.update(sql, id) == 1 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

}
