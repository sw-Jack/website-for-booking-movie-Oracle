package movie.admin.movie.dao;

import java.util.List;

import com.oreilly.servlet.MultipartRequest;

import movie.admin.movie.dto.MovieDTO;
import movie.admin.movie.dto.ReviewDTO;

public interface MovieDAO {
	public int insertMovie(MultipartRequest mr);
	public int updateMovie(MultipartRequest mr);
	public int deleteMovie(int movie_num);
	public List<MovieDTO> movieList(int startRow, int endRow);
	public MovieDTO movieView(int movie_num);
	public int movieNavi();
	public List<MovieDTO> alllistMovie();
	public String nameMovie(int movie_num);
	public List<MovieDTO> findMovie(String search, String searchString);
	public List<MovieDTO> cmovieList();
	public MovieDTO cmovieView(int movie_num);
	public MovieDTO actorView(int movie_num);
	public List<ReviewDTO> listreview(int movie_num);
	public int insertreview(ReviewDTO dto);
	public String memberimage(String id);
	public List<ReviewDTO> avgscore(int movie_num);
	public int scoreup(int movie_num,double avg);
	public ReviewDTO getReview(int review_num);
	public int reviewUpdate(ReviewDTO dto);
	public int reviewDelete(int review_num);
	public int upresercount(int movie_num);
	
}
