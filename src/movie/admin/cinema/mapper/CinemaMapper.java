package movie.admin.cinema.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.admin.cinema.dto.CinemaDTO;
import movie.admin.member.dto.AdminDTO;

public class CinemaMapper {
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
	
	public static int insertCinema(CinemaDTO dto) {
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("insertCinema",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static CinemaDTO getCinema(String cinema_addr) {
		SqlSession session = sqlMapper.openSession();
		CinemaDTO dto = session.selectOne("getCinema",cinema_addr);
		session.close();
		return dto;
	}
	
	public static List<CinemaDTO> listCinema(){
		SqlSession session = sqlMapper.openSession();
		List<CinemaDTO> list = session.selectList("listCinema");
		session.close();
		return list;
	}
	
	public static CinemaDTO viewCinema(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		CinemaDTO dto = session.selectOne("viewCinema",cinema_num);
		session.close();
		return dto;
	}
	
	public static int deleteCinema(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("deleteCinema",cinema_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static int sizeupCinema(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.update("sizeupCinema",cinema_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static int sizedownCinema(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.update("sizedownCinema",cinema_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<CinemaDTO> checkCinema(String cinema_addr){
		SqlSession session = sqlMapper.openSession();
		List<CinemaDTO> list = session.selectList("checkCinema",cinema_addr);
		session.close();
		return list;
	}
	
	public static String getAddr(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		String addr = session.selectOne("getAddr",cinema_num);
		session.close();
		return addr;
	}
}
