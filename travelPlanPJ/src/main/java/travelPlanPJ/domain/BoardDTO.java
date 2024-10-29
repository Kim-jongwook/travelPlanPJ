package travelPlanPJ.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("board")
public class BoardDTO {
	Integer boardNum;
	String boardName;
	String boardContent;
	Date boardWriteDate;
	Date boardModDate;
	String memNum;
}
