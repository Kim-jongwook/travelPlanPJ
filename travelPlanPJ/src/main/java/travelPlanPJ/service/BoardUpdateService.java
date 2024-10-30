package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.command.BoardCommand;
import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class BoardUpdateService {
	@Autowired
	BoardMapper boardMapper;
	public void execute(BoardCommand boardCommand, HttpSession session) {
		BoardDTO dto = new BoardDTO();
		dto.setMemNum(boardCommand.getMemNum()); //아이디형식
		dto.setBoardNum(boardCommand.getBoardNum());
		dto.setBoardName(boardCommand.getBoardName());
		dto.setBoardContent(boardCommand.getBoardContent());
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		
		if(dto != null && auth != null) {
			if(dto.getMemNum().equals(auth.getMemId())) {
				//이때 매퍼 실행
				boardMapper.boardUpdate(dto);
			}else {
				// 다를 때
			}
		}
	}
}
