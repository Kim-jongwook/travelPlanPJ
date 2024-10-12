package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.command.MemberCommand;
import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.MemberDTO;
import travelPlanPJ.mapper.MemberMapper;

@Service
public class MemberDetailService {
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		String memId = auth.getMemId();
		MemberDTO dto = memberMapper.selectOne(memId);
		MemberCommand memberCommand = new MemberCommand();
		
		memberCommand.setMemAddr(dto.getMemAddr());
		memberCommand.setMemAddrDetail(dto.getMemAddrDetail());
		memberCommand.setMemBirth(dto.getMemBirth());
		memberCommand.setMemEmail(dto.getMemEmail());
		memberCommand.setMemGender(dto.getMemGender());
		memberCommand.setMemId(dto.getMemId());
		memberCommand.setMemName(dto.getMemName());
		memberCommand.setMemNum(dto.getMemNum());
		memberCommand.setMemPhone(dto.getMemPhone());
		memberCommand.setMemPost(dto.getMemPost());
		memberCommand.setMemRegist(dto.getMemRegist());
		
		model.addAttribute("memberCommand", memberCommand);		
	}
}
