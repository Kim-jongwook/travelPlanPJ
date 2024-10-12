package travelPlanPJ.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import travelPlanPJ.command.LoginCommand;
import travelPlanPJ.service.CookiesService;

@Controller
public class MainController {
	@Autowired
	CookiesService cookiesService;
	
	@RequestMapping("/")
	public String main(@ModelAttribute("loginCommand")LoginCommand loginCommand, Model model, HttpServletRequest request) {
		cookiesService.execute(request, model);
		return "thymeleaf/main";
	}
}
