package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class BoardDetailService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(Integer boardNum, Model model, HttpSession session) {
		BoardDTO dto = boardMapper.boardDetail(boardNum);
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		if(dto != null && auth != null) {
			if( dto.getMemNum().equals(auth.getMemNum())) {
				model.addAttribute("isAuthor", true);
			}
		}
		model.addAttribute("dto", dto);
	}
	
}
