package travelPlanPJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.mapper.MemberMapper;

@Service
public class IdCheckService {
	@Autowired
	MemberMapper memberMapper;
	
	public String execute(String userId) {
		String resultId = memberMapper.selectIdCheck(userId);
		return resultId;
	} 
}
