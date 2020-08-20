package movie.admin.cinema.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.admin.cinema.dao.CinemaDAO;
import movie.admin.cinema.dto.CinemaDTO;
import movie.admin.member.dao.MemberDAO;
import movie.admin.member.dto.AdminDTO;
import movie.admin.movie.dao.MovieDAO;
import movie.admin.movie.dto.MovieDTO;
import movie.admin.theater.dao.TheaterDAO;
import movie.admin.theater.dto.TheaterDTO;
import movie.admin.theater.dto.Theater_playDTO;

@Controller
public class CinemaController {
	
	@Autowired
	private CinemaDAO cinemaDAO;
	@Autowired
	private TheaterDAO theaterDAO;
	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private MemberDAO memberDAO;
	
	@RequestMapping(value="/insertCinema.mo")
	public ModelAndView insertCinemaP() throws Exception{
		ModelAndView mav = new ModelAndView("admin/cinema/insertCinema");
		return mav;
	}
	
	@RequestMapping(value="/insertCinemaP.mo",method=RequestMethod.POST)
	public ModelAndView insertCinema(HttpServletRequest req) throws Exception{
		CinemaDTO dto = new CinemaDTO();
		dto.setCinema_addr(req.getParameter("cinema_addr"));
		dto.setCinema_size(Integer.parseInt(req.getParameter("cinema_size")));
		dto.setCinema_admin(req.getParameter("cinema_admin"));
		//��ȭ��
		ModelAndView mav = new ModelAndView("admin/message");
		if(cinemaDAO.checkCinema(dto.getCinema_addr()).size()>0){
			mav.addObject("msg","이미 등록된 영화관지점이름 입니다.");
			mav.addObject("url","insertCinema.mo");
			return mav;
		}
		HttpSession session = req.getSession();
		String cinema_addr = dto.getCinema_addr();
		session.setAttribute("cinema_addr", cinema_addr);
		int res = cinemaDAO.insertCinema(dto);
		CinemaDTO imsidto = cinemaDAO.getCinema(cinema_addr);
		AdminDTO adto = new AdminDTO();
		adto.setAdmin_name(req.getParameter("cinema_admin"));
		adto.setCinema_num(imsidto.getCinema_num());
		adto.setAdmin_passwd(req.getParameter("admin_passwd"));
		String tel = req.getParameter("tel1")+"-"+req.getParameter("tel2")+"-"+req.getParameter("tel3");
		adto.setAdmin_tel(tel);
		adto.setAdmin_id(req.getParameter("admin_id"));
		res = memberDAO.insertAdmin(adto);
		//
		if(res>0) {
			mav.addObject("msg","영화관 등록 성공! 상영관 추가 페이지로 이동합니다.");
			mav.addObject("url","insertTheater.mo");
		}else {
			mav.addObject("msg","영화관 등록 실패!");
			mav.addObject("url","insertCinema.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/insertTheater.mo")
	public ModelAndView insertTheaterP(HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String cinema_addr = (String) session.getAttribute("cinema_addr");
		CinemaDTO dto = cinemaDAO.getCinema(cinema_addr);
		List<TheaterDTO> list = theaterDAO.listTheater(dto.getCinema_num());
		ModelAndView mav = new ModelAndView("admin/cinema/insertTheater");
		mav.addObject("getCinema",dto);
		mav.addObject("listTheater",list);
		return mav;
	}
	
	@RequestMapping(value="/insertTheaterP.mo",method=RequestMethod.POST)
	public ModelAndView insertTheater(TheaterDTO tdto,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String cinema_addr = (String) session.getAttribute("cinema_addr");
		CinemaDTO dto = cinemaDAO.getCinema(cinema_addr);
		List<TheaterDTO> list = theaterDAO.listTheater(dto.getCinema_num());
		if(theaterDAO.checkTheater(tdto.getCinema_num(), tdto.getTheater_stage()).size()>0){		
			ModelAndView mav = new ModelAndView("admin/message");
			mav.addObject("msg","이미 등록된 상영관 이름입니다.");
			mav.addObject("url","insertTheater.mo");
			mav.addObject("getCinema",dto);
			mav.addObject("listTheater",list);
			return mav;
		}
		int res = theaterDAO.insertTheater(tdto);
		list = theaterDAO.listTheater(dto.getCinema_num());
		ModelAndView mav = new ModelAndView("admin/cinema/insertTheater");
		mav.addObject("getCinema",dto);
		mav.addObject("listTheater",list);
		return mav;
		
	}
	
	@RequestMapping(value="/listCinema.mo")
	public ModelAndView listCinema() throws Exception{
		List<CinemaDTO> list = cinemaDAO.listCinema();
		List<CinemaDTO> listCinema = new ArrayList<CinemaDTO>();
		for(CinemaDTO dto : list){
			dto.setCinema_size(theaterDAO.getSize(dto.getCinema_num()));
			listCinema.add(dto);
		}
		ModelAndView mav = new ModelAndView("admin/cinema/listCinema");
		mav.addObject("listCinema",listCinema);
		return mav;
	}
	
	@RequestMapping(value="/viewCinema.mo")
	public ModelAndView viewCinema(@RequestParam int cinema_num) throws Exception{
		CinemaDTO dto = cinemaDAO.viewCinema(cinema_num);
		List<TheaterDTO> list = theaterDAO.listTheater(cinema_num);
		ModelAndView mav = new ModelAndView("admin/cinema/viewCinema");
		mav.addObject("viewCinema",dto);
		mav.addObject("viewTheater",list);
		return mav;
	}
	
	@RequestMapping(value="/deleteCinema.mo")
	public ModelAndView deleteCinema(@RequestParam int cinema_num) throws Exception{
		ModelAndView mav = new ModelAndView("admin/message");
		int res = memberDAO.deleteAdmin(cinema_num);
		res = theaterDAO.deleteTheater(cinema_num);
		res = cinemaDAO.deleteCinema(cinema_num);
		if(res>0) {
			mav.addObject("msg","영화관 삭제 성공!!");
			mav.addObject("url","listCinema.mo");
		}else {
			mav.addObject("msg","영화관 삭제 실패!!");
			mav.addObject("url","listCinema.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/insertmovie.mo")
	public ModelAndView insertplaymovieP() throws Exception{
		List<CinemaDTO> cinemaList = cinemaDAO.listCinema();
		ModelAndView mav = new ModelAndView("admin/cinema/playmovieinsert");
		mav.addObject("cinemaList",cinemaList);
		return mav;
	}
	
	@RequestMapping(value="/selectTheater.mo")
	public ModelAndView selectTheater(@RequestParam int cinema_num,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String admin_id = (String) session.getAttribute("a_id");
		AdminDTO dto = memberDAO.idgetAdmin(admin_id);
		if(dto.getCinema_num()!=cinema_num){
			ModelAndView mav = new ModelAndView("admin/message");
			mav.addObject("msg","이 지점의 관리자가 아닙니다.");
			mav.addObject("url","insertmovie.mo");
			return mav;
		}
		List<CinemaDTO> cinemaList = cinemaDAO.listCinema();
		List<TheaterDTO> theaterList = theaterDAO.listTheater(cinema_num);
		ModelAndView mav = new ModelAndView("admin/cinema/playmovieinsert");
		mav.addObject("cinemaList",cinemaList);
		mav.addObject("theaterList",theaterList);
		return mav;
	}
	
	@RequestMapping(value="/selectMovie.mo")
	public ModelAndView selectMovieP(@RequestParam int theater_stage,@RequestParam int cinema_num) throws Exception{
		List<MovieDTO> movieList = movieDAO.alllistMovie();
		ModelAndView mav = new ModelAndView("admin/cinema/theatermovieinsert");
		mav.addObject("movieList",movieList);
		mav.addObject("theater_stage",theater_stage);
		mav.addObject("cinema_num",cinema_num);
		return mav;
	}
	
	@RequestMapping(value="/selectMovieP.mo",method=RequestMethod.POST)
	public ModelAndView selectMovie(Theater_playDTO dto,@RequestParam int start_time_hour,@RequestParam int start_time_min,@RequestParam int end_time_hour,@RequestParam int end_time_min) throws Exception{
		int startTime = (start_time_hour*60)+start_time_min;
		int endTime = (end_time_hour*60)+end_time_min;
		ModelAndView mav = new ModelAndView("admin/message");
		 if(startTime>=endTime){
				mav.addObject("msg","시작시간이 끝나는시간보다 작을수 없습니다.");
				mav.addObject("url","selectMovie.mo?cinema_num="+dto.getCinema_num()+"&theater_stage="+dto.getTheater_stage());
				return mav;
		}
		List<Theater_playDTO> calcList = theaterDAO.timecalc(dto.getTheater_stage(), dto.getPlay_year(), dto.getPlay_month(), dto.getPlay_day(),dto.getCinema_num());
		for(Theater_playDTO check : calcList){
			if(startTime>=check.getStart_time()&&startTime<=check.getEnd_time()+30){
				mav.addObject("msg","설정하신 시간은 사용할수 없습니다.시간표를 확인해주세요.");
				mav.addObject("url","selectMovie.mo?cinema_num="+dto.getCinema_num()+"&theater_stage="+dto.getTheater_stage());
				return mav;
			}else if(endTime>=check.getStart_time()&&endTime<=check.getEnd_time()){
				mav.addObject("msg","설정하신 시간은 사용할수 없습니다.시간표를 확인해주세요.");
				mav.addObject("url","selectMovie.mo?cinema_num="+dto.getCinema_num()+"&theater_stage="+dto.getTheater_stage());
				return mav;
			}else if(startTime<=check.getStart_time()){
				if(endTime>=check.getStart_time()&&endTime<=check.getEnd_time()){
					mav.addObject("msg","설정하신 시간은 사용할수 없습니다.시간표를 확인해주세요.");
					mav.addObject("url","selectMovie.mo?cinema_num="+dto.getCinema_num()+"&theater_stage="+dto.getTheater_stage());
					return mav;
				}else if(endTime>=check.getEnd_time()+30){
					mav.addObject("msg","설정하신 시간은 사용할수 없습니다.시간표를 확인해주세요.");
					mav.addObject("url","selectMovie.mo?cinema_num="+dto.getCinema_num()+"&theater_stage="+dto.getTheater_stage());
					return mav;
				}
			}
		}
		dto.setStart_time(startTime);
		dto.setEnd_time(endTime);
		int res = theaterDAO.insertTheaterplay(dto);
		if(res>0){
			mav.addObject("msg","스케쥴 등록 성공!!");
			mav.addObject("url","insertmovie.mo");
		}else{
			mav.addObject("msg","스케쥴 등록 실패!!");
			mav.addObject("url","insertmovie.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/listMovie.mo")
	public ModelAndView listMovie(@RequestParam int play_year,@RequestParam int play_month,@RequestParam int play_day,@RequestParam int cinema_num,@RequestParam int theater_stage) throws Exception{
		List<Theater_playDTO> list = theaterDAO.timecalc(theater_stage, play_year, play_month, play_day, cinema_num);
		List<Theater_playDTO> playlist = new ArrayList<Theater_playDTO>();
		for( Theater_playDTO dto : list) {
			String m_name = movieDAO.nameMovie(dto.getMovie_num());
			dto.setMovie_name(m_name);
			dto.setStart_time_hour(dto.getStart_time()/60);
			dto.setStart_time_min(dto.getStart_time()%60);
			dto.setEnd_time_hour(dto.getEnd_time()/60);
			dto.setEnd_time_min(dto.getEnd_time()%60);
			playlist.add(dto);
		}
		ModelAndView mav = new ModelAndView("admin/cinema/schedule");
		mav.addObject("play_year",play_year);
		mav.addObject("play_month",play_month);
		mav.addObject("play_day",play_day);
		mav.addObject("playlist",playlist);
		return mav;
		
	}
	
	@RequestMapping(value="/updateCinema.mo")
	public ModelAndView updateCinema(HttpServletRequest req) throws Exception{
		int cinema_num = Integer.parseInt(req.getParameter("cinema_num"));
		CinemaDTO getCinema = cinemaDAO.getCinema(cinemaDAO.getAddr(cinema_num));
		List<TheaterDTO> listTheater = theaterDAO.listTheater(cinema_num);
		ModelAndView mav = new ModelAndView("admin/cinema/updateCinema");
		mav.addObject("getCinema", getCinema);
		mav.addObject("listTheater", listTheater);
		return mav;
		}
	
	@RequestMapping(value="/updateTheater.mo")
	public ModelAndView updateTheater(@RequestParam int cinema_num, 
			@RequestParam int theater_stage) throws Exception{
		CinemaDTO getCinema = cinemaDAO.getCinema(cinemaDAO.getAddr(cinema_num));
		TheaterDTO getTheater = theaterDAO.getTheater(cinema_num, theater_stage);
		ModelAndView mav = new ModelAndView("admin/cinema/updatetheater");
		mav.addObject("getTheater", getTheater);
		mav.addObject("getCinema", getCinema);
		return mav;
	}
	
	@RequestMapping(value="/updateTheaterP.mo", method=RequestMethod.POST)
	public ModelAndView updateTheaterPro(TheaterDTO dto) throws Exception{
		ModelAndView mav = new ModelAndView("admin/message");
		int res = theaterDAO.updateTheater(dto);
		if(res>0) {
			mav.addObject("msg","상영관 수정 성공!!");
		}else {
			mav.addObject("msg","상영관 수정 실패!!");
		}
		mav.addObject("url","updateCinema.mo?cinema_num="+dto.getCinema_num());
		return mav;
	}
		
	@RequestMapping(value="/addTheater.mo")
	public ModelAndView addTheaterP(@RequestParam int cinema_num) throws Exception{
		CinemaDTO getCinema = cinemaDAO.viewCinema(cinema_num);
		ModelAndView mav = new ModelAndView("admin/cinema/addTheater");
		mav.addObject("getCinema", getCinema);
		return mav;
	}
	
	@RequestMapping(value="/addTheaterP.mo",method=RequestMethod.POST)
	public ModelAndView addTheater(TheaterDTO dto) throws Exception{
		if(theaterDAO.checkTheater(dto.getCinema_num(), dto.getTheater_stage()).size()>0){		
			ModelAndView mav = new ModelAndView("admin/message");
			mav.addObject("msg","이미 등록된 상영관 이름입니다.");
			mav.addObject("url","addTheater.mo?cinema_num="+dto.getCinema_num());
			return mav;
		}
		ModelAndView mav = new ModelAndView("admin/message");
		int res = theaterDAO.insertTheater(dto);
		if(res>0) {
			cinemaDAO.sizeupCinema(dto.getCinema_num());
			mav.addObject("msg","상영관 추가 성공!!");
			mav.addObject("url","updateCinema.mo?cinema_num="+dto.getCinema_num());
		}else {
			mav.addObject("msg","상영관 추가 실패!!");
			mav.addObject("url","addTheater.mo?cinema_num="+dto.getCinema_num());
		}
		return mav;
	}
	
	@RequestMapping(value="deleteTheater.mo")
	public ModelAndView deleteTheater(@RequestParam int cinema_num,@RequestParam int theater_stage) throws Exception{
		ModelAndView mav = new ModelAndView("admin/message");
		int res = theaterDAO.deletePlay(cinema_num);
		res = theaterDAO.deleteTheater1(cinema_num, theater_stage);
		if(res>0) {
			cinemaDAO.sizedownCinema(cinema_num);
			mav.addObject("msg","상영관 삭제 성공!!");
		}else{
			mav.addObject("msg","상영관 삭제 실패!!");
		}
		mav.addObject("url","updateCinema.mo?cinema_num="+cinema_num);
		return mav;
	}
	
	@RequestMapping(value="index.mo")
	public ModelAndView index() throws Exception{
		return new ModelAndView("redirect:index.jsp");
	}
	
	@RequestMapping(value="checkId.mo")
	public ModelAndView checkIdP() throws Exception{
		return new ModelAndView("admin/cinema/checkId");
	}
	
	@RequestMapping(value="checkIdP.mo",method=RequestMethod.POST)
	public ModelAndView checkId(@RequestParam String admin_id) throws Exception{
		List<AdminDTO> checklist = memberDAO.checkadminId(admin_id);
		ModelAndView mav = new ModelAndView("admin/message");
		if(checklist.size()>0){
			mav.addObject("msg","이미 등록된 아이디입니다.");
			mav.addObject("url","checkId.mo");
			return mav;
		}
		mav.addObject("msg","사용가능한 아이디입니다. 확인버튼을 눌러주세요.");
		mav.addObject("url","checkId.mo");
		mav.addObject("admin_id",admin_id);
		mav.addObject("check","check");
		return mav;
	}
	
	@RequestMapping(value="adminMain.mo")
	public ModelAndView adminMain() throws Exception{
		return new ModelAndView("admin/adminLogin");
	}
	
	@RequestMapping(value="adminLogin.mo")
	public ModelAndView adminLogin(@RequestParam String admin_id,@RequestParam String admin_passwd,HttpServletRequest req) throws Exception{
		List<AdminDTO> list = memberDAO.adminLogin(admin_id, admin_passwd);
		ModelAndView mav = new ModelAndView("admin/message");
		if(list.size()!=1){
			mav.addObject("msg","아이디/비밀번호가 정확하지 않습니다.");
			mav.addObject("url","adminMain.mo");
			return mav;
		}
		HttpSession session = req.getSession();
		session.setAttribute("a_id",admin_id);
		String user_id = (String) session.getAttribute("a_id");
		for(AdminDTO dto : list){
			session.setAttribute("a_name", dto.getAdmin_name());
		}
		mav.addObject("msg","환영합니다!");
		mav.addObject("url","adminIndex.mo");
		return mav;
	}
	
	@RequestMapping(value="adminIndex.mo")
	public ModelAndView adminIndex() throws Exception{
		return new ModelAndView("admin/index");
	}
	
	@RequestMapping(value="updateAdmin.mo")
	public ModelAndView adminUpdate(@RequestParam int cinema_num) throws Exception{
		AdminDTO dto = memberDAO.getAdmin(cinema_num);
		CinemaDTO cdto = cinemaDAO.viewCinema(cinema_num);
		ModelAndView mav = new ModelAndView("admin/cinema/updateAdmin");
		mav.addObject("cinema_addr",cdto.getCinema_addr());
		mav.addObject("getAdmin",dto);
		return mav;
	}
	
	
}
