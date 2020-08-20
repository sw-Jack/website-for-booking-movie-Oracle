package movie.admin.board.dao;

import java.util.List;

import movie.admin.board.dto.NoticeDTO;
import movie.admin.board.mapper.BoardMapper;

public class BoardDAOImpl implements BoardDAO{

	@Override
	public int insertNotice(NoticeDTO dto) {
		// TODO Auto-generated method stub
		return BoardMapper.insertNotice(dto);
	}

	@Override
	public List<NoticeDTO> listNotice() {
		// TODO Auto-generated method stub
		return BoardMapper.listNotice();
	}

	@Override
	public NoticeDTO getNotice(int notice_num) {
		// TODO Auto-generated method stub
		return BoardMapper.getNotice(notice_num);
	}

	@Override
	public int deleteNotice(int notice_num) {
		// TODO Auto-generated method stub
		return BoardMapper.deleteNotice(notice_num);
	}

	@Override
	public int updateNotice(NoticeDTO dto) {
		// TODO Auto-generated method stub
		return BoardMapper.updateNotice(dto);
	}
	//공지사항

}
