package com.example.client.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.example.client.model.Barang;
import com.example.client.model.Cart;
import com.example.client.service.BarangService;
import com.example.client.service.CartService;

@Controller
public class BarangController {
	int currentID = 0;
	
	@Autowired
	private BarangService service; 
	

	
	private Barang barang = new Barang();

	

	@RequestMapping("/listbarang.html")
	public String viewDaftarBarang(Model model) {
		List listbarang = service.listAll();
		model.addAttribute("listbarang", listbarang);
		
		return "listbarang";
	}



	@RequestMapping("/detailbarang.html/{barang_id}")
	public String viewDetail(Model model,@PathVariable(name = "barang_id") int barang_id) {
		List<Barang> listdetail = service.listAll2(barang_id+"");
		model.addAttribute("listdetail", listdetail);
		Cart cart = new Cart();
		model.addAttribute("cart", cart);
		return "detailbarang";
	}
	
	

}