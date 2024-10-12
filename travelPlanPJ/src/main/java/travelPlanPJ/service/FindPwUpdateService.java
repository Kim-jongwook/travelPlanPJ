package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.command.FindPwUpdateCommand;
import travelPlanPJ.domain.FindDTO;
import travelPlanPJ.mapper.FindMapper;

@Service
public class FindPwUpdateService {
	@Autowired
	FindMapper findMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void execute(FindPwUpdateCommand findPwUpdateCommand, Model model, HttpSession session) {
		FindDTO findDTO = (FindDTO) session.getAttribute("findDTO");
		findDTO.setMemPw(passwordEncoder.encode(findPwUpdateCommand.getMemPw()));
		
		findMapper.memPwUpdate(findDTO);
		findMapper.empPwUpdate(findDTO);
	}
}
