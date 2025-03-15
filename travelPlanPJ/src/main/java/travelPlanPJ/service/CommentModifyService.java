package travelPlanPJ.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.command.CommentCommand;
import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.CommentDTO;
import travelPlanPJ.mapper.BoardMapper;

@Service
public class CommentModifyService {
	@Autowired
	BoardMapper boardMapper;
	
	public void execute(CommentCommand commentCommand, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
		if(auth.getMemNum().equals(commentCommand.getMemNum())) {
			CommentDTO dto = new CommentDTO();
			dto.setBoardNum(commentCommand.getBoardNum());
			dto.setMemNum(commentCommand.getMemNum());
			dto.setCommentNum(commentCommand.getCommentNum());
			dto.setCommentContent(commentCommand.getCommentContent());
			boardMapper.commentModify(dto);
		}else {
						
		}
	}
}
