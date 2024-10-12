package travelPlanPJ.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.command.LoginCommand;
import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.mapper.LoginMapper;

@Service
public class CookiesService {
	@Autowired
	LoginMapper loginMapper;
	
	public void execute(HttpServletRequest request, Model model) {
		Cookie [] cookies = request.getCookies();
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("idStore")) {
					LoginCommand loginCommand = new LoginCommand();
					loginCommand.setMemId(cookie.getValue());
					loginCommand.setIdStore(true);
					model.addAttribute("loginCommand", loginCommand);
				}
				if(cookie.getName().equals("autoLogin")) {
					AuthInfoDTO auth = loginMapper.loginSelect(cookie.getValue());
					HttpSession session = request.getSession();
					session.setAttribute("auth", auth);
				}
			}
		}
	}
}
