package travelPlanPJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import travelPlanPJ.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	public int boardWrite(BoardDTO dto);
	public Integer boardAutoNum();
	public BoardDTO boardDetail(Integer boardNum);
	public List<BoardDTO> boardList();
}
