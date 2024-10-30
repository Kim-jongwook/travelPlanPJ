package travelPlanPJ.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import travelPlanPJ.command.BoardCommand;
import travelPlanPJ.service.BoardDetailService;
import travelPlanPJ.service.BoardListService;
import travelPlanPJ.service.BoardUpdateService;
import travelPlanPJ.service.BoardWriteService;
import travelPlanPJ.service.BoardAutoNumService;
import travelPlanPJ.service.BoardDeleteService;

@Controller
@RequestMapping(value = "community")
public class CommunityController {
	@Autowired
	BoardWriteService boardWriteService;
	@Autowired
	BoardAutoNumService boardAutoNumService;
	@Autowired
	BoardDetailService boardDetailService;
	@Autowired
	BoardListService boardListService;
	@Autowired
	BoardUpdateService boardUpdateService;
	@Autowired
	BoardDeleteService boardDeleteService;
	
	@RequestMapping(value = "communityHome", method = RequestMethod.GET)
	public String home() {
		return "thymeleaf/community/communityHome";
	}

	@RequestMapping(value = "boardList")
	public String boardList(@RequestParam(value = "searchType", required = false)String searchType,
							@RequestParam(value = "searchWord", required = false)String searchWord, 
							Model model) {
		boardListService.execute(searchType, searchWord, model);
		return "thymeleaf/community/boardList";
	}
	
	@RequestMapping(value = "boardDetail", method = RequestMethod.GET)
	public String boardDetail(@RequestParam(value = "boardNum")Integer boardNum, Model model, HttpSession session) {
		boardDetailService.execute(boardNum, model, session);
		return "thymeleaf/community/boardDetail";
	}
	
	@RequestMapping(value = "boardWrite", method = RequestMethod.GET)
	public String boardWrite(@ModelAttribute("boardCommand")BoardCommand boardCommand) {
		return "thymeleaf/community/boardForm";
	}
	
	@RequestMapping(value = "boardWrite", method = RequestMethod.POST)
	public String boardWrite(@Validated BoardCommand boardCommand, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/community/boardForm";
		}
		Integer boardAutoNum = boardAutoNumService.execute();
		Integer boardNum = boardWriteService.execute(boardCommand, session, boardAutoNum);
		return "redirect:boardDetail?boardNum=" + boardNum;
	}
	
	@RequestMapping(value = "boardModify", method = RequestMethod.GET)
	public String boardModify(@RequestParam(value = "boardNum")Integer boardNum, @Validated BoardCommand boardCommand, BindingResult result, Model model, HttpSession session) {
		boardDetailService.execute(boardNum, model, session);
		return "thymeleaf/community/boardModify";
	}
	
	@RequestMapping(value = "boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(@Validated BoardCommand boardCommand, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "thymeleaf/community/boardModify";
		}
		boardUpdateService.execute(boardCommand, session);
		return "redirect:boardDetail?boardNum=" + boardCommand.getBoardNum();
	}
	
	@RequestMapping(value = "boardDelete", method = RequestMethod.GET)
	public String boardDelete(@RequestParam(value = "boardNum")Integer boardNum, Model model) {
		model.addAttribute("boardNum", boardNum);
		return "thymeleaf/community/boardDelete";
	}
	
	@RequestMapping(value = "boardDelete", method = RequestMethod.POST)
	public String boardDelete(@RequestParam(value = "boardNum")Integer boardNum, HttpSession session) {
		boardDeleteService.execute(boardNum, session);
		return "redirect:boardList";
	}
}
