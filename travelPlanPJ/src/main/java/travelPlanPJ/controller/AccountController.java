package travelPlanPJ.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import travelPlanPJ.command.MemberCommand;
import travelPlanPJ.service.ConfirmPasswordService;
import travelPlanPJ.service.MemberDeleteService;
import travelPlanPJ.service.MemberDetailService;
import travelPlanPJ.service.MemberUpdateService;

@Controller
@RequestMapping("account")
public class AccountController {
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	ConfirmPasswordService confirmPasswordService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@RequestMapping(value = "myInfo", method = RequestMethod.GET)
	public String myInfo(HttpSession session, Model model) {
		memberDetailService.execute(session, model);
		return "thymeleaf/account/myInfo";
	}
	
	
	@RequestMapping(value = "confirmPassword", method = RequestMethod.GET)
	public String confirmPassword(Model model, @RequestParam(value = "link")String link) {
		if(link.equals("edit")) {
			model.addAttribute("link", "edit");
		}else if(link.equals("delete")){
			model.addAttribute("link", "delete");
		}
		return "thymeleaf/account/confirmPassword";
	}
	
	@RequestMapping(value = "confirmPassword", method = RequestMethod.POST)
	public String confirmPassword(HttpSession session, Model model, @RequestParam(value = "memPw")String memPw
									, @RequestParam(value = "link")String link) {
		int i = confirmPasswordService.execute(session, model, memPw, link);
		if(i == 0) {
			return "thymeleaf/account/confirmPassword";
		}else {
			memberDetailService.execute(session, model);
			return "thymeleaf/account/editProfile";
		}
	}
	
	@RequestMapping(value = "editProfile", method = RequestMethod.POST)
	public String editProfile(@Validated MemberCommand memberCommand, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/account/editProfile";
		}
		if(!memberCommand.isMemPwEqualsMemPwCon()) {
			result.rejectValue("memPwCon", "memberCommand.memPwCon", "비밀번호가 서로 일치하지 않습니다.");
			return "thymeleaf/account/editProfile";
		}
		memberUpdateService.execute(memberCommand, model, session);
		return "thymeleaf/account/myInfo";
	}
	
	@RequestMapping(value = "confirmDelete", method = RequestMethod.POST)
	public String confirmDelete(HttpSession session, Model model, @RequestParam(value = "memPw")String memPw
								, @RequestParam(value = "link")String link) {
		int i = confirmPasswordService.execute(session, model, memPw, link);
		if(i == 0) {
			return "thymeleaf/account/confirmPassword";
		}else {
			return "thymeleaf/account/confirmDelete";
		}
	}
	
	@RequestMapping(value = "deleteProfile", method = RequestMethod.POST)
	public String deleteProfile(HttpSession session, HttpServletResponse response) {
		memberDeleteService.execute(session);
		return "redirect:/login/logOut";
	}
}
