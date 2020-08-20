package movie.admin.movie.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.admin.movie.dto.MovieDTO;
import movie.admin.movie.dto.ReviewDTO;

public class MovieMapper {

	private static SqlSessionFactory sqlMapper; //����    

	static {
		try {//xml������ java���Ϸ� �о���� ��.
			String resource = "SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			reader.close(); 
		} catch (IOException e) {
			// Fail fast.
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}
	public static int insertMovie(MovieDTO mvdto){
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("insertMovie", mvdto);
		session.commit();
		session.close();
		return res;
	}
	public static List<MovieDTO> movieList(int startRow,int endRow){
		SqlSession session = sqlMapper.openSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<MovieDTO> movieList=session.selectList("movieList",map);
		session.close();
		return movieList;
	}
	public static int deleteMovie(int movie_num){
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("deleteMovie", movie_num);
		session.commit();
		session.close();
		return res;
	}
	public static MovieDTO movieView(int movie_num){
		SqlSession session = sqlMapper.openSession();
		MovieDTO mvdto = (MovieDTO)session.selectOne("movieView", movie_num);
		session.close();
		return mvdto;
	}
	public static int updateMovie(MovieDTO mvdto){
		SqlSession session = sqlMapper.openSession();
		int res = session.update("updateMovie", mvdto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int countMovie() {
		SqlSession session = sqlMapper.openSession();
		int res = session.selectOne("countMovie");
		session.close();
		return res;
	}
	
	public static List<MovieDTO> alllistmovie(){
		SqlSession session = sqlMapper.openSession();
		List<MovieDTO> list = session.selectList("listmovie");
		session.close();
		return list;
	}
	
	public static String namemovie(int movie_num) {
		SqlSession session = sqlMapper.openSession();
		String name = session.selectOne("namemovie",movie_num);
		session.close();
		return name;
	}
	
	public static List<MovieDTO> findMovie(String search, String searchString){
		SqlSession session = sqlMapper.openSession();
		Map<String,String> map = new HashMap<String,String>();
		map.put("search", search);
		map.put("searchString", "%"+searchString+"%");
		List<MovieDTO> list = session.selectList("findmovie", map);
		return list;
	}
	
	public static List<MovieDTO> cmovieList(){
		SqlSession session = sqlMapper.openSession();
		List<MovieDTO> clist = session.selectList("cmovieList");
		return clist;
		
	}
	
	public static MovieDTO cmovieView(int movie_num){
		SqlSession session = sqlMapper.openSession();
		MovieDTO mvdto = (MovieDTO)session.selectOne("cmovieView", movie_num);
		session.close();
		return mvdto;
	}
	public static MovieDTO actorView(int movie_num){
		SqlSession session = sqlMapper.openSession();
		MovieDTO mvdto = session.selectOne("actorView", movie_num);
		session.close();
		return mvdto;
	}
	
	public static List<ReviewDTO> listreview(int movie_num){
		SqlSession session = sqlMapper.openSession();
		List<ReviewDTO> list = session.selectList("listreview",movie_num);
		session.close();
		return list;
	}
	
	public static int insertreview(ReviewDTO dto){
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("insertreview",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static String memberimage(String id){
		SqlSession session = sqlMapper.openSession();
		String image = session.selectOne("memberimage",id);
		session.close();
		return image;
	}
	
	public static List<ReviewDTO> avgscore(int movie_num){
		SqlSession session = sqlMapper.openSession();
		List<ReviewDTO> list = session.selectList("avgscore", movie_num);
		session.close();
		return list;
	}
	
	public static int scoreup(int movie_num,double avg) {
		SqlSession session = sqlMapper.openSession();
		Map map = new HashMap();
		map.put("movie_num", movie_num);
		map.put("score", avg);
		int res = session.update("scoreup",map);
		session.commit();
		session.close();
		return res;
		
	}
	
	public static ReviewDTO getReview(int review_num){
		SqlSession session = sqlMapper.openSession();
		ReviewDTO dto = session.selectOne("getReview",review_num);
		session.close();
		return dto;
	}
	
	public static int updateReview(ReviewDTO dto) {
		SqlSession session = sqlMapper.openSession();
		int res = session.update("updateReview",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int deleteReview(int review_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("deleteReview",review_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static int upresercount(int movie_num){
		SqlSession session = sqlMapper.openSession();
		int res = session.update("upresercount",movie_num);
		session.commit();
		session.close();
		return res;
	}
}

