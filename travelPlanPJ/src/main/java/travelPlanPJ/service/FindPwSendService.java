package travelPlanPJ.service;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.domain.FindDTO;
import travelPlanPJ.mapper.FindMapper;

@Service
public class FindPwSendService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	FindMapper findMapper;
	@Autowired
	EmailSendService emailSendService;
	public void execute(HttpSession session, Model model) {
		FindDTO findDTO = (FindDTO) session.getAttribute("findDTO");
		
		String newPw = UUID.randomUUID().toString().substring(0, 16);
		
		findDTO.setMemPw(newPw);
		
		findMapper.memPwUpdate(findDTO);
		findMapper.empPwUpdate(findDTO);
		model.addAttribute("memEmail", findDTO.getMemEmail());
		
		String html = "<html><body>"
				+ "<h2 style='font-weight: 700; font-size: 2rem; line-height: 2.75rem; padding-top: 64px; margin: 0;'>임시 비밀번호가 도착했습니다 :-)</h2><br>"
				+ "<p>발급받은 임시 비밀번호로 로그인하여 꼭 안전한 비밀번호로 변경해주세요.<br>"
				+ "임시 비밀번호는 " + newPw + "입니다.";
		String subject = "[트래블플랜] 임시비밀번호 발급.";
		String fromEmail = "kimzong1smtp@gmail.com";
		String toEmail = findDTO.getMemEmail();
		
		emailSendService.mailSend(html, subject, fromEmail, toEmail);
	}
}
