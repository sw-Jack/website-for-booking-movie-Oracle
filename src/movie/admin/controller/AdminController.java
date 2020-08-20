package movie.admin.controller;

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

import movie.admin.board.dao.BoardDAO;
import movie.admin.board.dto.NoticeDTO;
import movie.admin.category.dao.CategoryDAO;
import movie.admin.category.dto.CategoryDTO;
import movie.admin.cinema.dao.CinemaDAO;
import movie.admin.cinema.dto.CinemaDTO;
import movie.admin.member.dao.MemberDAO;
import movie.admin.member.dto.AdminDTO;
import movie.admin.member.dto.MemberDTO;
import movie.admin.movie.dao.MovieDAO;
import movie.admin.movie.dto.MovieDTO;
import movie.admin.movie.dto.ReviewDTO;
import movie.admin.product.dao.ProductDAO;
import movie.admin.product.dto.ProductDTO;
import movie.admin.question.dao.QuestionDAO;
import movie.admin.question.dto.QuestionDTO;
import movie.admin.theater.dao.TheaterDAO;
import movie.admin.theater.dto.TheaterDTO;
import movie.admin.theater.dto.Theater_playDTO;

@Controller	
public class AdminController {
	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private CinemaDAO cinemaDAO;
	@Autowired
	private TheaterDAO theaterDAO;
	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	public QuestionDAO questionDAO;
	
	@RequestMapping(value="/noticeinsert.mo")
	public ModelAndView noticeinsertP() throws Exception{
		return new ModelAndView("admin/board/noticeInsert");
	}
	
	@RequestMapping(value="/noticeinsertP.mo",method=RequestMethod.POST)
	public ModelAndView noticeinsert(NoticeDTO dto) throws Exception{
		//삭제예정
		dto.setName("관리자");
		//
		int res = boardDAO.insertNotice(dto);
		ModelAndView mav = new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg","공지사항 등록 성공!!");
			mav.addObject("url","noticelist.mo");			
		}else{
			mav.addObject("msg","공지사항 등록 실패!!");
			mav.addObject("url","noticeinsert.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/noticelist.mo")
	public ModelAndView noticelist() throws Exception{
		List<NoticeDTO> noticeList = boardDAO.listNotice();
		ModelAndView mav = new ModelAndView("admin/board/noticeList");
		mav.addObject("noticeList",noticeList);
		return mav;
	}
	
	@RequestMapping(value="/noticecontent.mo")
	public ModelAndView noticecontent(@RequestParam int notice_num) throws Exception{
		NoticeDTO dto = boardDAO.getNotice(notice_num);
		ModelAndView mav = new ModelAndView("admin/board/noticeContent");
		mav.addObject("getnotice",dto);
		return mav;
	}
	
	@RequestMapping(value="/noticeupdate.mo")
	public ModelAndView noticeupdateP(@RequestParam int notice_num) throws Exception{
		NoticeDTO dto = boardDAO.getNotice(notice_num);
		ModelAndView mav = new ModelAndView("admin/board/noticeUpdate");
		mav.addObject("getnotice",dto);
		return mav;
	}
	
	@RequestMapping(value="/noticeupdateP.mo",method=RequestMethod.POST)
	public ModelAndView noticeupdate(NoticeDTO dto) throws Exception{
		int res = boardDAO.updateNotice(dto);
		ModelAndView mav = new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg","공지사항 수정 성공!!");
		}else{
			mav.addObject("msg","공지사항 수정 실패!!");
		}
		mav.addObject("url","noticelist.mo");
		return mav;
	}
	
