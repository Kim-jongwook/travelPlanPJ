package travelPlanPJ.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("comment")
public class CommentDTO {
	Integer commentNum;
	Integer boardNum;
	String commentContent;
	Date commentDate;
	String memNum;
	String memId;
}
