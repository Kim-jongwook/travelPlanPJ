package travelPlanPJ.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import travelPlanPJ.command.LoginCommand;
import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.mapper.LoginMapper;

@Service
public class LoginService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(LoginCommand loginCommand, BindingResult result, HttpSession session, HttpServletResponse response) {
		String memId = loginCommand.getMemId();
		String memPw = loginCommand.getMemPw();
		
		if(memId != null && !memId.isEmpty()) {
			if(memPw != null && !memPw.isEmpty()) {
				AuthInfoDTO dto = loginMapper.loginSelect(memId);
				
				if(dto != null) {
					if(dto.getUserEmailCheck() == null || dto.getUserEmailCheck().equals("N")) { // 이메일 인증 안되어있을 때
						result.rejectValue("userEmailCheck", "loginCommand.userEmailCheck", "이메일 인증이 완료되지 않았습니다.");
					}else { // 아이디도 있고, 이메일 인증이 되어있을 때
						if(dto.getGrade().equals("emp")) { // 직원은 비밀번호를 암호화하지 않기 때문에 바로 대조하기
							if(memPw.equals(dto.getMemPw())) {
								session.setAttribute("auth", dto);
							}else {
								result.rejectValue("memPw", "loginCommand.memPw", "비밀번호가 일치하지 않습니다.(관리자)");
							}
						}else {//직원 아닐 때
							if(passwordEncoder.matches(memPw, dto.getMemPw())) { // 암호화된 비밀번호 일치 여부
								session.setAttribute("auth", dto);
								if(loginCommand.getIdStore() != null && loginCommand.getIdStore()) {
									// 아이디저장
									// IdSotre가 null이 아니고 getIdStore 메서드가 반환하는 값이 참(True)일 떄
									Cookie cookie = new Cookie("idStore", loginCommand.getMemId());
									cookie.setPath("/");
									cookie.setMaxAge(60*60*24*30); //60초->60분->24시간->30일
									response.addCookie(cookie); // response를 통해 사용자에게 전달
								}else {
									//아이디 저장 체크해제한 경우
									Cookie cookie = new Cookie("idStore", loginCommand.getMemId());
									cookie.setPath("/");
									cookie.setMaxAge(0);
									response.addCookie(cookie);
								}
								
								if(loginCommand.getAutoLogin() != null && loginCommand.getAutoLogin()) {
									// 자동로그인
									// AutoLogin이 null이 아니고 getAutoLogin 메서드가 반환하는 값이 참(True)일 떄
									Cookie cookie = new Cookie("autoLogin", loginCommand.getMemId());
									cookie.setPath("/");
									cookie.setMaxAge(60*60*24*30);
									response.addCookie(cookie);
									
								}else {
									
								}
							}else {//직원 아니고 비밀번호 다를 때
								result.rejectValue("memPw", "loginCommand.memPw", "비밀번호가 일치하지 않습니다.");
							}
						}
					}
				}else {
					//아이디가 맞지 않을 때
					result.rejectValue("memId", "loginCommand.memId", "아이디가 존재하지 않습니다.");
				}
			}else {}
		}
		
	}
}
