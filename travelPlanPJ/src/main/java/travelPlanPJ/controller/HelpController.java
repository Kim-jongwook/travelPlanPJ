package travelPlanPJ.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import travelPlanPJ.command.FindIdCommand;
import travelPlanPJ.command.FindPwCommand;
import travelPlanPJ.command.FindPwUpdateCommand;
import travelPlanPJ.service.FindPwUpdateService;
import travelPlanPJ.service.FindIdService;
import travelPlanPJ.service.FindPwSendService;
import travelPlanPJ.service.FindPwService;

@Controller
@RequestMapping("help")
public class HelpController {
	@Autowired
	FindIdService findIdService;
	@Autowired
	FindPwService findPwService;
	@Autowired
	FindPwUpdateService findPwUpdateService;
	@Autowired
	FindPwSendService findPwSendService;
	
	@RequestMapping(value = "findId", method = RequestMethod.GET)
	public String findId(Model model) {
		model.addAttribute("findIdCommand", new FindIdCommand());
		return "thymeleaf/help/findId";
	}
	
	@RequestMapping(value = "findId", method = RequestMethod.POST)
	public String findId(@Validated FindIdCommand findIdCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/help/findId";
		}
		findIdService.execute(findIdCommand, model);
		return "thymeleaf/help/findIdOk";
	}
	
	@RequestMapping(value = "findPw", method = RequestMethod.GET)
	public String findPw(Model model) {
		model.addAttribute("findPwCommand", new FindPwCommand());
		return "thymeleaf/help/findPw";
	}
	
	@RequestMapping(value = "findPw", method = RequestMethod.POST)
	public String findPw(@Validated FindPwCommand findPwCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/help/findPw";
		}
		findPwService.execute(findPwCommand, result, model, session);
		if(result.hasErrors()) {
			return "thymeleaf/help/findPw";
		}else {
			return "thymeleaf/help/findPwOk";
		}
	}
	
	@RequestMapping(value = "findPwUpdate", method = RequestMethod.GET)
	public String findPwUpdate(Model model) {
		model.addAttribute("findPwUpdateCommand", new FindPwUpdateCommand());
		return "thymeleaf/help/findPwUpdate";
	}
	
	@RequestMapping(value = "findPwUpdate", method = RequestMethod.POST)
	public String findPwUpdate(@Validated FindPwUpdateCommand findPwUpdateCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/help/findPwUpdate";
		}else {
			findPwUpdateService.execute(findPwUpdateCommand, model, session);
			session.invalidate();
			return "thymeleaf/help/findPwUpdateOk";
		}
	}
	
	@RequestMapping(value = "findPwSend", method = RequestMethod.GET)
	public String findPwSend(HttpSession session, Model model) {
		findPwSendService.execute(session, model);
		session.invalidate();
		return "thymeleaf/help/findPwSendOk";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
