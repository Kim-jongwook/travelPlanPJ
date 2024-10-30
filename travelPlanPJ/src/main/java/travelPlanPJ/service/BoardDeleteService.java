package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class BoardDeleteService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(Integer boardNum, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		BoardDTO dto = boardMapper.boardDetail(boardNum);
		
		if(dto != null && auth != null) {
			if(dto.getMemNum().equals(auth.getMemId())) {
				boardMapper.boardDelete(dto.getBoardNum());
			}
		}
	}
}
