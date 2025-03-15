package travelPlanPJ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.mapper.BoardMapper;

@Service
public class CommentAutoNumService {
	@Autowired
	BoardMapper boardMapper;
	
	public Integer execute() {
		Integer i = boardMapper.commentAutoNum();
		
		return i;
	}

}
