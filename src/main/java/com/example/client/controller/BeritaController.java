package com.example.client.controller;
import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;
import com.example.client.model.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.client.service.UserService;
import com.example.client.model.Berita;
import com.example.client.service.BeritaService;

@Controller
public class BeritaController {
	
	@Autowired
	private BeritaService service; 
	
	@Autowired
	private UserService service2;


/*
	@RequestMapping("/listberita.html")
	public String viewDaftarBerita(Model model) {
		
			List<Berita> listberita = service.listAll();
			model.addAttribute("listberita", listberita);
			return "listberita";

	}
*/
	
	
}