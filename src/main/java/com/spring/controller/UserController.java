package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.model.dao.DaoUser;
import com.spring.model.entity.User;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
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
			// flash.addFlashAttribute("user", user);
			session.setAttribute("user", user);
			return "redirect:/cita";
		}
	}

	@GetMapping("/logout")
	private String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/user")
	public String user(Model modelo) {
		modelo.addAttribute("users", dao.listar(""));
		return "User/index";
	}

	@GetMapping("/createUser")
	public String createUser(Model modelo) {
		modelo.addAttribute("user", new User());
		return "User/formUser";
	}

	@PostMapping("/saveUser")
	public String saveUser(@Validated @ModelAttribute User obj, BindingResult result, Model modelo,
			@RequestParam("file") MultipartFile imagen) {
		try {
			Path directorioImagenes = Paths.get("src/main/resources/static/public/imgUser");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

			Path rutaImagen = Paths.get(rutaAbsoluta, imagen.getOriginalFilename());

			Files.copy(imagen.getInputStream(), rutaImagen, StandardCopyOption.REPLACE_EXISTING);

			obj.setFoto("public/imgUser/" + imagen.getOriginalFilename());

			if (dao.insertar(obj)) {
				return "redirect:/user";
			} else {
				return "redirect:/createUser";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/createUser";
		}
	}

	@GetMapping("/editUser/{id}")
	public String editUser(@PathVariable int id, Model modelo) {
		User obj = dao.buscar(id);
		modelo.addAttribute("user", obj);
		return "User/edit";
	}

	@PostMapping("/updateUser/{id}")
	public String updateUser(@Validated @ModelAttribute User obj, @PathVariable int id,
			@RequestParam("file") MultipartFile imagen) {
		obj.setId(id);

		try {
			if (!imagen.isEmpty()) {
				Path directorioImagenes = Paths.get("src/main/resources/static/public/imgUser");
				String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

				Path rutaImagen = Paths.get(rutaAbsoluta, imagen.getOriginalFilename());

				Files.copy(imagen.getInputStream(), rutaImagen, StandardCopyOption.REPLACE_EXISTING);

				obj.setFoto("public/imgUser/" + imagen.getOriginalFilename());
			}

			if (dao.actualizar(obj)) {
				return "redirect:/user";
			} else {
				return "redirect:/editUser/" + obj.getId();
			}
		} catch (IOException e) {
			e.printStackTrace();
			// return "redirect:/editUser/" + obj.getId();
			return "redirect:/user";
		}
	}

	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id) {
		if (dao.eliminar(id)) {
			return "redirect:/user";
		} else {
			return "redirect:/user";
		}
	}
}
