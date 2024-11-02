package travelPlanPJ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.domain.PagingDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class BoardListService {
	@Autowired
	BoardMapper boardMapper;
	@Autowired
	PagingService pagingService;
	
	public void execute(String searchType, String searchWord, Model model, int page) {
		if(searchWord != null && !searchWord.equals("")) {
			searchWord = searchWord.trim();
		}
		PagingDTO paging = pagingService.execute(page, searchType, searchWord);
		
		List<BoardDTO> list = boardMapper.boardList(paging);
		model.addAttribute("dtos", list);

		int count = boardMapper.boardCount(paging);
		pagingService.execute(page, count, model, searchType, searchWord);
	}
}
