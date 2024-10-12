package travelPlanPJ.command;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	String memNum;
	
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9]).{6,12}$", message= "영문,숫자가 포함된 6~12자리 이어야 합니다.")
	String memId;
	
	@NotBlank(message = "성명을 입력해주세요.")
	@Size(min = 2, max = 10)
	String memName;
	
	@Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[!@#$%^&*(),.]).{10,16}$", message = "영문, 숫자, 특수문자가 포함된 10~16자리 이어야 합니다.")
	String memPw;
	
	@NotBlank(message = "비밀번호 확인을 입력해주세요.")
	String memPwCon;
	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "생년월일을 입력해주세요.")
	Date memBirth;
	
	String memGender;
	
	@NotBlank(message = "전화번호를 입력해주세요.")
	String memPhone;
	
	@NotBlank(message = "이메일을 입력해주세요.")
	String memEmail;
	
	String memEmailConf;
	
	@NotBlank(message = "주소를 입력해주세요.")
	String memAddr;
	
	@NotBlank(message = "상세 주소를 입력해주세요.")
	String memAddrDetail;
	
	String memPost;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date memRegist;
	
	public boolean isMemPwEqualsMemPwCon() {
		return memPw.equals(memPwCon);
	}
}
