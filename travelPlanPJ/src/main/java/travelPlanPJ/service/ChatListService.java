package travelPlanPJ.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelPlanPJ.domain.ChatRoomDTO;
import travelPlanPJ.mapper.ChatMapper;

@Service
public class ChatListService {
	@Autowired
	ChatMapper chatMapper;
	
	public List<ChatRoomDTO> execute(){
		
		List<ChatRoomDTO> list = chatMapper.chatRoomList();
		
		return list;
	}
}