	@RequestMapping(value="/noticedelete.mo")
	public ModelAndView noticedelete(@RequestParam int notice_num) throws Exception{
		int res = boardDAO.deleteNotice(notice_num);
		ModelAndView mav = new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg","공지사항 삭제 성공!!");
		}else{
			mav.addObject("msg","공지사항 삭제 실패!!");
		}
		mav.addObject("url","noticelist.mo");
		return mav;
	}
	//공지사항
	
	@RequestMapping(value="/cate_input.mo")
	public ModelAndView inputCategoryF() throws Exception{
		return new ModelAndView("admin/product/admin/cate_input");
		
	}
	
	@RequestMapping(value="/cate_inputP.mo" , method=RequestMethod.POST)
	public ModelAndView inputCategoryP(@ModelAttribute CategoryDTO dto) throws Exception{
		int res=categoryDAO.insertCategory(dto);
		ModelAndView mav = new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg","카테고리 등록 성공!!");
			mav.addObject("url", "cate_list.mo");
		}else{
			mav.addObject("msg", "카테고리 등록 실패!!");
			mav.addObject("url","cate_input.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/cate_list.mo")
	public ModelAndView listCategory() throws Exception{
		List <CategoryDTO> categoryList=categoryDAO.listCategory();
		ModelAndView mav= new ModelAndView();
		mav.setViewName("admin/product/admin/cate_list");
		mav.addObject("cateList",categoryList);
		return mav;
	}
	
	@RequestMapping(value="/cate_delete.mo")
	public ModelAndView deleteCategory(@RequestParam int cnum) throws Exception{
		int res=categoryDAO.deleteCategory(cnum);
		ModelAndView mav=new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg","카테고리 삭제 성공!!");
			mav.addObject("url","cate_list.mo");
		}else{
			mav.addObject("msg","카테고리 삭제 실패!!");
			mav.addObject("url","cate_list.mo");
		}
		return mav;
	}
	
	//카테고리
	
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
	public ModelAndView selectMovie(Theater_playDTO dto,@RequestParam int start_time_hour,@RequestParam int start_time_min) throws Exception{
		int startTime = (start_time_hour*60)+start_time_min;
		MovieDTO mdto = movieDAO.movieView(dto.getMovie_num());
		int endTime = startTime+Integer.parseInt(mdto.getTime());
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
	
	//영화관 관리
	
	@RequestMapping(value="/memberList.mo")
	public ModelAndView memberList() throws Exception{
		List<MemberDTO> memberList=memberDAO.listMember();
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/member/memberList");
		mav.addObject("memberList",memberList);
		return mav;
	}
	
	@RequestMapping(value="/memberContent.mo")
	public ModelAndView memberContent(@RequestParam int user_num) {
		MemberDTO dto=memberDAO.getMember(user_num);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/member/memberContent");
		mav.addObject("memberContent",dto);
		return mav;
		
	}
	
	@RequestMapping(value="/memberFind.mo")
	public ModelAndView memberFindF() {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/member/memberFind");
		mav.addObject("find","findP");
		return mav;
	}
	
	@RequestMapping(value="/memberFindP.mo",method=RequestMethod.POST)
	public ModelAndView memberFind(@RequestParam String search, @RequestParam String searchString) {
		List<MemberDTO> memberList= memberDAO.findMember(search, searchString);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("admin/member/memberFind");
		mav.addObject("memberFind",memberList);
		return mav;
	}
	
	@RequestMapping(value="/memberUpdate.mo")
	public ModelAndView memberUpadateF(@RequestParam int user_num) {
		MemberDTO dto=memberDAO.getMember(user_num);
		ModelAndView mav= new ModelAndView();
		mav.setViewName("admin/member/memberUpdate");
		mav.addObject("getMember",dto);
		return mav;
	}
	
	@RequestMapping(value="memberUpdateP.mo", method=RequestMethod.POST)
	public ModelAndView memberUpdate(@ModelAttribute MemberDTO dto) {
		int res= memberDAO.updateMember(dto);
		return new ModelAndView("redirect:memberList.mo");
		
	}
	
	@RequestMapping(value="memberDelete.mo")
	public ModelAndView memberDelete(@RequestParam int user_num) {
		int res= memberDAO.deleteMember(user_num);
		return new ModelAndView("redirect:memberList.mo");
	}
	
	//멤버 관리
	
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
		int res = theaterDAO.deleteplay(Integer.parseInt(movie_num));
		res = movieDAO.deleteMovie(Integer.parseInt(movie_num));
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
	
	//영화 관리
	
	@RequestMapping(value="/prod_input.mo")
	public ModelAndView inputProductF() throws Exception{
		List<CategoryDTO> categoryList=categoryDAO.listCategory();
		ModelAndView mav= new ModelAndView("admin/product/admin/prod_input");
		mav.addObject("cateList",categoryList);
		return mav;
	}
	
	@RequestMapping(value="/prod_inputP.mo", method=RequestMethod.POST)
	public ModelAndView inputProductP(HttpServletRequest req) throws Exception{
		MultipartRequest mr= null;
		HttpSession session =req.getSession();
		String upPath=session.getServletContext().getRealPath("/images");
		int len=10*1024*1024;
		try {
			mr= new MultipartRequest(req,upPath,len,"UTF-8");
		}catch(IOException e) {
			System.err.println("err");
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("admin/message");
		int res = productDAO.insertProduct(mr);
		if(res>0){
			mav.addObject("msg", "상품 추가 성공!!");
			mav.addObject("url","prod_list.mo");
		}else{
			mav.addObject("msg","상품 추가 실패!!");
			mav.addObject("url","prod_input.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/prod_list.mo")
	public ModelAndView listProduct(HttpServletRequest req) throws Exception{
		List<ProductDTO> list=productDAO.listProduct();
		HttpSession session=req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav=new ModelAndView("admin/product/admin/prod_list");
		mav.addObject("upPath",upPath);
		mav.addObject("prodList",list);
		return mav;
	}
	
	@RequestMapping(value="/prod_content.mo")
	public ModelAndView contentProduct(@RequestParam int pnum, HttpServletRequest req) throws Exception{
		ProductDTO dto=productDAO.getProduct(pnum);
		HttpSession session=req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav= new ModelAndView("admin/product/admin/prod_content");
		mav.addObject("upPath",upPath);
		mav.addObject("getProd",dto);
		return mav;
	}
	
	@RequestMapping(value="/prod_update.mo")
	public ModelAndView updateProductF(@RequestParam int pnum, HttpServletRequest req) throws Exception{
		ProductDTO dto = productDAO.getProduct(pnum);
		HttpSession session=req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav= new ModelAndView("admin/product/admin/prod_update");
		mav.addObject("upPath",upPath);
		mav.addObject("getProd",dto);
		return mav;
	}
	
	@RequestMapping(value="/prod_updateP.mo" , method=RequestMethod.POST)
	public ModelAndView updateProductP(HttpServletRequest req) throws Exception{
		MultipartRequest mr = null;
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		int len = 10*1024*1024;
		try{
			mr = new MultipartRequest(req, upPath, len, "EUC-KR");
		}catch(IOException e){
			System.err.println("err");
			e.printStackTrace();
		}
		int res = 0;
		File file = new File(upPath,mr.getParameter("pimage2"));
		ModelAndView mav = new ModelAndView("admin/message");
		if(mr.getFilesystemName("pimage")==null){
			res = productDAO.updateProduct(mr);
		}else{
			res = productDAO.updateProduct(mr);
			file.delete();
		}
		if(res>0){
			mav.addObject("msg","상품 수정 성공!!");
		}else{
			mav.addObject("msg","상품 수정 실패!!");
		}
		mav.addObject("url","prod_list.mo");
		return mav;
	
	}
	
	@RequestMapping(value="/prod_delete.mo")
	public ModelAndView deleteProduct(HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String path = session.getServletContext().getRealPath("/images");
		File file = new File(path,req.getParameter("pimage"));
		ModelAndView mav = new ModelAndView("admin/message");
		try{
			if(file.exists()){
				productDAO.deleteProduct(Integer.parseInt(req.getParameter("pnum")));
				file.delete();
				mav.addObject("msg","상품과 상품사진을 삭제하였습니다.");
				mav.addObject("url","prod_list.mo");
			}else{
				productDAO.deleteProduct(Integer.parseInt(req.getParameter("pnum")));
				mav.addObject("msg","상품은 삭제하였으나, 상품사진은 삭제하지 못 하였습니다.");
				mav.addObject("url","prod_list.mo");
			}
		}catch(Exception e){
			mav.addObject("msg","상품, 상품사진 삭제 실패");
			mav.addObject("url","prod_list.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/prod_find.mo")
	public ModelAndView fintProductF() throws Exception {
		List<CategoryDTO> categoryList=categoryDAO.listCategory();
		ModelAndView mav= new ModelAndView();
		mav.setViewName("admin/product/admin/prod_find");
		mav.addObject("cateList",categoryList);
		mav.addObject("find","findP");
		return mav;
	}
	
	@RequestMapping(value="/prod_findP.mo" , method=RequestMethod.POST)
	public ModelAndView findProductP(@RequestParam String search, @RequestParam String searchString, HttpServletRequest req) {
		List<CategoryDTO> categoryList=categoryDAO.listCategory();
		List<ProductDTO> productList=productDAO.findProduct(search, searchString);
		HttpSession session=req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		ModelAndView mav= new ModelAndView();
		mav.setViewName("admin/product/admin/prod_find");
		mav.addObject("cateList",categoryList);
		mav.addObject("findProd",productList);
		mav.addObject("upPath",upPath);
		return mav;
	}
	
	//상품 관리
	
	@RequestMapping(value="/question_list.mo")
	public ModelAndView questionList(HttpServletRequest req, HttpServletResponse resp){
		ModelAndView mav = new ModelAndView();
		int pageSize = 10;
		String pageNum = req.getParameter("pageNum");
		if(pageNum==null){
			pageNum="1";
		}
		int currentPage = Integer.parseInt(pageNum);
		int startRow = currentPage * pageSize -(pageSize-1);
		int endRow = startRow + pageSize -1;		
		int count = (int)questionDAO.pagecount();
		if(endRow>count) endRow = count;
		int pnum = count - (currentPage-1) * pageSize;
		List<QuestionDTO> questionList = questionDAO.questionList(startRow, endRow);
		if(count>0){
			int pageCount = count/pageSize + (count%pageSize==0? 0:1);
			int pageBlock = 3;
			int startPage=(currentPage-1)/pageBlock * pageBlock+1;
			int endPage = startPage + pageBlock -1;
			if(endPage>pageCount) endPage = pageCount;
			mav.addObject("startPage", startPage);
			mav.addObject("endPage", endPage);
			mav.addObject("pageCount", pageCount);
			mav.addObject("pageBlock", pageBlock);
		}
		mav.addObject("pnum", pnum);
		mav.setViewName("admin/board/questionList");
		mav.addObject("questionList", questionList);
		mav.addObject("count", count);
		mav.addObject("pageSize", pageSize);
		mav.addObject("currentPage", currentPage);
		return mav;
	}
	@RequestMapping(value="/question_content.mo")
	public ModelAndView questionContent(@RequestParam int question_num){
		QuestionDTO qdto = questionDAO.getQuestion(question_num);
		ModelAndView mav = new ModelAndView("admin/board/questionContent");
		mav.addObject("getQuestion", qdto);
		return mav;
	}
	@RequestMapping(value="/question_delete.mo")
	public ModelAndView deleteQuestion(HttpServletRequest req, HttpServletResponse resp){
		String question_num = req.getParameter("question_num");
		int res = questionDAO.deleteQuestion(Integer.parseInt(question_num));
		ModelAndView mav = new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg","삭제성공!");
		}else{
			mav.addObject("msg", "삭제실패!");
		}
		mav.addObject("url","question_list.mo");
		return mav;
	}
	@RequestMapping(value="/question_answer.mo")
	public ModelAndView answerQuestionForm(@RequestParam int question_num,@RequestParam int re_group,@RequestParam int re_step,@RequestParam int re_level,@RequestParam int user_num){
		ModelAndView mav = new ModelAndView("admin/board/answerForm");
		mav.addObject("question_num",question_num);
		mav.addObject("re_group",re_group);
		mav.addObject("re_step",re_step);
		mav.addObject("re_level",re_level);
		mav.addObject("user_num",user_num);
		return mav;
	}
	@RequestMapping(value="question_answerP.mo", method=RequestMethod.POST)
	public ModelAndView answerQuestionPro(@ModelAttribute QuestionDTO qdto, BindingResult result){
		if(result.hasErrors()){
			qdto.setQuestion_num(0);
			qdto.setRe_group(0);
			qdto.setRe_step(0);
			qdto.setRe_level(0);
		}
		int res = questionDAO.answer(qdto);
		ModelAndView mav = new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg", "답변작성을 성공했습니다!");
			mav.addObject("url", "question_list.mo");
		}else{
			mav.addObject("msg", "답변작성을 실패했습니다!");
			mav.addObject("url", "question_answer.mo");
		}
		return mav;
	}
	
	//관리자 1:1 문의
	
	
}
