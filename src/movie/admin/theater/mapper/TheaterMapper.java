package movie.admin.theater.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.admin.theater.dto.TheaterDTO;
import movie.admin.theater.dto.Theater_playDTO;
import movie.admin.theater.dto.Theater_seatDTO;

public class TheaterMapper {
	private static SqlSessionFactory sqlMapper;
	// ������ ��Ÿ���� ��
	static {
		try {//xml������ �ڹٿ��� �о���Բ�����
			String resource = "SqlMapConfig.xml"; 
			Reader reader = Resources.getResourceAsReader(resource); 
			sqlMapper = new SqlSessionFactoryBuilder().build(reader); 
			reader.close();
		} catch (IOException e) {
			// Fail fast.
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}
	
	public static int insertTheater(TheaterDTO dto) {
		SqlSession session = sqlMapper.openSession();
		dto.setTheater_seatcount(dto.getSeat_row()*dto.getSeat_num());
		int res = session.insert("insertTheater",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<TheaterDTO> listTheater(int cinema_num){
		SqlSession session = sqlMapper.openSession();
		List<TheaterDTO> list = session.selectList("listTheater",cinema_num);
		session.close();
		return list;
	}
	
	public static int deleteTheater(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("deleteTheater",cinema_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<TheaterDTO> alllistTheater(){
		SqlSession session = sqlMapper.openSession();
		List<TheaterDTO> list = session.selectList("alllistTheater");
		session.close();
		return list;
	}
	
	public static List<Theater_playDTO> timecalc(int theater_stage,int play_year,int play_month,int play_day,int cinema_num){
		SqlSession session = sqlMapper.openSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("theater_stage", theater_stage);
		map.put("play_year", play_year);
		map.put("play_month", play_month);
		map.put("play_day", play_day);
		map.put("cinema_num", cinema_num);
		List<Theater_playDTO> list = session.selectList("timecalc",map);
		session.close();
		return list;
	}
	
	public static int insertTheaterplay(Theater_playDTO dto){
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("insertTheaterplay",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static TheaterDTO getTheater(int cinema_num, int theater_stage) {
		SqlSession session = sqlMapper.openSession();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("cinema_num", cinema_num);
		map.put("theater_stage", theater_stage);
		TheaterDTO dto = session.selectOne("getTheater",map);
		session.commit();
		session.close();
		return dto;
	}
	
	public static int deleteTheater1(int cinema_num, int theater_stage) {
		SqlSession session = sqlMapper.openSession();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("cinema_num", cinema_num);
		map.put("theater_stage", theater_stage);
		int res = session.delete("deleteTheater1", map);
		session.commit();
		session.close();
		return res;
	}
	
	public static int updateTheater(TheaterDTO dto) {
		SqlSession session = sqlMapper.openSession();
		dto.setTheater_seatcount(dto.getSeat_num()*dto.getSeat_row());
		int res = session.update("updateTheater", dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int countTheater(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		int count = session.selectOne("countTheater",cinema_num);
		session.close();
		return count;
	}
	
	public static List<TheaterDTO> checkTheater(int cinema_num,int theater_stage){
		SqlSession session = sqlMapper.openSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("cinema_num", cinema_num);
		map.put("theater_stage", theater_stage);
		List<TheaterDTO> list = session.selectList("checkTheater",map);
		session.close();
		return list;
	}
	
	public static int getSize(int cinema_num){
		SqlSession session = sqlMapper.openSession();
		int size = session.selectOne("getSize",cinema_num);
		session.close();
		return size;
	}
	
	public static int deletePlay(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("deletePlay",cinema_num);
		session.close();
		return res;
	}
	
	public static List<Theater_playDTO> getplay(int play_year,int play_month,int play_day,int cinema_num,int movie_num){
		SqlSession session = sqlMapper.openSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("play_year", play_year);
		map.put("play_month", play_month);
		map.put("play_day", play_day);
		map.put("cinema_num", cinema_num);
		map.put("movie_num", movie_num);
		List<Theater_playDTO> list = session.selectList("getplay",map);
		session.close();
		return list;
	}
	
	public static List<Theater_playDTO> listtheaterplay(){
		SqlSession session = sqlMapper.openSession();
		List<Theater_playDTO> list = session.selectList("listtheaterplay");
		session.close();
		return list;
	}
	
	public static Theater_playDTO getreserinfo(int play_num){
		SqlSession session = sqlMapper.openSession();
		Theater_playDTO dto = session.selectOne("getreserinfo",play_num);
		session.close();
		return dto;
	}
	
	public static int deleteplay(int movie_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("deleteplay",movie_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<Theater_seatDTO> soldseat(int play_num){
		SqlSession session = sqlMapper.openSession();
		List<Theater_seatDTO> list = session.selectList("soldseat",play_num);
		session.close();
		return list;
	}
	
	public static List<Theater_playDTO> listplayingmovie(int play_year,int play_month,int play_day,int cinema_num){
		SqlSession session = sqlMapper.openSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("play_year", play_year);
		map.put("play_month", play_month);
		map.put("play_day", play_day);
		map.put("cinema_num", cinema_num);
		List<Theater_playDTO> list = session.selectList("listplayingmovie",map);
		session.close();
		return list;
	}
	
	public static List<Theater_playDTO> getplist(int movie_num,int cinema_num){
		SqlSession session = sqlMapper.openSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("movie_num", movie_num);
		map.put("cinema_num", cinema_num);
		List<Theater_playDTO> list = session.selectList("getplist",map);
		session.close();
		return list;
	}
	
	public static int sellseat(Theater_seatDTO dto){
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("sellseat",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int refundseat(int play_num,int seat_row,int seat_num) {
		SqlSession session = sqlMapper.openSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("play_num", play_num);
		map.put("seat_row", seat_row);
		map.put("seat_num", seat_num);
		int res= session.delete("refundseat",map);
		session.commit();
		session.close();
		return res;
	}
}
