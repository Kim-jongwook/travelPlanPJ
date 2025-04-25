package travelPlanPJ.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("chatMsg")
public class ChatMsgDTO {
	Integer msgNum;
	Integer chatNum;
	String memNum;
	String msgContent;
}
