package travelPlanPJ.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import travelPlanPJ.command.LoginCommand;
import travelPlanPJ.service.LogOutService;
import travelPlanPJ.service.LoginService;

@Controller
@RequestMapping(value = "login")
public class LoginController {
	@Autowired
	LoginService loginService;
	@Autowired
	LogOutService logOutService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session, HttpServletResponse response) {
		loginService.execute(loginCommand, result, session, response);
		
		if(result.hasErrors()) {
			return "thymeleaf/main";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "logOut")
	public String logOut(HttpSession session, HttpServletResponse response) {
		logOutService.execute(session, response);
		return "redirect:/";
	}
}
