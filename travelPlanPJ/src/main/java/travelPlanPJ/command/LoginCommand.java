package travelPlanPJ.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginCommand {
	@NotBlank(message = "아이디를 입력해주세요")
	String memId;
	
	@NotEmpty(message = "비밀번호를 입력해주세요")
	String memPw;
	
	String userEmailCheck;
	
	Boolean autoLogin;
	Boolean idStore;
}
