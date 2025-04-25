package travelPlanPJ.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "chatRoom")
public class ChatRoomDTO {
	Integer chatNum;
	String chatName;
	Boolean chatState;
}
