package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.domain.AuthInfoDTO;

@Service
public class ConfirmPasswordService {	
	@Autowired
	PasswordEncoder passwordEncoder;

	public int execute(HttpSession session, Model model, String memPw, String link) {
		int i;
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		
		if(passwordEncoder.matches(memPw, auth.getMemPw())) {
			i = 1;
		}else {
			i = 0;
			model.addAttribute("errPw", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("link", link.equals("edit") ? "edit" : "delete");
		}
		return i;
	}
}