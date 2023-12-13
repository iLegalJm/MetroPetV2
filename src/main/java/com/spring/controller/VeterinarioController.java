package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.model.dao.DaoVeterinario;
import com.spring.model.entity.Veterinario;

@Controller
public class VeterinarioController {

    @Autowired
    private DaoVeterinario dao;

    @GetMapping("/veterinario")
    public String veterinario(Model modelo) {
        List<Veterinario> lista = dao.listar("");
        modelo.addAttribute("veterinarios", lista);
        return "Veterinario/index";
    }

    @GetMapping("/createVeterinario")
    public String createVeterinario(Model modelo) {
        modelo.addAttribute("veterinario", new Veterinario());
        return "Veterinario/formVeterinario";
    }

    @PostMapping("/saveVeterinario")
    public String saveVeterinario(Veterinario obj) {
        if (dao.insertar(obj)) {
            return "redirect:/veterinario";
        } else {
            return "redirect:/createVeterinario";
        }
    }

    @GetMapping("/editVeterinario/{id}")
    public String editVeterinario(@PathVariable int id, Model modelo) {
        Veterinario obj = dao.buscar(id);
        modelo.addAttribute("veterinario", obj);
        return "Veterinario/edit";
    }

    @PostMapping("/updateVeterinario/{id}")
    public String updateVeterinario(@ModelAttribute Veterinario obj, @PathVariable int id) {
        obj.setId(id);
        if (dao.actualizar(obj)) {
            return "redirect:/veterinario";
        } else {
            return "redirect:/editVeterinario/" + obj.getId();
        }
    }

    @GetMapping("/deleteVeterinario/{id}")
    public String deleteVeterinario(@PathVariable int id) {
        if (dao.eliminar(id)) {
            return "redirect:/veterinario";
        } else {
            return "redirect:/veterinario";
        }
    }
}
