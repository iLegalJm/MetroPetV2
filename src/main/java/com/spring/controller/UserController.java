package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.model.dao.DaoUser;
import com.spring.model.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private DaoUser dao;

	@GetMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("user", new User());
		return "index";
	}

	@PostMapping("/login")
	private String login(Model modelo, User obj, RedirectAttributes flash, HttpSession session) {
		User user = dao.login(obj.getUsuario(), obj.getPassword());
		if (user == null) {
			modelo.addAttribute("mensaje", "Usuario o password incorrecto");
			return "index";
		} else {
			flash.addFlashAttribute("user", user);
			session.setAttribute("user", user);
			return "redirect:/admin";
		}
	}

	@GetMapping("/logout")
	private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
