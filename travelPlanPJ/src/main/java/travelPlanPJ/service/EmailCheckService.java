package travelPlanPJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.mapper.MemberMapper;

@Service
public class EmailCheckService {
	@Autowired
	MemberMapper memberMapper;
	
	public String execute(String userEmail) {
		String resultEmail = memberMapper.selectEmailCheck(userEmail);
		
		return resultEmail;
		
	}
}
