package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.model.dao.DaoCliente;
import com.spring.model.dao.DaoMascota;
import com.spring.model.entity.Cliente;
import com.spring.model.entity.Mascota;

@Controller
public class MascotaController {

    @Autowired
    private DaoMascota dao;

    @Autowired
    private DaoCliente daoCliente;

    @GetMapping("/mascota")
    public String Mascota(Model modelo) {
        List<Mascota> lista = dao.listar("");
        modelo.addAttribute("mascotas", lista);
        return "Mascota/index";
    }

    @GetMapping("/createMascota")
    public String createMascota(Model modelo) {
        modelo.addAttribute("mascota", new Mascota());
        List<Cliente> listaClientes = daoCliente.listar("");
        modelo.addAttribute("clientes", listaClientes);
        return "Mascota/formMascota";
    }

    @PostMapping("/saveMascota")
    public String saveMascota(Mascota obj) {
        if (dao.insertar(obj)) {
            return "redirect:/mascota";
        } else {
            return "redirect:/createMascota";
        }
    }
}
