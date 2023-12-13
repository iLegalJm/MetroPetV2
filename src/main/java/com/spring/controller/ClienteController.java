package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.model.dao.DaoCliente;
import com.spring.model.entity.Cliente;

@Controller
public class ClienteController {

    @Autowired
    private DaoCliente dao;

    @GetMapping("/cliente")
    public String Cliente(Model modelo) {
        List<Cliente> lista = dao.listar("");
        modelo.addAttribute("clientes", lista);
        return "Cliente/index";
    }

    @GetMapping("/createCliente")
    public String createCliente(Model modelo) {
        modelo.addAttribute("cliente", new Cliente());
        return "Cliente/formCliente";
    }

    @PostMapping("/saveCliente")
    public String saveCliente(Cliente obj) {
        if (dao.insertar(obj)) {
            return "redirect:/cliente";
        } else {
            return "redirect:/createCliente";
        }
    }

    @GetMapping("/editCliente/{id}")
    public String editCliente(@PathVariable int id, Model modelo) {
        Cliente cliente = dao.buscar(id);
        modelo.addAttribute("cliente", cliente);
        return "Cliente/edit";
    }

    @PostMapping("/updateCliente/{id}")
    public String updateCliente(@PathVariable int id, Cliente obj) {
        if (dao.actualizar(obj)) {
            return "redirect:/cliente";
        } else {
            return "redirect:/editCliente/" + id;
        }
    }

    @GetMapping("/deleteCliente/{id}")
    public String deleteCliente(@PathVariable int id) {
        if (dao.eliminar(id)) {
            return "redirect:/cliente";
        } else {
            return "redirect:/cliente";
        }
    }
}
