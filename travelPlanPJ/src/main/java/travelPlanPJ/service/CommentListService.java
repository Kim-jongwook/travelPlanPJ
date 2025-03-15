package travelPlanPJ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.domain.CommentDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class CommentListService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(Integer boardNum, Model model){
		
		List<CommentDTO> list = boardMapper.commentList(boardNum);
		model.addAttribute("dtos", list);
	
	}
}
