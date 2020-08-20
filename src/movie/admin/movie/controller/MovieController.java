package movie.admin.movie.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;

import movie.admin.member.dao.MemberDAO;
import movie.admin.member.dto.MemberDTO;
import movie.admin.movie.dao.MovieDAO;
import movie.admin.movie.dto.MovieDTO;
import movie.admin.movie.dto.ReviewDTO;

@Controller
public class MovieController {
	@Autowired
	public MovieDAO movieDAO;
	@Autowired
	public MemberDAO memberDAO;

	@RequestMapping(value="/movie_insert.mo")
	public ModelAndView insertMovie(HttpServletRequest req, HttpServletResponse resp)throws Exception{
		return new ModelAndView("admin/movie/insert_movie");
	}
	@RequestMapping(value="/movie_insertP.mo", method=RequestMethod.POST)
	public ModelAndView insertMoviePro(HttpServletRequest req) throws Exception{
		MultipartRequest mr = null;
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		int len = 50*1024*1024;
		mr = new MultipartRequest(req, upPath, len, "UTF-8");
		int res = movieDAO.insertMovie(mr);
		return new ModelAndView("redirect:movie_list.mo");
	}
	@RequestMapping(value="/movie_list.mo")
	public ModelAndView movieList(HttpServletRequest req, HttpServletResponse resp){
		int pageSize=4;
		String pageNum = req.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = currentPage * pageSize -(pageSize-1);
		int endRow = startRow + pageSize -1;
		int count = (int)movieDAO.movieNavi();
		if(endRow>count) endRow = count;
		int pnum = count - (currentPage-1)*pageSize;
		List<MovieDTO> movieList = movieDAO.movieList(startRow, endRow);
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav = new ModelAndView("admin/movie/movieList");
		if(count>0){
			int pageCount=count/pageSize + (count%pageSize==0? 0:1);
			int pageBlock =3;
			int startPage=(currentPage-1)/pageBlock*pageBlock +1;
			int endPage = startPage + pageBlock -1;
			if(endPage>pageCount) endPage = pageCount;
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageCount", pageCount);
			mav.addObject("pageBlock", pageBlock);
		}
		mav.addObject("pnum", pnum);
		mav.addObject("movieList", movieList);
		mav.addObject("image", upPath);
		mav.addObject("count", count);
		mav.addObject("pageSize", pageSize);
		mav.addObject("currentPage", currentPage);
		return mav;
	}
	@RequestMapping(value="/movie_delete.mo")
	public ModelAndView deleteMovie(HttpServletRequest req, HttpServletResponse resp){
		String movie_num = req.getParameter("movie_num");
		int res = movieDAO.deleteMovie(Integer.parseInt(movie_num));
		return new ModelAndView("redirect:movie_list.mo");
	}
	@RequestMapping(value="/movie_view.mo")
	public ModelAndView movieView(HttpServletRequest req, HttpServletResponse resp){
		int movie_num = Integer.parseInt(req.getParameter("movie_num"));
		MovieDTO mvdto = movieDAO.movieView(movie_num);
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav = new ModelAndView("admin/movie/movie_view");
		mav.addObject("mvdto", mvdto);
		mav.addObject("image", upPath);
		mav.addObject("movie_num", movie_num);
		return mav;
	}
	@RequestMapping(value="/movie_update.mo")
	public ModelAndView updateMovie(HttpServletRequest req, @RequestParam int movie_num){
		MovieDTO mvdto = movieDAO.movieView(movie_num);
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav = new ModelAndView("admin/movie/update_movie");
		mav.addObject("mvdto", mvdto);
		mav.addObject("upPath", upPath);
		return mav;
	}
	@RequestMapping(value="/movie_updateP.mo", method=RequestMethod.POST)
	public ModelAndView updateMoviePro(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		MultipartRequest mr = null;
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		int len = 50*1024*1024;
		mr = new MultipartRequest(req, upPath, len, "UTF-8");
		int res = movieDAO.updateMovie(mr);
		ModelAndView mav = new ModelAndView("redirect:movie_list.mo");
		return mav;
	}
		
	@RequestMapping(value="/movie_find.mo", method=RequestMethod.POST)
	public ModelAndView findMovie(@RequestParam String search, @RequestParam String searchString, HttpServletRequest req){
		String mode = req.getParameter("mode");
		ModelAndView mav= new ModelAndView();
		int pageSize=10;
		String pageNum = req.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = currentPage * pageSize -(pageSize-1);
		int endRow = startRow + pageSize -1;
		int count = (int)movieDAO.movieNavi();
		if(endRow>count) endRow = count;
		int pnum = count - (currentPage-1)*pageSize;
		
		List<MovieDTO> movieList = null;
		if(mode==null){
			if(count>0){
				int pageCount=count/pageSize + (count%pageSize==0? 0:1);
				int pageBlock =3;
				int startPage=(currentPage-1)/pageBlock*pageBlock +1;
				int endPage = startPage + pageBlock -1;
				if(endPage>pageCount) endPage = pageCount;
				mav.addObject("startPage", startPage);
				mav.addObject("endPage", endPage);
				mav.addObject("pageCount", pageCount);
				mav.addObject("pageBlock", pageBlock);
			}
			movieDAO.movieList(startRow, endRow);
		}else{
			search = req.getParameter("search");
			searchString = req.getParameter("searchString");
			movieList = movieDAO.findMovie(search, searchString);
		}
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		
		mav.setViewName("admin/movie/movieList");
		mav.addObject("movieList", movieList);
		mav.addObject("image",upPath);
		return mav;
		
	}
	
	@RequestMapping(value="/cmovie_list.mo")
	public ModelAndView cmovieList(HttpServletRequest req, HttpServletResponse resp){
		ModelAndView mav = new ModelAndView("client/movie/cmovie");
		List<MovieDTO> clist = movieDAO.cmovieList();
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		mav.addObject("cmovieList", clist);
		mav.addObject("image", upPath);
		return mav;
	}
	@RequestMapping(value="/cmovie_view.mo")
	public ModelAndView cmovieView(HttpServletRequest req, HttpServletResponse resp){
		int movie_num = Integer.parseInt(req.getParameter("movie_num"));
		List<ReviewDTO> list = movieDAO.listreview(movie_num);
		List<ReviewDTO> list2 = new ArrayList<ReviewDTO>();
		for(ReviewDTO dto : list){
			String image = movieDAO.memberimage(dto.getReview_writer());
			dto.setWriter_image(image);
			list2.add(dto);
		}
		String num = (String) req.getParameter("review_num");
		if(num==null) {
			num = "0";
		}
		int review_num = Integer.parseInt(num); 
		MovieDTO mvdto = movieDAO.cmovieView(movie_num);
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav = new ModelAndView("client/movie/cmovie_view");
		mav.addObject("mvdto", mvdto);
		mav.addObject("image", upPath);
		mav.addObject("movie_num", movie_num);
		mav.addObject("reviewList",list2);
		mav.addObject("redto", list);
		mav.addObject("update_num",review_num);
		return mav;
		
	}
	@RequestMapping(value="/actor_view.mo")
	public ModelAndView actorView(HttpServletRequest req, HttpServletResponse resp){
		int movie_num = Integer.parseInt(req.getParameter("movie_num"));
		MovieDTO mvdto = movieDAO.actorView(movie_num);
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav = new ModelAndView("client/movie/actor_view");
		mav.addObject("mvdto", mvdto);
		mav.addObject("image", upPath);
		mav.addObject("movie_num", movie_num);
		return mav;
	}
	
}
