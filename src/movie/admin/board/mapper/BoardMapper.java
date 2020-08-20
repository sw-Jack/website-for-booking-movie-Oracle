package movie.admin.board.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.admin.board.dto.NoticeDTO;

public class BoardMapper {
	private static SqlSessionFactory sqlMapper;
	// 시작을 나타낼때 씀
	static {
		try {//xml파일을 자바에서 읽어오게끔해줌
			String resource = "SqlMapConfig.xml"; 
			Reader reader = Resources.getResourceAsReader(resource); 
			sqlMapper = new SqlSessionFactoryBuilder().build(reader); 
			reader.close();
		} catch (IOException e) {
			// Fail fast.
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}
	
	public static int insertNotice(NoticeDTO dto){
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("noticeInsert",dto);
		session.commit();
		session.close();
		return res;
	}
	public static List<NoticeDTO> listNotice(){
		SqlSession session = sqlMapper.openSession();
		List<NoticeDTO> list = session.selectList("noticeList");
		session.close();
		return list;
	}
	public static NoticeDTO getNotice(int notice_num){
		SqlSession session = sqlMapper.openSession();
		NoticeDTO dto = session.selectOne("noticeGet",notice_num);
		session.close();
		return dto;
	}
	public static int deleteNotice(int notice_num){
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("noticeDelete",notice_num);
		session.commit();
		session.close();
		return res;
	}
	public static int updateNotice(NoticeDTO dto){
		SqlSession session = sqlMapper.openSession();
		int res = session.update("noticeUpdate",dto);
		session.commit();
		session.close();
		return res;
	}
	//공지사항
}
