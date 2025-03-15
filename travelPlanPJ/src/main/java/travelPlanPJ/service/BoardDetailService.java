package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import travelPlanPJ.command.BoardCommand;
import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class BoardDetailService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(Integer boardNum, Model model, HttpSession session) {
		BoardDTO dto = boardMapper.boardDetail(boardNum);
		BoardCommand boardCommand = new BoardCommand();
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		if(dto != null && auth != null) {
			if( dto.getMemNum().equals(auth.getMemNum())) {
				model.addAttribute("boardAuthor", true);
			}
		}
		boardCommand.setBoardContent(dto.getBoardContent());
		boardCommand.setBoardModDate(dto.getBoardModDate());
		boardCommand.setBoardName(dto.getBoardName());
		boardCommand.setBoardNum(dto.getBoardNum());
		boardCommand.setBoardWriteDate(dto.getBoardWriteDate());
		boardCommand.setMemNum(dto.getMemNum());
		boardCommand.setMemId(dto.getMemId());
		model.addAttribute("boardCommand", boardCommand);
	}
}
