package travelPlanPJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import travelPlanPJ.domain.ChatRoomDTO;

@Mapper
public interface ChatMapper {
	public List<ChatRoomDTO> chatRoomList();
}
