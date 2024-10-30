package travelPlanPJ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class BoardListService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(String searchType, String searchWord, Model model) {
		List<BoardDTO> list = boardMapper.boardList(searchType, searchWord);
		System.out.println(list);
		model.addAttribute("dtos", list);
		
	}
}
