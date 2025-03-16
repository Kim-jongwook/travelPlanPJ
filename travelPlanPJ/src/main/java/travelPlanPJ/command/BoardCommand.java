package travelPlanPJ.command;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BoardCommand {
	Integer boardNum;
	
	@NotBlank(message = "제목을 입력해주세요.")
	@Size(min = 2, max = 50, message = "제목은 2자 이상 50자 이내로 입력해야 합니다.")
	String boardName;
	
	@NotBlank(message = "내용을 입력해주세요.")
	@Size(min = 2, max = 255, message = "내용은 2자 이상 255자 이내로 입력해야 합니다.")
	String boardContent;
	
	Date boardWriteDate;
	Date boardModDate;
	
	String memNum;
	String memId;
}
