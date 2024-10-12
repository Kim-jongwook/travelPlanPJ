package travelPlanPJ.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import travelPlanPJ.domain.FindDTO;

@Mapper
public interface FindMapper {
	public String findId(@Param(value = "memName")String memName, @Param(value = "memPhone") String memPhone, 
			@Param(value = "memEmail") String memEmail);
	public FindDTO findPw(@Param(value = "memId")String memId, @Param(value = "memPhone")String memPhone, 
			@Param(value = "memEmail")String memEmail);
	public void memPwUpdate(FindDTO findDTO);
	public void empPwUpdate(FindDTO findDTO);
}