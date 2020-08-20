package movie.admin.board.dao;

import java.util.List;

import movie.admin.board.dto.NoticeDTO;

public interface BoardDAO {
	public int insertNotice(NoticeDTO dto);
	public List<NoticeDTO> listNotice();
	public NoticeDTO getNotice(int notice_num);
	public int deleteNotice(int notice_num);
	public int updateNotice(NoticeDTO dto);
	//공지사항

}
