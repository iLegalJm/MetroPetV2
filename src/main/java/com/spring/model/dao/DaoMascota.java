package com.spring.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.model.entity.Mascota;

@Repository
public class DaoMascota implements InterfaceCrud<Mascota> {

	@Autowired
	private JdbcTemplate jt;

	@Override
	public List<Mascota> listar(String texto) {
		// String sql = "select * from mascota";
		String sql = "select m.*, c.nombres as cliente_nombre, c.apellidos as cliente_apellidos from mascota m inner join cliente c on m.cliente_id = c.id";
		List<Mascota> lista = jt.query(sql, BeanPropertyRowMapper.newInstance(Mascota.class));
		return lista;
	}

	@Override
	public boolean insertar(Mascota obj) {
		try {
			String sql = "insert into mascota(nombre, raza, edad, peso, cliente_id) values (?,?,?,?,?)";
			return jt.update(sql, obj.getNombre(), obj.getRaza(), obj.getEdad(), obj.getPeso(),
					obj.getCliente_id()) == 1 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean actualizar(Mascota obj) {
		String sql = "update mascota set nombre = ?, raza = ?, edad = ?, peso = ?, cliente_id = ? where id = ?";
		try {
			return jt.update(sql, obj.getNombre(), obj.getRaza(), obj.getEdad(), obj.getPeso(),
					obj.getCliente_id(), obj.getId()) == 1 ? true : false;
		} catch (Exception e) {
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

	@Override
	public Mascota buscar(int id) {
		String sql = "select m.*, c.nombres as cliente_nombre, c.apellidos as cliente_apellidos from mascota m inner join cliente c on m.cliente_id = c.id where m.id = ?";
		return jt.queryForObject(sql, new BeanPropertyRowMapper<Mascota>(Mascota.class), id);
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "delete from mascota where id = ?";
		try {
			return jt.update(sql, id) == 1 ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

}
