package travelPlanPJ.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.domain.PagingDTO;

@Service
public class PagingService {
	int limit;
	
	public PagingDTO execute(int page, String searchType, String searchWord) {
		
		PagingDTO paging = new PagingDTO();
		
		limit = 10;
		int startRow = ((page - 1) * limit) + 1;
		int endRow = startRow + limit - 1;
		
		paging.setEndRow(endRow);
		paging.setSearchType(searchType);
		paging.setSearchWord(searchWord);
		paging.setStartRow(startRow);
		
		return paging;
		
	}
	
	public void execute(int page, int count, Model model, String searchType, String searchWord) {
		
		int limitPage = 5;
		int startPage = (page - 1) / limitPage * limitPage + 1;
		int endPage = startPage + limitPage - 1;
		int maxPage = count / limit;

		if(count % limit != 0) {
			maxPage = maxPage + 1;
		}

		if(maxPage < endPage) {
			endPage = maxPage;
		}		
		
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("searchType", searchType);
		model.addAttribute("count", count);
		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("maxPage", maxPage);
		
	}
}
