package travelPlanPJ.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import travelPlanPJ.command.BoardCommand;
import travelPlanPJ.command.CommentCommand;
import travelPlanPJ.domain.ChatRoomDTO;
import travelPlanPJ.service.BoardAutoNumService;
import travelPlanPJ.service.BoardDeleteService;
import travelPlanPJ.service.BoardDetailService;
import travelPlanPJ.service.BoardListService;
import travelPlanPJ.service.BoardUpdateService;
import travelPlanPJ.service.BoardWriteService;
import travelPlanPJ.service.ChatListService;
import travelPlanPJ.service.CommentAutoNumService;
import travelPlanPJ.service.CommentDeleteService;
import travelPlanPJ.service.CommentListService;
import travelPlanPJ.service.CommentModifyService;
import travelPlanPJ.service.CommentWriteService;

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
	@Autowired
	CommentAutoNumService commentAutoNumService;
	@Autowired
	CommentWriteService commentWriteService;
	@Autowired
	CommentListService commentListService;
	@Autowired
	CommentModifyService commentModifyService;
	@Autowired
	CommentDeleteService commentDeleteService;
	@Autowired
	ChatListService chatListService;
	
	@RequestMapping(value = "communityHome", method = RequestMethod.GET)
	public String home() {
		return "thymeleaf/community/communityHome";
	}

	@RequestMapping(value = "boardList")
	public String boardList(@RequestParam(value = "searchType", required = false, defaultValue = "")String searchType,
							@RequestParam(value = "searchWord", required = false, defaultValue = "")String searchWord,
							@RequestParam(value = "page", required = false, defaultValue = "1")int page,
							Model model) {
		boardListService.execute(searchType, searchWord, model, page);
		return "thymeleaf/community/boardList";
	}
	
	@RequestMapping(value = "boardDetail", method = RequestMethod.GET)
	public String boardDetail(@RequestParam(value = "boardNum")Integer boardNum, Model model, HttpSession session) {
		boardDetailService.execute(boardNum, model, session);
		commentListService.execute(boardNum, model);
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
	public String boardModify(@RequestParam(value = "boardNum")Integer boardNum, Model model, HttpSession session) {
		boardDetailService.execute(boardNum, model, session);
		return "thymeleaf/community/boardModify";
	}
	
	@RequestMapping(value = "boardModify", method = RequestMethod.POST)
	public String boardModify(@Validated BoardCommand boardCommand, BindingResult result, HttpSession session
							  , Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			if(result.hasFieldErrors("boardName")) {
				redirectAttributes.addFlashAttribute("boardNameError", result.getFieldError("boardName").getDefaultMessage());
			}
			if(result.hasFieldErrors("boardContent")) {
				redirectAttributes.addFlashAttribute("boardContentError", result.getFieldError("boardContent").getDefaultMessage());
			}
			return "redirect:boardModify?boardNum=" + boardCommand.getBoardNum();
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
	
	@RequestMapping(value = "commentWrite", method = RequestMethod.POST)
	public String commentWrite(CommentCommand commentCommand) {
		commentCommand.setCommentNum(commentAutoNumService.execute());
		commentWriteService.execute(commentCommand); 
		return "redirect:boardDetail?boardNum=" + commentCommand.getBoardNum();
	}
	
	@RequestMapping(value = "commentModify", method = RequestMethod.POST)
		public String commentModify(CommentCommand commentCommand, HttpSession session) {
			commentModifyService.execute(commentCommand, session);
			return "redirect:boardDetail?boardNum=" + commentCommand.getBoardNum();
		}
	
	@RequestMapping(value = "commentDelete", method = RequestMethod.GET)
	public String commentDelete(Integer boardNum, Integer commentNum, String memNum, HttpSession session) {
		commentDeleteService.execute(boardNum, commentNum, memNum, session);
		return "redirect:boardDetail?boardNum=" + boardNum;
	}
	
	/* 채팅  */
	
	@RequestMapping(value = "chatHome", method = RequestMethod.GET)
	public String chatHome() {
		
		return "thymeleaf/community/chatHome";
	}
	
	@RequestMapping(value = "chat/readRooms", method = RequestMethod.GET)
	@ResponseBody
	public List<ChatRoomDTO> chatList() {
		System.out.println("chatList실행!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return chatListService.execute();
	}
	
	@RequestMapping(value = "chat/createRoom")
	public void createRoom() {
		
	}
	
	
}


























