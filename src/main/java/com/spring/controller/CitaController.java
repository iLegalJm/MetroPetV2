package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.model.dao.DaoCita;
import com.spring.model.dao.DaoMascota;
import com.spring.model.dao.DaoVeterinario;
import com.spring.model.entity.Cita;
import com.spring.model.entity.Mascota;
import com.spring.model.entity.User;
import com.spring.model.entity.Veterinario;

@Controller
public class CitaController {

    @Autowired
    private DaoCita dao;

    @Autowired
    private DaoMascota daoMascota;

    @Autowired
    private DaoVeterinario daoVeterinario;

    @GetMapping("/admin")
    public String admin(Model modelo) {
        List<Cita> lista = dao.listar("");
        modelo.addAttribute("citas", lista);

        if (modelo.containsAttribute("user")) {
            User user = (User) modelo.getAttribute("user");
            // Haz algo con userNombre...
        }
        return "admin";
    }

    @GetMapping("/createCita")
    public String createCita(Model modelo) {
        modelo.addAttribute("cita", new Cita());

        List<Mascota> listaMascotas = daoMascota.listar("");
        modelo.addAttribute("mascotas", listaMascotas);

        List<Veterinario> listaVeterinarios = daoVeterinario.listar("");
        modelo.addAttribute("veterinarios", listaVeterinarios);
        return "Cita/formCita";
    }

    @PostMapping("/saveCita")
    public String saveCita(Cita obj) {
        if (dao.insertar(obj)) {
            return "redirect:/admin";
        } else {
            return "redirect:/createCita";
        }
    }
}
