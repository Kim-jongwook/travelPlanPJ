package travelPlanPJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.domain.PagingDTO;

@Mapper
public interface BoardMapper {
	public int boardWrite(BoardDTO dto);
	public Integer boardAutoNum();
	public BoardDTO boardDetail(Integer boardNum);
	public List<BoardDTO> boardList(PagingDTO paging);
	public void boardUpdate(BoardDTO dto);
	public void boardDelete(Integer boardNum);
	public int boardCount(PagingDTO paging);
}
