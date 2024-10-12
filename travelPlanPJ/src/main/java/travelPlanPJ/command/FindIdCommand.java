package travelPlanPJ.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class FindIdCommand {
	
	@NotBlank(message = "이름을 입력해주세요.")
	String memName;
	
	@NotBlank(message = "등록하신 전화번호를 입력해주세요.")
	String memPhone;
	
	@NotEmpty(message = "등록하신 이메일을 입력해주세요.")
	String memEmail;
}
