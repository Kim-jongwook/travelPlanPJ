package travelPlanPJ.command;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class FindPwCommand {
	
	@NotBlank(message = "아이디를 입력해주세요.")
	String memId;
	
	@NotBlank(message = "등록하신 전화번호를 입력해주세요.")
	String memPhone;
	
	@NotBlank(message = "등록하신 이메일을 입력해주세요.")
	String memEmail;
	
	String memPw;
}
