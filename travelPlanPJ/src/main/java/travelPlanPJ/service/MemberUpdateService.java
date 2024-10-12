package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.command.MemberCommand;
import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.MemberDTO;
import travelPlanPJ.mapper.MemberMapper;

@Service
public class MemberUpdateService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(MemberCommand memberCommand, Model model, HttpSession session) {
		MemberDTO dto = new MemberDTO();
		dto.setMemId(memberCommand.getMemId());
		dto.setMemPw(passwordEncoder.encode(memberCommand.getMemPw()));
		dto.setMemBirth(memberCommand.getMemBirth());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemPost(memberCommand.getMemPost());
		dto.setMemAddr(memberCommand.getMemAddr());
		dto.setMemAddrDetail(memberCommand.getMemAddrDetail());
		
		int i = memberMapper.memUpdate(dto);
		
		if(i == 1) {
			AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
			auth.setMemPw(dto.getMemPw());
			model.addAttribute("message", "수정되었습니다.");
		}else {}
	}
}
