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
import travelPlanPJ.service.BoardWriteService;
import travelPlanPJ.service.BoardAutoNumService;

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
	
	@RequestMapping(value = "communityHome", method = RequestMethod.GET)
	public String home() {
		return "thymeleaf/community/communityHome";
	}

	@RequestMapping(value = "boardList", method = RequestMethod.GET)
	public String boardList(Model model) {
		boardListService.execute(model);
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
}
