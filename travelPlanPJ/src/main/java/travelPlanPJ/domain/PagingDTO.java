package travelPlanPJ.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("paging")
public class PagingDTO {
	String searchType;
	String searchWord;
	int startRow;
	int endRow;
}
