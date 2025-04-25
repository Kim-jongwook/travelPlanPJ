package travelPlanPJ.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("chatJoin")
public class ChatJoinDTO {
	Integer chatNum;
	String memNum;
}
