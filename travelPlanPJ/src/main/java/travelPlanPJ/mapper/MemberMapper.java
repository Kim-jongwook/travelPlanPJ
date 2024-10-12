package travelPlanPJ.mapper;

import org.apache.ibatis.annotations.Mapper;

import travelPlanPJ.domain.AuthInfoDTO;
import travelPlanPJ.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	public String autoNum();
	public String selectIdCheck(String userId);
	public String selectEmailCheck(String userEmail);
	public int memInsert(MemberDTO dto);
	public int memCheckUpdate(String email);
	public MemberDTO selectOne(String memId);
	public int memUpdate(MemberDTO dto);
	public void memDelete(AuthInfoDTO auth);
}
