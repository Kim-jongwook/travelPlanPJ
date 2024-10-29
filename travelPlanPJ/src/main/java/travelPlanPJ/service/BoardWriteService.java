package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.command.BoardCommand;
import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class BoardWriteService {
	@Autowired
	BoardMapper boardMapper;

	public Integer execute(BoardCommand boardCommand, HttpSession session, Integer boardAutoNum) {
		BoardDTO dto = new BoardDTO();
		
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
				
		dto.setMemNum(auth.getMemNum());
		dto.setBoardNum(boardAutoNum);
		dto.setBoardName(boardCommand.getBoardName());
		dto.setBoardContent(boardCommand.getBoardContent());
		
		boardMapper.boardWrite(dto);
		
		return dto.getBoardNum();
	}
}
