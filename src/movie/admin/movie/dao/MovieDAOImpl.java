package movie.admin.movie.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.SystemPropertyUtils;

import com.oreilly.servlet.MultipartRequest;

import movie.admin.movie.dto.MovieDTO;
import movie.admin.movie.dto.ReviewDTO;
import movie.admin.movie.mapper.MovieMapper;

public class MovieDAOImpl implements MovieDAO{

	@Override
	public int insertMovie(MultipartRequest mr) {
		MovieDTO mvdto = new MovieDTO();
		mvdto.setTitle(mr.getParameter("title"));
		mvdto.setDirector(mr.getParameter("director"));
		mvdto.setGenre(mr.getParameter("genre"));
		mvdto.setActor(mr.getParameter("actor"));
		mvdto.setActor2(mr.getParameter("actor2"));
		mvdto.setActor3(mr.getParameter("actor3"));
		mvdto.setActor4(mr.getParameter("actor4"));
		mvdto.setActor5(mr.getParameter("actor5"));
		mvdto.setActor6(mr.getParameter("actor6"));
		mvdto.setStory(mr.getParameter("story"));
		mvdto.setGrade(mr.getParameter("grade"));
		mvdto.setTime(mr.getParameter("time"));
		mvdto.setOpendate_year(Integer.parseInt(mr.getParameter("opendate_year")));
		mvdto.setOpendate_month(Integer.parseInt(mr.getParameter("opendate_month")));
		mvdto.setOpendate_day(Integer.parseInt(mr.getParameter("opendate_day")));
		mvdto.setTrailer(mr.getParameter("trailer"));
		String state = "";
		if(mr.getParameter("2d")!=null) {
			state = mr.getParameter("2d");
		}
		if(mr.getParameter("3d")!=null) {
			state = state+","+mr.getParameter("3d");
		}
		if(mr.getParameter("4dx")!=null) {
			state = state+","+mr.getParameter("4dx");
		}
		if(mr.getParameter("imax")!=null) {
			state = state+","+mr.getParameter("imax");
		}
		mvdto.setState(state);
		String imsi_image = mr.getFilesystemName("image");
		if(imsi_image==null) {
			imsi_image = "sorry.png";
		}
		mvdto.setImage(imsi_image);
		String imsi_name1 = mr.getFilesystemName("actorimage1");
		String imsi_name2 = mr.getFilesystemName("actorimage2");
		String imsi_name3 = mr.getFilesystemName("actorimage3");
		String imsi_name4 = mr.getFilesystemName("actorimage4");
		String imsi_name5 = mr.getFilesystemName("actorimage5");
		String imsi_name6 = mr.getFilesystemName("actorimage6");
		if(imsi_name1 == null) {
			imsi_name1 = "basicimage.jpg";
		}
		if(imsi_name2 == null) {
			imsi_name2 = "basicimage.jpg";
		}
		if(imsi_name3 == null) {
			imsi_name3 = "basicimage.jpg";
		}
		if(imsi_name4 == null) {
			imsi_name4 = "basicimage.jpg";
		}
		if(imsi_name5 == null) {
			imsi_name5 = "basicimage.jpg";
		}
		if(imsi_name6 == null) {
			imsi_name6 = "basicimage.jpg";
		}
		mvdto.setActorimage1(imsi_name1);
		mvdto.setActorimage2(imsi_name2);
		mvdto.setActorimage3(imsi_name3);
		mvdto.setActorimage4(imsi_name4);
		mvdto.setActorimage5(imsi_name5);
		mvdto.setActorimage6(imsi_name6);
		mvdto.setActor1role(mr.getParameter("actor1role"));
		mvdto.setActor2role(mr.getParameter("actor2role"));
		mvdto.setActor3role(mr.getParameter("actor3role"));
		mvdto.setActor4role(mr.getParameter("actor4role"));
		mvdto.setActor5role(mr.getParameter("actor5role"));
		mvdto.setActor6role(mr.getParameter("actor6role"));
		mvdto.setActor1past(mr.getParameter("actor1past"));
		mvdto.setActor2past(mr.getParameter("actor2past"));
		mvdto.setActor3past(mr.getParameter("actor3past"));
		mvdto.setActor4past(mr.getParameter("actor4past"));
		mvdto.setActor5past(mr.getParameter("actor5past"));
		mvdto.setActor6past(mr.getParameter("actor6past"));
		return MovieMapper.insertMovie(mvdto);
	}

