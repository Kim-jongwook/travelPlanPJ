package travelPlanPJ.command;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BoardCommand {
	Integer boardNum;
	
	@NotBlank(message = "제목을 입력해주세요.")
	String boardName;
	
	@NotBlank(message = "내용을 입력해주세요.")
	String boardContent;
	
	Date boardWriteDate;
	Date boardModDate;
	
	String memNum;
	String memId;
}
