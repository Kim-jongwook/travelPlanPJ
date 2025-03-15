package travelPlanPJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.command.CommentCommand;
import travelPlanPJ.domain.CommentDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class CommentWriteService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(CommentCommand commentCommand) {
		CommentDTO dto = new CommentDTO();
		dto.setBoardNum(commentCommand.getBoardNum());
		dto.setCommentContent(commentCommand.getCommentContent());
		dto.setCommentNum(commentCommand.getCommentNum());
		dto.setMemNum(commentCommand.getMemNum());
		
		boardMapper.commentWrite(dto);
		
	}
}
