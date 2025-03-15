package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class CommentDeleteService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(Integer boardNum, Integer commentNum, String memNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		if(auth.getMemNum().equals(memNum)) {
			boardMapper.commentDelete(boardNum, commentNum, memNum);
		}else {
			
			
		}
		
	}
}
