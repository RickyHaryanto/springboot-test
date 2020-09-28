package com.example.client.controller;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.client.model.User;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.client.model.Donasi;
import com.example.client.model.DonasiUang;
import com.example.client.service.DonasiService;
import com.example.client.service.DonasiUangService;
import com.example.client.service.UserService;
@Controller
public class DonasiController {
    @Autowired
	private DonasiService service; 
	
	@Autowired
	private DonasiUangService service3;
	@Autowired
	private UserService service2;

	@RequestMapping("/list_donasi.html")
	public String viewDaftarDonasi(Model model) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		
		List listdonasi = service.listAll(usr.getUser_id()+"");
		model.addAttribute("listdonasi", listdonasi);


		List listdonasiuang = service3.listAll(usr.getUser_id()+"");
		model.addAttribute("listdonasiuang", listdonasiuang);

		model.addAttribute("test",usr.getUser_id());
		return "list_donasi";
	}



	@RequestMapping("/cetak_donasi.html/{donasi_id}")
	public String cetakdonasi(Model model, @PathVariable(name = "donasi_id") int donasi_id) {
		List<Donasi> listdonasi2 = service.listAll2(donasi_id+"");
		model.addAttribute("listdonasi2", listdonasi2);
		return "cetak_donasi";
	}
    
	
}