	@Override
	public int updateMovie(MultipartRequest mr) {
		MovieDTO mvdto = new MovieDTO();
		mvdto.setMovie_num(Integer.parseInt(mr.getParameter("movie_num")));
		mvdto.setTitle(mr.getParameter("title"));
		mvdto.setDirector(mr.getParameter("director"));
		mvdto.setGenre(mr.getParameter("genre"));
		mvdto.setActor(mr.getParameter("actor"));
		mvdto.setActor2(mr.getParameter("actor2"));
		mvdto.setActor3(mr.getParameter("actor3"));
		mvdto.setActor4(mr.getParameter("actor4"));
		mvdto.setActor5(mr.getParameter("actor5"));
		mvdto.setActor6(mr.getParameter("actor6"));
		mvdto.setStory(mr.getParameter("story"));
		mvdto.setGrade(mr.getParameter("grade"));
		mvdto.setTime(mr.getParameter("time"));
		mvdto.setOpendate_year(Integer.parseInt(mr.getParameter("opendate_year")));
		mvdto.setOpendate_month(Integer.parseInt(mr.getParameter("opendate_month")));
		mvdto.setOpendate_day(Integer.parseInt(mr.getParameter("opendate_day")));
		mvdto.setTrailer(mr.getParameter("trailer"));
		String state = "";
		if(mr.getParameter("2d")!=null) {
			state = mr.getParameter("2d");
		}
		if(mr.getParameter("3d")!=null) {
			state = state+","+mr.getParameter("3d");
		}
		if(mr.getParameter("4dx")!=null) {
			state = state+","+mr.getParameter("4dx");
		}
		if(mr.getParameter("imax")!=null) {
			state = state+","+mr.getParameter("imax");
		}
		mvdto.setState(state);
		String imsi_image = mr.getFilesystemName("image");
		String imsi_name1 = mr.getFilesystemName("actorimage1");
		String imsi_name2 = mr.getFilesystemName("actorimage2");
		String imsi_name3 = mr.getFilesystemName("actorimage3");
		String imsi_name4 = mr.getFilesystemName("actorimage4");
		String imsi_name5 = mr.getFilesystemName("actorimage5");
		String imsi_name6 = mr.getFilesystemName("actorimage6");
		if(imsi_image == null) {
			imsi_image = mr.getParameter("image2");
		}
		if(imsi_name1 == null) {
			imsi_name1 = mr.getParameter("bactorimage1");
		}
		if(imsi_name2 == null) {
			imsi_name2 = mr.getParameter("bactorimage2");
		}
		if(imsi_name3 == null) {
			imsi_name3 = mr.getParameter("bactorimage3");
		}
		if(imsi_name4 == null) {
			imsi_name4 = mr.getParameter("bactorimage4");
		}
		if(imsi_name5 == null) {
			imsi_name5 = mr.getParameter("bactorimage5");
		}
		if(imsi_name6 == null) {
			imsi_name6 = mr.getParameter("bactorimage6");
		}

		mvdto.setImage(imsi_image);
		mvdto.setActorimage1(imsi_name1);
		mvdto.setActorimage2(imsi_name2);
		mvdto.setActorimage3(imsi_name3);
		mvdto.setActorimage4(imsi_name4);
		mvdto.setActorimage5(imsi_name5);
		mvdto.setActorimage6(imsi_name6);
		mvdto.setActor1role(mr.getParameter("actor1role"));
		mvdto.setActor2role(mr.getParameter("actor2role"));
		mvdto.setActor3role(mr.getParameter("actor3role"));
		mvdto.setActor4role(mr.getParameter("actor4role"));
		mvdto.setActor5role(mr.getParameter("actor5role"));
		mvdto.setActor6role(mr.getParameter("actor6role"));
		mvdto.setActor1past(mr.getParameter("actor1past"));
		mvdto.setActor2past(mr.getParameter("actor2past"));
		mvdto.setActor3past(mr.getParameter("actor3past"));
		mvdto.setActor4past(mr.getParameter("actor4past"));
		mvdto.setActor5past(mr.getParameter("actor5past"));
		mvdto.setActor6past(mr.getParameter("actor6past"));
		return MovieMapper.updateMovie(mvdto);
	}

	@Override
	public int deleteMovie(int movie_num) {
		// TODO Auto-generated method stub
		return MovieMapper.deleteMovie(movie_num);
	}

	@Override
	public List<MovieDTO> movieList(int startRow, int endRow) {
		return MovieMapper.movieList(startRow,endRow);
	}

	@Override
	public MovieDTO movieView(int movie_num) {
		
		return MovieMapper.movieView(movie_num);
	}

	@Override
	public int movieNavi() {
		return MovieMapper.countMovie();
	}

	@Override
	public List<MovieDTO> alllistMovie() {
		// TODO Auto-generated method stub
		return MovieMapper.alllistmovie();
	}

	@Override
	public String nameMovie(int movie_num) {
		// TODO Auto-generated method stub
		return MovieMapper.namemovie(movie_num);
	}
	
	@Override
	public List<MovieDTO> findMovie(String search, String searchString) {
		// TODO Auto-generated method stub
		return MovieMapper.findMovie(search, searchString);
	}
	
	@Override
	public List<MovieDTO> cmovieList() {
		// TODO Auto-generated method stub
		return MovieMapper.cmovieList();
	}

	@Override
	public MovieDTO cmovieView(int movie_num) {
		// TODO Auto-generated method stub
		return MovieMapper.cmovieView(movie_num);
	}

	@Override
	public MovieDTO actorView(int movie_num) {
		// TODO Auto-generated method stub
		return MovieMapper.actorView(movie_num);
	}
	
	@Override
	public List<ReviewDTO> listreview(int movie_num) {
		// TODO Auto-generated method stub
		return MovieMapper.listreview(movie_num);
	}

	@Override
	public int insertreview(ReviewDTO dto) {
		// TODO Auto-generated method stub
		return MovieMapper.insertreview(dto);
	}

	@Override
	public String memberimage(String id) {
		// TODO Auto-generated method stub
		return MovieMapper.memberimage(id);
	}

	@Override
	public List<ReviewDTO> avgscore(int movie_num) {
		// TODO Auto-generated method stub
		List<ReviewDTO> list = MovieMapper.avgscore(movie_num);
		return list;
	}

	@Override
	public int scoreup(int movie_num, double avg) {
		// TODO Auto-generated method stub
		return MovieMapper.scoreup(movie_num, avg);
	}

	@Override
	public ReviewDTO getReview(int review_num) {
		// TODO Auto-generated method stub
		return MovieMapper.getReview(review_num);
	}

	@Override
	public int reviewUpdate(ReviewDTO dto) {
		// TODO Auto-generated method stub
		return MovieMapper.updateReview(dto);
	}

	@Override
	public int reviewDelete(int review_num) {
		// TODO Auto-generated method stub
		return MovieMapper.deleteReview(review_num);
	}

	@Override
	public int upresercount(int movie_num) {
		// TODO Auto-generated method stub
		return MovieMapper.upresercount(movie_num);
	}
}
