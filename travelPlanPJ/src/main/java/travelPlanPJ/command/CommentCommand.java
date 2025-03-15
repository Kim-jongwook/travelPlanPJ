package travelPlanPJ.command;

import java.util.Date;
import lombok.Data;

@Data
public class CommentCommand {
	Integer commentNum;
	Integer boardNum;
	String commentContent;
	Date commentDate;
	String memNum;
	
}
