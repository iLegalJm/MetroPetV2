package com.spring.model.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.model.entity.Cita;

@Repository
public class DaoCita implements InterfaceCrud<Cita> {

	@Autowired
	private JdbcTemplate jt;

	@Override
	public List<Cita> listar(String texto) {
		String sql = "select c.*, m.nombre as mascota_nombre, v.nombres as veterinario_nombre, cl.nombres as cliente_nombre from cita c inner join mascota m on c.mascota_id = m.id inner join veterinario v on c.veterinario_id = v.id inner join cliente cl on m.cliente_id = cl.id";
		List<Cita> lista = jt.query(sql, new BeanPropertyRowMapper<Cita>(Cita.class));
		return lista;
	}

	@Override
	public boolean insertar(Cita obj) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
			String sql = "insert into cita(fecha, hora, mascota_id, veterinario_id) values (?,?,?,?)";
			return jt.update(sql, formatter.format(obj.getFecha()), obj.getHora(),
					obj.getMascota_id(),
					obj.getVeterinario_id()) == 1
							? true
							: false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean actualizar(Cita obj) {
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
