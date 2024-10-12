package travelPlanPJ.mapper;

import org.apache.ibatis.annotations.Mapper;

import travelPlanPJ.domain.AuthInfoDTO;

@Mapper
public interface LoginMapper {
	public AuthInfoDTO loginSelect(String memId);
}
