package travelPlanPJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import travelPlanPJ.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	public int boardWrite(BoardDTO dto);
	public Integer boardAutoNum();
	public BoardDTO boardDetail(Integer boardNum);
	public List<BoardDTO> boardList(@Param(value = "searchType")String searchType, @Param(value = "searchWord")String searchWord);
	public void boardUpdate(BoardDTO dto);
	public void boardDelete(Integer boardNum);
}
