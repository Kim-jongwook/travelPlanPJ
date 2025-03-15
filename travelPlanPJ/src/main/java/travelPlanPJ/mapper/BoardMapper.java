package travelPlanPJ.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import travelPlanPJ.domain.BoardDTO;
import travelPlanPJ.domain.CommentDTO;
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
	public Integer commentAutoNum();
	public void commentWrite(CommentDTO dto);
	public List<CommentDTO> commentList(Integer boardNum);
	public void commentModify(CommentDTO dto);
	public void commentDelete(@Param(value = "boardNum")Integer boardNum, @Param(value = "commentNum")Integer commentNum
							 ,@Param(value = "memNum")String memNum);
}
