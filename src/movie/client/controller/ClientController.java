package movie.client.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


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
import movie.admin.theater.dao.TheaterDAO;
import movie.admin.theater.dto.TheaterDTO;
import movie.admin.theater.dto.Theater_playDTO;
import movie.admin.theater.dto.Theater_seatDTO;
import movie.client.question.dao.QuestionDAO;
import movie.client.question.dto.QuestionDTO;
import movie.client.reservation.dao.CreditDAO;
import movie.client.reservation.dto.CreditDTO;

@Controller
public class ClientController {
	
	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private CinemaDAO cinemaDAO;
	@Autowired
	private TheaterDAO theaterDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	public QuestionDAO cquestionDAO;
	@Autowired
	public BoardDAO boardDAO;
	@Autowired
	public CreditDAO creditDAO;
	
	@RequestMapping(value="/kgvindex.mo")
	public ModelAndView index(HttpServletRequest req) throws Exception{
		ModelAndView mav = new ModelAndView("client/index");
		List<MovieDTO> clist = movieDAO.cmovieList();
		List<MovieDTO> list = new ArrayList<MovieDTO>();
		int imsi = 0;
		for(MovieDTO dto : clist){
			list.add(dto);
			imsi++;
			if(imsi==4)break;
		}
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		mav.addObject("cmovieList", list);
		mav.addObject("image", upPath);
		return mav;
	}
	
	@RequestMapping(value="/register.mo")
	public ModelAndView registerP(HttpServletRequest req) throws Exception{
		ModelAndView mav = new ModelAndView("client/member/register");
		mav.addObject("authNum",req.getParameter("authNum"));
		mav.addObject("email",req.getParameter("email"));
		return mav;
	}
	
	@RequestMapping(value="/checkCId.mo")
	public ModelAndView checkCidP() throws Exception{
		return new ModelAndView("client/member/checkCId");
	}
	
	@RequestMapping(value="/checkCIdP.mo")
	public ModelAndView checkCid(@RequestParam String id) throws Exception{
		List<MemberDTO> checkid = memberDAO.clientcheckId(id);
		List<AdminDTO> checkid2 = memberDAO.checkadminId(id);
		ModelAndView mav = new ModelAndView("client/message");
		if(checkid.size()>0 || checkid2.size()>0) {
			mav.addObject("msg","이미 가입된 아이디입니다.");
			mav.addObject("url","checkCId.mo");
			return mav;
		}
		mav.addObject("msg","사용가능한 아이디입니다 확인버튼을 눌러주세요.");
		mav.addObject("url","checkCId.mo");
		mav.addObject("id",id);
		mav.addObject("check","check");
		return mav;
	}
	
	@RequestMapping(value="/insertMember.mo")
	public ModelAndView insertMember(HttpServletRequest req) throws Exception{
		MultipartRequest mr = null;
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		int len = 50*1024*1024;
		mr = new MultipartRequest(req, upPath, len, "UTF-8");
		ModelAndView mav = new ModelAndView("client/message");
		List<MemberDTO> list = memberDAO.clientcheckId(mr.getParameter("id"));
		List<AdminDTO> list2 = memberDAO.checkadminId(mr.getParameter("id"));
		if(list.size()>0||list2.size()>0) {
			mav.addObject("msg","아이디확인을 다시해주세요.");
			mav.addObject("url","register.mo");
			return mav;
		}else {
			int res = memberDAO.insertmember(mr);
			if(res>0) {
				mav.addObject("msg","가입성공!");
				session.setAttribute("id", mr.getParameter("id"));
				mav.addObject("url","kgvindex.mo");
			}else {
				mav.addObject("msg","가입실패.");
				mav.addObject("url","register.mo");
			}
		}
		return mav;
	}
	
	@RequestMapping(value="/loginMemberP.mo")
	public ModelAndView loginMemberP() throws Exception{
		return new ModelAndView("client/member/login");
	}
	
	@RequestMapping(value="/memberlogin.mo")
	public ModelAndView loginMember(@RequestParam String id,@RequestParam String passwd,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		List<MemberDTO> list = memberDAO.loginmember(id, passwd);
		ModelAndView mav = new ModelAndView();
		if(list.size()==1){
			session.setAttribute("id", id);
			String id12 = (String)session.getAttribute("id");
			mav.setViewName("redirect:kgvindex.mo");
			return mav;
		}else{
			List<AdminDTO> adminlist = memberDAO.adminLogin(id,passwd);
			if(adminlist.size()>0){
				session.setAttribute("a_id", id);
				mav.setViewName("redirect:kgvindex.mo");
				return mav;
			}else{
				mav.setViewName("client/message");
				mav.addObject("msg","로그인 정보가 일치하지 않습니다!!");
				mav.addObject("url","loginMemberP.mo");
				return mav;
			}
		}
	}
	
	@RequestMapping(value="/logout.mo")
	public ModelAndView logoutMember(HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		session.removeAttribute("a_id");
		session.removeAttribute("id");
		return new ModelAndView("redirect:kgvindex.mo");
	}
	
	@RequestMapping(value="/reservation.mo")
	public ModelAndView reservationP() throws Exception{
		Calendar cal = Calendar.getInstance();
		List<CinemaDTO> clist = cinemaDAO.listCinema();
		List<Theater_playDTO> tlist = theaterDAO.listtheaterplay();
		List<MovieDTO> mlist = new ArrayList<MovieDTO>();
		for(Theater_playDTO dto : tlist){
			MovieDTO mdto = null;
			boolean check = true;
			if(dto.getPlay_year()>cal.get(Calendar.YEAR)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()>(cal.get(Calendar.MONTH)+1)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()==(cal.get(Calendar.MONTH)+1)
					&&dto.getPlay_day()>=cal.get(Calendar.DAY_OF_MONTH)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else{
				continue;
			}
			for(MovieDTO imsi : mlist){
				if(imsi.getMovie_num()==mdto.getMovie_num()){
					check=false;
				}
			}
			if(check){
				mlist.add(mdto);
			}
		}
		ModelAndView mav = new ModelAndView("client/reservation/reservationIndex");
		mav.addObject("clist",clist);
		mav.addObject("mlist",mlist);
		mav.addObject("play_year",cal.get(Calendar.YEAR));
		mav.addObject("play_month",cal.get(Calendar.MONTH)+1);
		mav.addObject("play_day",cal.get(Calendar.DAY_OF_MONTH));
		return mav;
	}
	
	@RequestMapping(value="/cselectMovie.mo")
	public ModelAndView selectMovieP(HttpServletRequest req) throws Exception{
		String movie_nums = req.getParameter("movie_num");
		String cinema_nums = req.getParameter("cinema_num");
		if(movie_nums == ""){
			movie_nums = "0";
		}
		if(cinema_nums == ""||cinema_nums==null){
			cinema_nums = "0";
		}
		int cinema_num = Integer.parseInt(cinema_nums);
		int movie_num = Integer.parseInt(movie_nums);
		Calendar cal = Calendar.getInstance();
		List<CinemaDTO> clist = cinemaDAO.listCinema();
		List<Theater_playDTO> tlist = theaterDAO.listtheaterplay();
		List<MovieDTO> mlist = new ArrayList<MovieDTO>();
		for(Theater_playDTO dto : tlist){
			MovieDTO mdto = null;
			boolean check = true;
			if(dto.getPlay_year()>cal.get(Calendar.YEAR)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()>(cal.get(Calendar.MONTH)+1)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()==(cal.get(Calendar.MONTH)+1)
					&&dto.getPlay_day()>=cal.get(Calendar.DAY_OF_MONTH)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else{
				continue;
			}
			for(MovieDTO imsi : mlist){
				if(imsi.getMovie_num()==mdto.getMovie_num()){
					check=false;
				}
			}
			if(check){
				mlist.add(mdto);
			}
		}
		ModelAndView mav = new ModelAndView("client/reservation/reservationIndex");
		mav.addObject("clist",clist);
		mav.addObject("mlist",mlist);
		mav.addObject("movie_num",movie_num);
		mav.addObject("cinema_num",cinema_num);
		mav.addObject("play_year",cal.get(Calendar.YEAR));
		mav.addObject("play_month",cal.get(Calendar.MONTH)+1);
		mav.addObject("play_day",cal.get(Calendar.DAY_OF_MONTH));
		return mav;
	}
	
	@RequestMapping(value="/reserselectc.mo")
	public ModelAndView reservationPs(HttpServletRequest req) {
		String cinema_nums = req.getParameter("cinema_num");
		String movie_nums = req.getParameter("movie_num");
		if(cinema_nums==null){
			cinema_nums = "0";
		}
		if(movie_nums==""){
			movie_nums = "0";
		}
		int cinema_num = Integer.parseInt(cinema_nums);
		int movie_num = Integer.parseInt(movie_nums);
		Calendar cal = Calendar.getInstance();
		List<CinemaDTO> clist = cinemaDAO.listCinema();
		List<Theater_playDTO> tlist = theaterDAO.listtheaterplay();
		List<MovieDTO> mlist = new ArrayList<MovieDTO>();
		for(Theater_playDTO dto : tlist){
			MovieDTO mdto = null;
			boolean check = true;
			if(dto.getPlay_year()>cal.get(Calendar.YEAR)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()>(cal.get(Calendar.MONTH)+1)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()==(cal.get(Calendar.MONTH)+1)
					&&dto.getPlay_day()>=cal.get(Calendar.DAY_OF_MONTH)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else{
				continue;
			}
			for(MovieDTO imsi : mlist){
				if(imsi.getMovie_num()==mdto.getMovie_num()){
					check=false;
				}
			}
			if(check){
				mlist.add(mdto);
			}
		}
		ModelAndView mav = new ModelAndView("client/reservation/reservationIndex");
		mav.addObject("cinema_num",cinema_num);
		mav.addObject("movie_num",movie_num);
		mav.addObject("clist",clist);
		mav.addObject("mlist",mlist);
		mav.addObject("play_year",cal.get(Calendar.YEAR));
		mav.addObject("play_month",cal.get(Calendar.MONTH)+1);
		mav.addObject("play_day",cal.get(Calendar.DAY_OF_MONTH));
		return mav;
	}
	
	@RequestMapping(value="/cinemaplaylist.mo")
	public ModelAndView reservationtimes(@RequestParam int cinema_num,@RequestParam int play_year,
			@RequestParam int play_month,@RequestParam int play_day, @RequestParam int movie_num) {
		List<Theater_playDTO> list = theaterDAO.getplay(play_year, play_month, play_day, cinema_num, movie_num);
		List<Theater_playDTO> playlist = new ArrayList<Theater_playDTO>();
		List<CinemaDTO> clist = cinemaDAO.listCinema();
		Calendar cal = Calendar.getInstance();
		List<Theater_playDTO> tlist = theaterDAO.listtheaterplay();
		List<MovieDTO> mlist = new ArrayList<MovieDTO>();
		for(Theater_playDTO dto : tlist){
			MovieDTO mdto = null;
			boolean check = true;
			if(dto.getPlay_year()>cal.get(Calendar.YEAR)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()>(cal.get(Calendar.MONTH)+1)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()==(cal.get(Calendar.MONTH)+1)
					&&dto.getPlay_day()>=cal.get(Calendar.DAY_OF_MONTH)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else{
				continue;
			}
			for(MovieDTO imsi : mlist){
				if(imsi.getMovie_num()==mdto.getMovie_num()){
					check=false;
				}
			}
			if(check){
				mlist.add(mdto);
			}
		}
		for( Theater_playDTO dto : list) {
			String m_name = movieDAO.nameMovie(dto.getMovie_num());
			CinemaDTO cdto = cinemaDAO.viewCinema(dto.getCinema_num());
			dto.setMovie_name(m_name);
			dto.setCinema_addr(cdto.getCinema_addr());
			int imsi_shour = dto.getStart_time()/60;
			int imsi_ehour = dto.getEnd_time()/60;
			if(imsi_shour>24) {
				imsi_shour -= 24;
			}
			if(imsi_ehour>24) {
				imsi_ehour -= 24;
			}
			String start_hour = String.valueOf(imsi_shour);
			String start_min = String.valueOf(dto.getStart_time()%60);
			String end_hour = String.valueOf(imsi_ehour);
			String end_min = String.valueOf(dto.getEnd_time()%60);
			
			if(start_hour.length()==1) {
				start_hour = "0"+start_hour;
			}
			if(start_min.length()==1) {
				start_min = "0"+start_min;
			}
			if(end_hour.length()==1) {
				end_hour = "0"+end_hour;
			}
			if(end_min.length()==1) {
				end_min = "0"+end_min;
			}
			dto.setStart(start_hour+":"+start_min);
			dto.setEnd(end_hour+":"+end_min);
			playlist.add(dto);
			
		}
		int size = theaterDAO.getSize(cinema_num);
		int now_hour = cal.get(Calendar.HOUR_OF_DAY);
		int now_min = cal.get(Calendar.MINUTE);
		int now_year = cal.get(Calendar.YEAR);
		int now_month = cal.get(Calendar.MONTH)+1;
		int now_day = cal.get(Calendar.DAY_OF_MONTH);
		int now_time = now_hour*60+now_min;
		ModelAndView mav = new ModelAndView("client/reservation/reservationIndex");
		mav.addObject("playlist",playlist);
		mav.addObject("cinema_num",cinema_num);
		mav.addObject("movie_num",movie_num);
		mav.addObject("size",size);
		mav.addObject("clist",clist);
		mav.addObject("mlist",mlist);
		mav.addObject("now_year",now_year);
		mav.addObject("now_month",now_month);
		mav.addObject("now_day",now_day);
		mav.addObject("nowtime",now_time);
		mav.addObject("play_year",play_year);
		mav.addObject("play_month",play_month);
		mav.addObject("play_day",play_day);
		return mav;
	}
	
	@RequestMapping(value="/store_main.mo")
	public ModelAndView storeMain(HttpServletRequest req){
		ModelAndView mav = new ModelAndView("client/store/storemain");
		return mav;
	}
	@RequestMapping(value="/store_list.mo")
	public ModelAndView liststore(HttpServletRequest req, @RequestParam String ccode){
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		List<ProductDTO> list = productDAO.storeList(ccode);
		ModelAndView mav = new ModelAndView("client/store/storeList");
		mav.addObject("prodList",list);
		mav.addObject("upPath", upPath);
		return mav;
	}
	@RequestMapping(value="/prod_view.mo")
	   public ModelAndView storeView(@RequestParam int pnum, HttpServletRequest req) throws Exception{
	      ProductDTO dto = productDAO.getProduct(pnum);
	      HttpSession session=req.getSession();
	      String upPath = session.getServletContext().getRealPath("/images");
	      ModelAndView mav= new ModelAndView("client/store/prod_view");
	      mav.addObject("upPath",upPath);
	      mav.addObject("getProd",dto);
	      return mav;
	}
	@RequestMapping(value="/inputreview.mo")
	public ModelAndView insertReview(ReviewDTO dto) throws Exception{
		List<ReviewDTO> list = movieDAO.listreview(dto.getMovie_num());
		for(ReviewDTO rdto : list){
			if(rdto.getReview_writer().equals(dto.getReview_writer())){
				ModelAndView mav = new ModelAndView("client/message");
				mav.addObject("msg", "리뷰 작성은 한번만 할 수 있습니다.");
				mav.addObject("url", "cmovie_view.mo?movie_num="+dto.getMovie_num());
				return mav;
			}
		}
		int res = movieDAO.insertreview(dto);
		List<ReviewDTO> avg = movieDAO.avgscore(dto.getMovie_num());
		double sum = 0.0;
		for(ReviewDTO rdto : avg) {
			sum += rdto.getReview_score();
		}
		double score_avg = sum/avg.size();
		res = movieDAO.scoreup(dto.getMovie_num(), score_avg);
		return new ModelAndView("redirect:cmovie_view.mo?movie_num="+dto.getMovie_num()); 
	}
	
	@RequestMapping(value="/review_update.mo")
	public ModelAndView reviewupdateP(@RequestParam int review_num,@RequestParam int movie_num) throws Exception{
		ModelAndView mav = new ModelAndView("redirect:cmovie_view.mo?movie_num="+movie_num+"&review_num="+review_num);
		return mav;
	}
	
	@RequestMapping(value="/review_updateP.mo")
	public ModelAndView reviewupdate(ReviewDTO dto) throws Exception{
		int res = movieDAO.reviewUpdate(dto);
		List<ReviewDTO> avg = movieDAO.avgscore(dto.getMovie_num());
		double sum = 0.0;
		for(ReviewDTO rdto : avg) {
			sum += rdto.getReview_score();
		}
		double score_avg = sum/avg.size();
		res = movieDAO.scoreup(dto.getMovie_num(), score_avg);
		return new ModelAndView("redirect:cmovie_view.mo?movie_num="+dto.getMovie_num()); 
	}
	
	@RequestMapping(value="/review_delete.mo")
	public ModelAndView reviewdelete(@RequestParam int review_num,@RequestParam int movie_num,@RequestParam String id,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String a_id = (String) session.getAttribute("a_id");
		ReviewDTO dto = movieDAO.getReview(review_num);
		ModelAndView mav = null;
		if(a_id==null) {
			if(!dto.getReview_writer().equals(id)) {
				mav = new ModelAndView("kgvindex.mo");
				return mav;
			}
		}
		int res = movieDAO.reviewDelete(review_num);
		List<ReviewDTO> avg = movieDAO.avgscore(dto.getMovie_num());
		double score_avg = 0.0;
		if(avg.size()==0) {
			score_avg = 0.0;
		}else {
			double sum = 0.0;
			for(ReviewDTO rdto : avg) {
				sum += rdto.getReview_score();
			}
			score_avg = sum/avg.size();
		}
		res = movieDAO.scoreup(dto.getMovie_num(), score_avg);
		mav = new ModelAndView("client/message");
		if(res>0) {
			mav.addObject("msg","리뷰삭제성공!");
		}else {
			mav.addObject("msg","리뷰삭제실패!");
		}
		mav.addObject("url","cmovie_view.mo?movie_num="+movie_num);
		return mav;
		
	}
	
	@RequestMapping(value="/cquestion_insert.mo")
	public ModelAndView insertQuestionF() throws Exception{
		ModelAndView mav= new ModelAndView();
		mav.setViewName("client/board/question_insert");
		return mav;
	}
	
	@RequestMapping(value="/cquestion_insertP.mo" , method=RequestMethod.POST)
	public ModelAndView insertQuestionP(@ModelAttribute QuestionDTO dto, BindingResult result, HttpServletRequest req,@RequestParam String id) {
		if(result.hasErrors()){
			dto.setRe_step(0);
			dto.setRe_level(0);
		}
		System.out.println(id);
		MemberDTO mdto = memberDAO.getMemberInfo(id);
		dto.setUser_num(mdto.getUser_num());
		dto.setName(id);
		int res =cquestionDAO.insertQuestion(dto);
		ModelAndView mav= new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg", "문의 작성 성공!");
			mav.addObject("url", "cquestion_list.mo?id="+id);
		}else{
			mav.addObject("msg", "문의 작성 실패!");
			mav.addObject("url", "cquestion_insert.mo");
		}
		return mav;
	
	}
	
	@RequestMapping(value="/cquestion_list.mo")
	public ModelAndView listQuestion(HttpServletRequest req, HttpServletResponse rep) {
		ModelAndView mav= new ModelAndView();
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		String a_id=(String)session.getAttribute("a_id");
		if(a_id!=null){
			return new ModelAndView("redirect:question_list.mo");
		}
		if(id==null){
			mav.setViewName("client/message");
			mav.addObject("msg","로그인하셔야 이용가능합니다.");
			mav.addObject("url","loginMemberP.mo");
			return mav;
		}
		MemberDTO dto = memberDAO.getMemberInfo(id);
		List<QuestionDTO> questionList= cquestionDAO.listQuestion(dto.getUser_num());
		mav.setViewName("client/board/question_list");
		mav.addObject("questionList",questionList);
		return mav;
		
	}
	
	@RequestMapping(value="/cquestion_content.mo")
	public ModelAndView contentQuestion(@RequestParam int question_num) throws Exception{
		QuestionDTO dto= cquestionDAO.getQuestion(question_num);
		ModelAndView mav= new ModelAndView();
		mav.setViewName("client/board/question_content");
		mav.addObject("getQuestion",dto);
		return mav;
	}
	
	@RequestMapping(value="/cquestion_update.mo")
	public ModelAndView updateQuestionF(HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String name = (String) session.getAttribute("id");
		QuestionDTO dto=cquestionDAO.getcquestion(name);
		ModelAndView mav= new ModelAndView();
		mav.setViewName("client/board/question_update");
		mav.addObject("getQuestion", dto);
		return mav;
	}
	
	@RequestMapping(value="/cquestion_updateP.mo", method=RequestMethod.POST)
	public ModelAndView updateQuestionP(QuestionDTO dto,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		int res=cquestionDAO.updateQuestion(dto);
		ModelAndView mav= new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg", "1:1문의 수정 성공!");
			mav.addObject("url", "cquestion_list.mo?id="+id);
		}else{
			mav.addObject("msg", "수정 실패!");
			mav.addObject("url", "cquestion_update.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/cquestion_delete.mo")
	public ModelAndView deleteQuestion(@RequestParam int question_num,@RequestParam String id) throws Exception{
		int res= cquestionDAO.deleteQuestion(question_num);
		ModelAndView mav = new ModelAndView("admin/message");
		if(res>0){
			mav.addObject("msg","문의 삭제 성공!!");
		}else{
			mav.addObject("msg","문의 삭제 실패!!");
		}
		mav.addObject("url","cquestion_list.mo?id="+id);
		return mav;
	}
	
	@RequestMapping(value="/selectseat.mo")
	public ModelAndView selectSeatP(@RequestParam int play_num,HttpServletRequest req) throws Exception{
		String seat = req.getParameter("selectseat");
		if(seat == null||seat==""){
			seat = "0";
		}
		
		HttpSession session = req.getSession();
		List<Theater_seatDTO> seatlist = null;
		if(req.getParameter("selectseat") == null || req.getParameter("stickseat") == null){
			seatlist = new ArrayList<Theater_seatDTO>();
			session.setAttribute("seatlist", seatlist);//선택한 좌석 저장하는 리스트 만들어주거나 초기화시켜줌
		}else{
			seatlist = (List<Theater_seatDTO>) session.getAttribute("seatlist"); //선택한 좌석 리스트 받아오기
		}
		
		int selectseat = Integer.parseInt(seat);//선택한좌석
		int leftseat = 0;
		int stickseat = 0;
		if(req.getParameter("stickseat") == null||req.getParameter("stickseat")==""){
			if(selectseat == 1){
				stickseat = 1;
			}else{
				stickseat = 2;
			}
			leftseat = selectseat;
		}else{
			if(seatlist.size()>0){
				if(selectseat == seatlist.size()){
					leftseat = selectseat - seatlist.size();
					if(selectseat == 1){
						stickseat = 1;
					}else{
						stickseat = 2;
					}
				}else{
					leftseat = selectseat - seatlist.size();
					stickseat = leftseat;
				}
			}else{
				leftseat = selectseat - seatlist.size();
				stickseat = Integer.parseInt(req.getParameter("stickseat"));
			}
		}//붙임좌석 선택시 남은수만큼만 선택가능하게하기위해서 leftseat를 넘김
		
		Theater_playDTO playdto = theaterDAO.getreserinfo(play_num);
		int start_hour = playdto.getStart_time()/60;
		int start_min = playdto.getStart_time()%60;
		String start_hour_s = "";
		String start_min_s = "";
		if(start_hour<10){
			start_hour_s = "0"+String.valueOf(start_hour);
		}else{
			start_hour_s = String.valueOf(start_hour);
		}
		if(start_min<10){
			start_min_s = "0"+String.valueOf(start_min);
		}else{
			start_min_s = String.valueOf(start_min);
		}
		playdto.setStart(start_hour_s+":"+start_min_s);//시작시간 넣어주는 부분
		
		String cinema_addr = cinemaDAO.getAddr(playdto.getCinema_num());
		playdto.setCinema_addr(cinema_addr);//시네마 주소 넣어주는 부분
		
		MovieDTO moviedto = movieDAO.movieView(playdto.getMovie_num());//선택한 영화정보 가져오기
		
		TheaterDTO theaterdto = theaterDAO.getTheater(playdto.getCinema_num(), playdto.getTheater_stage());//상영관 정보가져오기
		
		List<Theater_seatDTO> soldlist = (List<Theater_seatDTO>) theaterDAO.soldseat(playdto.getPlay_num());
		
		ModelAndView mav = new ModelAndView("client/reservation/selectseat");
		mav.addObject("playdto",playdto);
		mav.addObject("moviedto",moviedto);
		mav.addObject("selectseat",selectseat);
		mav.addObject("theaterdto",theaterdto);
		mav.addObject("leftseat",leftseat);
		mav.addObject("stickseat",stickseat);
		mav.addObject("seatlist",seatlist);
		mav.addObject("soldlist",soldlist);
		
		return mav;
	}
	
	@RequestMapping(value="/choice.mo")
	public ModelAndView choiceseat(HttpServletRequest req) throws Exception{
		int stickseat = Integer.parseInt(req.getParameter("stickseat"));
		int selectseat = Integer.parseInt(req.getParameter("selectseat"));
		int play_num = Integer.parseInt(req.getParameter("play_num"));
		int seat_row = Integer.parseInt(req.getParameter("seat_row"));
		int seat_num = Integer.parseInt(req.getParameter("seat_num"));
		HttpSession session = req.getSession();
		List<Theater_seatDTO> list = (List<Theater_seatDTO>) session.getAttribute("seatlist");
		if(stickseat == 1){
			Theater_seatDTO dto = new Theater_seatDTO();
			dto.setPlay_num(play_num);
			dto.setSeat_row(seat_row);
			dto.setSeat_num(seat_num);
			boolean check = true;
			Iterator<Theater_seatDTO> it = list.iterator();
			while(it.hasNext()){
				Theater_seatDTO deldto = it.next();
				if(dto.getSeat_num()==deldto.getSeat_num()&&dto.getSeat_row()==deldto.getSeat_row()){
					it.remove();
					check=false;
					break;
				}
			}
			if(list.size()>=selectseat){
				ModelAndView mav = new ModelAndView("client/message");
				mav.addObject("stickseat",stickseat);
				mav.addObject("selectseat",selectseat);
				mav.addObject("play_num",play_num);
				mav.addObject("msg","선택하신 인원을 초과하셧습니다.");
				mav.addObject("url","selectseat.mo");
				System.out.println("if" + list.size());
				return mav;
			}
			if(check){
				list.add(dto);
			}
		}else if(stickseat == 2){
			Theater_seatDTO dto = new Theater_seatDTO();
			Theater_seatDTO dto2 = new Theater_seatDTO();
			Theater_seatDTO imsidto = new Theater_seatDTO();
			Theater_playDTO playdto = theaterDAO.getreserinfo(play_num);
			TheaterDTO theaterdto = theaterDAO.getTheater(playdto.getCinema_num(), playdto.getTheater_stage());//상영관 정보가져오기
			List<Theater_seatDTO> soldlist = (List<Theater_seatDTO>) theaterDAO.soldseat(playdto.getPlay_num());
			dto.setPlay_num(play_num);
			imsidto.setPlay_num(play_num);
			dto2.setPlay_num(play_num);
			dto.setSeat_row(seat_row);
			imsidto.setSeat_row(seat_row);
			dto2.setSeat_row(seat_row);
			dto.setSeat_num(seat_num);
			imsidto.setSeat_num(seat_num);
			boolean check = false;
			if(seat_num==theaterdto.getSeat_num()){
				dto2.setSeat_num(seat_num-1);
			}else{
				for(Theater_seatDTO seat : list){
					if(seat_num+1==seat.getSeat_num()&&seat_row==seat.getSeat_row()){
						check = true;
					}
				}
				if(check){
					dto2.setSeat_num(seat_num-1);
				}else{
					dto2.setSeat_num(seat_num+1);
				}
			}
			
			if(dto2.getSeat_num() == 0){
				for(Theater_seatDTO solddto : soldlist){
					if(seat_num+1 == solddto.getSeat_num() && seat_row == solddto.getSeat_row()){
						dto2.setSeat_num(seat_num-1);
						break;
					}else{
						dto2.setSeat_num(seat_num+1);
						break;
					}
				}
			}
			
			if(list.size()>=selectseat){
				ModelAndView mav = new ModelAndView("client/message");
				mav.addObject("stickseat",stickseat);
				mav.addObject("selectseat",selectseat);
				mav.addObject("play_num",play_num);
				mav.addObject("msg","선택하신 인원을 초과하셧습니다.");
				mav.addObject("url","selectseat.mo");
				return mav;
			}
			list.add(dto2);
			list.add(dto);
		}
		session.setAttribute("seatlist", list);
		ModelAndView mav = new ModelAndView("client/message");
		mav.addObject("stickseat",stickseat);
		mav.addObject("selectseat",selectseat);
		mav.addObject("play_num",play_num);
		mav.addObject("url","selectseat.mo");
		return mav;
	}
	
	@RequestMapping(value="/notice.mo")
	public ModelAndView notices() throws Exception{
		List<NoticeDTO> list = boardDAO.listNotice();
		ModelAndView mav = new ModelAndView("client/board/notice");
		mav.addObject("noticeList",list);
		return mav;
	}
	
	@RequestMapping(value="/cnoticecontent.mo")
	public ModelAndView noticecontent(@RequestParam int notice_num) throws Exception{
		NoticeDTO dto = boardDAO.getNotice(notice_num);
		ModelAndView mav = new ModelAndView("client/board/noticeContent");
		mav.addObject("getnotice",dto);
		return mav;
	}
	
	@RequestMapping(value="/getMemberInfo.mo")
	public ModelAndView mypageView(HttpServletRequest req, HttpServletResponse resp){
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		String id = (String) session.getAttribute("id");
		MemberDTO medto = memberDAO.getMemberInfo(id);
		ModelAndView mav = new ModelAndView("client/member/mypage");
		mav.addObject("medto", medto);
		mav.addObject("upPath", upPath);
		mav.addObject("id", id);
		return mav;
		
	}
	
	@RequestMapping(value="/update_profile.mo")
	public ModelAndView updateProfile(HttpServletRequest req) throws Exception{
		int user_num = Integer.parseInt(req.getParameter("user_num"));
		MemberDTO medto = memberDAO.getMember(user_num);
		ModelAndView mav = new ModelAndView("client/member/update_profile");
		mav.addObject("medto", medto);
		return mav;
		
	}
	
	@RequestMapping(value="/update_profileP")
	public ModelAndView updateProfileP(MemberDTO dto) throws Exception{
		ModelAndView mav = new ModelAndView("client/message");
		int res = memberDAO.updateProfile(dto);
		System.out.println(res);
		if(res>0) {
			mav.addObject("msg","정보 수정 성공!");
		}else {
			mav.addObject("msg","정보 수정 실패!");
		}
		mav.addObject("url","getMemberInfo.mo?user_num="+dto.getUser_num());
		return mav;
	}
	
	@RequestMapping(value="/update_image")
	public ModelAndView updateImage(HttpServletRequest req) throws Exception{
		MultipartRequest mr = null;
		HttpSession session = req.getSession();
		String upPath = session.getServletContext().getRealPath("/images");
		int len = 50*1024*1024;
		mr = new MultipartRequest(req, upPath, len, "UTF-8");
		int res = memberDAO.updateProfileImage(mr);
		return new ModelAndView("redirect:getMemberInfo.mo");
	}
	
	@RequestMapping(value="/member_passwd.mo")
	public ModelAndView passwdMemberF() throws Exception{
		ModelAndView mav= new ModelAndView();
		mav.setViewName("client/member/update_passwd");
		return mav;
	}
	
	@RequestMapping(value="/member_passwdP.mo")
	public ModelAndView passwdMemberP(@RequestParam String id,@RequestParam String newpasswd, @RequestParam String passwd,HttpServletRequest req) throws Exception{
		List<MemberDTO> list = memberDAO.loginmember(id, passwd);
		ModelAndView mav= new ModelAndView("client/message");
		if(list.size()==0) {
			mav.addObject("msg","현재비밀번호가 일치하지 않습니다.");
			mav.addObject("url","member_passwd.mo");
		}else {
			int res=memberDAO.updatePasswd(id, newpasswd);
			if(res>0){
				mav.addObject("msg","비밀번호 변경 성공.");
				HttpSession session = req.getSession();
				session.removeAttribute("id");				
			}else{
				mav.addObject("msg","비밀번호 변경 실패.");
			}
			mav.addObject("url","kgvindex.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/theaterlist.mo")
	public ModelAndView theaterlist() throws Exception{
		List<CinemaDTO> clist = cinemaDAO.listCinema();
		ModelAndView mav=new ModelAndView("client/theater/theaterlist");
		mav.addObject("clist",clist);
		return mav;
	}
	
	
	@RequestMapping(value="/cinema_list.mo")
	public ModelAndView cinemaList(HttpServletRequest req){
		List<Theater_playDTO> tlist = theaterDAO.listtheaterplay();
		List<CinemaDTO> clist = cinemaDAO.listCinema();
		List<MovieDTO> mlist = new ArrayList<MovieDTO>();
		ModelAndView mav = new ModelAndView("client/cinema/cinema");
		mav.addObject("clist", clist);
		mav.addObject("mlist", mlist);
		mav.addObject("tlist", tlist);
		return mav;
		
	}
	
	@RequestMapping(value="/cinema_view.mo")
	public ModelAndView cinemaView(HttpServletRequest req) throws Exception{
		String movie_nums = req.getParameter("movie_num");
		String cinema_nums = req.getParameter("cinema_num");
		if(cinema_nums==null){
			cinema_nums = "0";
		}
		if(movie_nums==""){
			movie_nums = "0";
		}
		if(cinema_nums==""){
			cinema_nums = "0";
		}
		if(movie_nums==null){
			movie_nums = "0";
		}
		int movie_num = Integer.parseInt(movie_nums);
		int cinema_num = Integer.parseInt(cinema_nums);
		Calendar cal = Calendar.getInstance();
		List<Theater_playDTO> list = theaterDAO.listplayingmovie(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH), cinema_num);
		List<CinemaDTO> clist = cinemaDAO.listCinema();
		List<TheaterDTO> imsi_tList = theaterDAO.listTheater(cinema_num);
		List<Theater_playDTO> tlist = theaterDAO.listtheaterplay();
		List<Theater_playDTO> playlist = new ArrayList<Theater_playDTO>();
		List<MovieDTO> mlist = new ArrayList<MovieDTO>();
		for(Theater_playDTO dto : tlist){
			MovieDTO mdto = null;
			boolean check = true;
			if(dto.getPlay_year()>cal.get(Calendar.YEAR)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()>(cal.get(Calendar.MONTH)+1)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()==(cal.get(Calendar.MONTH)+1)
					&&dto.getPlay_day()>=cal.get(Calendar.DAY_OF_MONTH)){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else{
				continue;
			}
			for(MovieDTO imsi : mlist){
				if(imsi.getMovie_num()==mdto.getMovie_num()){
					check=false;
				}
			}
			if(check){
				mlist.add(mdto);
			}
		}
		//상영중인 영화 한번씩만 들어가있는 리스트 MLIST
		for(Theater_playDTO dto : list) {
			String m_name = movieDAO.nameMovie(dto.getMovie_num());
			CinemaDTO cdto = cinemaDAO.viewCinema(dto.getCinema_num());
			dto.setMovie_name(m_name);
			dto.setCinema_addr(cdto.getCinema_addr());
			int imsi_shour = dto.getStart_time()/60;
			int imsi_ehour = dto.getEnd_time()/60;
			if(imsi_shour>24) {
				imsi_shour -= 24;
			}
			if(imsi_ehour>24) {
				imsi_ehour -= 24;
			}
			String start_hour = String.valueOf(imsi_shour);
			String start_min = String.valueOf(dto.getStart_time()%60);
			String end_hour = String.valueOf(imsi_ehour);
			String end_min = String.valueOf(dto.getEnd_time()%60);
			
			if(start_hour.length()==1) {
				start_hour = "0"+start_hour;
			}
			if(start_min.length()==1) {
				start_min = "0"+start_min;
			}
			if(end_hour.length()==1) {
				end_hour = "0"+end_hour;
			}
			if(end_min.length()==1) {
				end_min = "0"+end_min;
			}
			dto.setStart(start_hour+":"+start_min);
			dto.setEnd(end_hour+":"+end_min);
			
			playlist.add(dto);
			
		}
		//영화제목,영화관이름, 시작시간,끝나는시간 추가한 리스트playlist
		List<TheaterDTO> tList = new ArrayList<TheaterDTO>();
		for(TheaterDTO tdto : imsi_tList) {
			for(Theater_playDTO pdto : playlist) {
				if(tdto.getTheater_stage()==pdto.getTheater_stage()) {
					tList.add(tdto);
					break;
				}
			}
		}
		int now_hour = cal.get(Calendar.HOUR_OF_DAY);
		int now_min = cal.get(Calendar.MINUTE);
		int now_year = cal.get(Calendar.YEAR);
		int now_month = cal.get(Calendar.MONTH)+1;
		int now_day = cal.get(Calendar.DAY_OF_MONTH);
		int now_time = now_hour*60+now_min;
		ModelAndView mav = new ModelAndView("client/cinema/cinema");
		mav.addObject("playlist",playlist);
		mav.addObject("cinema_num",cinema_num);
		mav.addObject("movie_num",movie_num);
		mav.addObject("clist",clist);
		mav.addObject("mlist",mlist);
		mav.addObject("now_year",now_year);
		mav.addObject("now_month",now_month);
		mav.addObject("now_day",now_day);
		mav.addObject("nowtime",now_time);
		mav.addObject("tList", tList);
		mav.addObject("tlist", tlist);
		mav.addObject("selectday",now_day);
		return mav;
		
	}
	//아래는 영화관에서 날짜 선택했을때 오는곳
	@RequestMapping(value="/cinema_views.mo")
	public ModelAndView cinemaViews(HttpServletRequest req) throws Exception{
		Calendar cal = Calendar.getInstance();
		String movie_nums = req.getParameter("movie_num");
		String cinema_nums = req.getParameter("cinema_num");
		String selectdays = req.getParameter("selectday");
		if(cinema_nums==null){
			cinema_nums = "0";
		}
		if(movie_nums==""){
			movie_nums = "0";
		}
		if(cinema_nums==""){
			cinema_nums = "0";
		}
		if(movie_nums==null){
			movie_nums = "0";
		}
		if(selectdays == null){
			selectdays = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		}
		int movie_num = Integer.parseInt(movie_nums);
		int cinema_num = Integer.parseInt(cinema_nums);
		int selectday = Integer.parseInt(selectdays);
		List<Theater_playDTO> list = theaterDAO.listplayingmovie(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,selectday, cinema_num);
		List<CinemaDTO> clist = cinemaDAO.listCinema();
		List<TheaterDTO> imsi_tList = theaterDAO.listTheater(cinema_num);
		List<Theater_playDTO> tlist = theaterDAO.listtheaterplay();
		List<Theater_playDTO> playlist = new ArrayList<Theater_playDTO>();
		List<MovieDTO> mlist = new ArrayList<MovieDTO>();
		for(Theater_playDTO dto : tlist){
			MovieDTO mdto = null;
			boolean check = true;
			if(dto.getPlay_year()==cal.get(Calendar.YEAR)&&dto.getPlay_month()==(cal.get(Calendar.MONTH)+1)
					&&dto.getPlay_day()==selectday){
				mdto = movieDAO.movieView(dto.getMovie_num());
			}else{
				continue;
			}
			for(MovieDTO imsi : mlist){
				if(imsi.getMovie_num()==mdto.getMovie_num()){
					check=false;
				}
			}
			if(check){
				mlist.add(mdto);
			}
		}
		//상영중인 영화 한번씩만 들어가있는 리스트 MLIST
		for(Theater_playDTO dto : list) {
			String m_name = movieDAO.nameMovie(dto.getMovie_num());
			CinemaDTO cdto = cinemaDAO.viewCinema(dto.getCinema_num());
			dto.setMovie_name(m_name);
			dto.setCinema_addr(cdto.getCinema_addr());
			int imsi_shour = dto.getStart_time()/60;
			int imsi_ehour = dto.getEnd_time()/60;
			if(imsi_shour>24) {
				imsi_shour -= 24;
			}
			if(imsi_ehour>24) {
				imsi_ehour -= 24;
			}
			String start_hour = String.valueOf(imsi_shour);
			String start_min = String.valueOf(dto.getStart_time()%60);
			String end_hour = String.valueOf(imsi_ehour);
			String end_min = String.valueOf(dto.getEnd_time()%60);
			
			if(start_hour.length()==1) {
				start_hour = "0"+start_hour;
			}
			if(start_min.length()==1) {
				start_min = "0"+start_min;
			}
			if(end_hour.length()==1) {
				end_hour = "0"+end_hour;
			}
			if(end_min.length()==1) {
				end_min = "0"+end_min;
			}
			dto.setStart(start_hour+":"+start_min);
			dto.setEnd(end_hour+":"+end_min);
			
			playlist.add(dto);
			
		}
		//영화제목,영화관이름, 시작시간,끝나는시간 추가한 리스트playlist
		List<TheaterDTO> tList = new ArrayList<TheaterDTO>();
		for(TheaterDTO tdto : imsi_tList) {
			for(Theater_playDTO pdto : playlist) {
				if(tdto.getTheater_stage()==pdto.getTheater_stage()) {
					tList.add(tdto);
					break;
				}
			}
		}
		int now_hour = cal.get(Calendar.HOUR_OF_DAY);
		int now_min = cal.get(Calendar.MINUTE);
		int now_year = cal.get(Calendar.YEAR);
		int now_month = cal.get(Calendar.MONTH)+1;
		int now_day = cal.get(Calendar.DAY_OF_MONTH);
		int now_time = now_hour*60+now_min;
		ModelAndView mav = new ModelAndView("client/cinema/cinema");
		mav.addObject("playlist",playlist);
		mav.addObject("cinema_num",cinema_num);
		mav.addObject("movie_num",movie_num);
		mav.addObject("clist",clist);
		mav.addObject("mlist",mlist);
		mav.addObject("now_year",now_year);
		mav.addObject("now_month",now_month);
		mav.addObject("now_day",now_day);
		mav.addObject("nowtime",now_time);
		mav.addObject("tList", tList);
		mav.addObject("selectday",selectday);
		return mav;
		
	}
	@RequestMapping(value="/find_passwd.mo")
	public ModelAndView findPasswd(){
		return new ModelAndView("client/member/find_passwd");
	}
	
	@RequestMapping(value="/find_passwdP.mo")
	public ModelAndView findPasswdP(HttpServletRequest req) throws Exception{
		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		String pdto = memberDAO.findPasswd(id, email, hp1, hp2, hp3);
		ModelAndView mav = new ModelAndView("client/message");
		mav.addObject("pdto", pdto);
		if(pdto!=null){
			mav.addObject("msg", "회원님의 비밀번호는 "+pdto+"입니다.");
			mav.addObject("url", "find_passwd.mo");
		}else{
			mav.addObject("msg", "입력정보가 정확하지 않습니다.");
			mav.addObject("url", "find_passwd.mo");
		}
		
		return mav;
		
	}
	
	@RequestMapping(value="/find_id.mo")
	public ModelAndView findIdF() throws Exception{
		ModelAndView mav = new ModelAndView("client/member/findId");
		return mav;
	}
	
	@RequestMapping(value="/find_idP.mo")
	public ModelAndView findIdP(@RequestParam String name, @RequestParam String email) throws Exception{
		String id = memberDAO.findId(name, email);
		ModelAndView mav= new ModelAndView("client/message");
		mav.addObject("id", id);
		if(id!=null) {
			mav.addObject("msg","회원님의 아이디는 "+id+" 입니다.");
			mav.addObject("url","find_id.mo");
		}else {
			mav.addObject("msg", "입력정보가 정확하지 않습니다.");
			mav.addObject("url", "find_id.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/credit.mo")
	public ModelAndView creditP(@RequestParam int play_num,@RequestParam int selectseat,HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		List<Theater_seatDTO> list = (List<Theater_seatDTO>) session.getAttribute("seatlist");
		if(list.size()==0) {
			ModelAndView mav = new ModelAndView("client/message");
			mav.addObject("msg","인원을 선택해주세요.");
			mav.addObject("url","selectseat.mo");
			mav.addObject("play_num",play_num);
			return mav;
		}
		if(selectseat == list.size()) {
			ModelAndView mav = new ModelAndView("client/reservation/credit");
			mav.addObject("play_num",play_num);
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("client/message");
			mav.addObject("msg","좌석선택을 완료해주세요.");
			mav.addObject("url","selectseat.mo?play_num="+play_num+"&seatcount="+selectseat);
			return mav;
		}
	}
	
	@RequestMapping(value="/credit_complete.mo")
	public ModelAndView credit(HttpServletRequest req) throws Exception{
		CreditDTO dto = new CreditDTO();
		Theater_seatDTO seat = new Theater_seatDTO();
		HttpSession session = req.getSession();
		int play_num = Integer.parseInt(req.getParameter("play_num"));
		Theater_playDTO playdto = theaterDAO.getreserinfo(play_num);
		dto.setPlay_num(play_num);
		String id = (String)session.getAttribute("id");
		String name=(String) session.getAttribute("name");
		if(id==null){
			dto.setBuyer(name);
			String tel1 = (String) session.getAttribute("tel1");
			dto.setHp1(tel1);
			String tel2 = (String) session.getAttribute("tel2");
			dto.setHp2(tel2);
			String tel3 = (String) session.getAttribute("tel3");
			dto.setHp3(tel3);
		}else{
			MemberDTO mdto = memberDAO.getMemberInfo(id);
			dto.setBuyer(id);
			dto.setHp1(mdto.getHp1());
			dto.setHp2(mdto.getHp2());
			dto.setHp3(mdto.getHp3());
		}
		List<Theater_seatDTO> seatlist = (List<Theater_seatDTO>) session.getAttribute("seatlist");
		int res = 0;
		for(Theater_seatDTO seatdto : seatlist){
			seat.setPlay_num(play_num);
			seat.setSeat_row(seatdto.getSeat_row());
			seat.setSeat_num(seatdto.getSeat_num());
			dto.setSeat_row(seatdto.getSeat_row());
			dto.setSeat_num(seatdto.getSeat_num());
			res = creditDAO.insertcredit(dto);
			res = theaterDAO.sellseat(seat);
			res = movieDAO.upresercount(playdto.getMovie_num());
			
		}
		ModelAndView mav = new ModelAndView("client/message");
		if(res>0){
			mav.addObject("msg","결제 성공! 예매내역을 확인하세요.");
			mav.addObject("play_num", play_num);
			mav.addObject("url","checkreser.mo");
		}else{
			mav.addObject("msg","결제 실패! 다시 시도해보세요.");
			mav.addObject("url","reservation.mo");
		}
		return mav;
	}
	
	@RequestMapping(value="/checkreser.mo")
	public ModelAndView checkreser(HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String buyer = (String) session.getAttribute("id");
		if(session.getAttribute("id") == null){
			buyer = (String) session.getAttribute("name");
		}
		Calendar cal = Calendar.getInstance();
		String selectdays = req.getParameter("selectday");
		int play_num = Integer.parseInt(req.getParameter("play_num"));
		Theater_playDTO playdto = theaterDAO.getreserinfo(play_num); //예매한 상영영화,영화관 정보
		MovieDTO mvdto = movieDAO.movieView(playdto.getMovie_num());//상영영화정보
		List<Theater_seatDTO> seatlist = (List<Theater_seatDTO>) session.getAttribute("seatlist");
		if(selectdays == null){
			selectdays = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		}
		int selectday = Integer.parseInt(selectdays);
		String start_hour = String.valueOf(playdto.getStart_time()/60);
		String start_min = String.valueOf(playdto.getStart_time()%60);
		String cinema_addr = cinemaDAO.getAddr(playdto.getCinema_num());
		playdto.setCinema_addr(cinema_addr);
		if(start_hour.length()==1){
			start_hour = "0"+start_hour;
		}
		if(start_min.length()==1){
			start_min = "0"+start_min;
		}
		playdto.setStart(start_hour+":"+start_min);
		ModelAndView mav = new ModelAndView("client/reservation/complete_credit");
		mav.addObject("selectday", selectday);
		mav.addObject("playdto",playdto);
		mav.addObject("mvdto",mvdto);
		mav.addObject("seatlist",seatlist);
		mav.addObject("buyer",buyer);
		return mav;
	}
	
	@RequestMapping(value="/sendmail.mo")
	public ModelAndView sendmail(HttpServletRequest req,MemberDTO dto) throws Exception{
		String email = req.getParameter("email");
		boolean checkmail = memberDAO.checnmail(email);
		if(!checkmail) {
			ModelAndView mav = new ModelAndView("client/message");
			mav.addObject("msg","이미 등록된 이메일입니다.");
			mav.addObject("url","register.mo");
			return mav;
		}
		String authNum = RandomNum();
		
		sendEmail(email.toString(),authNum);
		ModelAndView mav = new ModelAndView("client/message");
		mav.addObject("msg","메일을 확인해주세요!");
		mav.addObject("authNum",authNum);
		mav.addObject("email",email);
		mav.addObject("url","register.mo");
		return mav;
	}
	private void sendEmail(String email,String authNum) throws Exception{
		Properties props = new Properties();
		String host = "smtp.mail.(메일 사이트 ex)nate.com)";
		final String user = "메일 사이트 아이디";
		final String password = "메일 사이트 비밀번호";
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port",465);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		InternetAddress to = new InternetAddress(email);
		msg.setFrom(new InternetAddress("메일 주소"));                                           //송신자 설정 
		msg.setRecipient(Message.RecipientType.TO, to);                 //수신자 설정 
		msg.setSubject("KGV 인증번호안내");                                                                                //제목 설정 
		msg.setSentDate(new java.util.Date());                                                     //보내는 날짜 설정 
		msg.setText("인증번호 ["+authNum+"]");                            //내용 설정 (HTML 형식)
		Transport.send(msg);
	}
	
	public String RandomNum() {
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<=5;i++) {
			int n = (int)(Math.random()*10);
			buffer.append(n);
		}
		return buffer.toString();
	}
	
	@RequestMapping(value="/nomemberlogin.mo")
	public ModelAndView nomemberloginP(@RequestParam int play_num) throws Exception{
		ModelAndView mav = new ModelAndView("client/member/nonmember");
		mav.addObject("play_num",play_num);
		return mav;
	}
	
	@RequestMapping(value="/nomemlogin.mo")
	public ModelAndView nomemlogin(HttpServletRequest req,@RequestParam int play_num) throws Exception{
		HttpSession session = req.getSession();
		String name = req.getParameter("name");
		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");
		session.setAttribute("name", name);
		session.setAttribute("tel1", tel1);
		session.setAttribute("tel2", tel2);
		session.setAttribute("tel3", tel3);
		
		return new ModelAndView("redirect:selectseat.mo?play_num="+play_num);
	}
	
	@RequestMapping(value="/viewreser.mo")
	public ModelAndView viewreserP(HttpServletRequest req) throws Exception{
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		if(id == null && req.getParameter("name")==null){
			ModelAndView mav = new ModelAndView("client/member/nonlog");
			return mav;
		}
		List<CreditDTO> clist = null;
		if(id == null){
			clist = creditDAO.getcredit(req.getParameter("name"), Integer.parseInt(req.getParameter("tel1")), Integer.parseInt(req.getParameter("tel2")), Integer.parseInt(req.getParameter("tel3")));
		}else{
			clist = creditDAO.memgetcredit(id);
		}
		List<CreditDTO> list = new ArrayList<CreditDTO>();
		for(CreditDTO dto : clist){
			Theater_playDTO playdto = theaterDAO.getreserinfo(dto.getPlay_num());
			String cinema_addr = cinemaDAO.getAddr(playdto.getCinema_num());
			MovieDTO mdto = movieDAO.movieView(playdto.getMovie_num());
			dto.setCinema_addr(cinema_addr);
			dto.setTitle(mdto.getTitle());
			dto.setTheater_stage(playdto.getTheater_stage());
			String start_hour = String.valueOf(playdto.getStart_time()/60);
			String start_min = String.valueOf(playdto.getStart_time()%60);
			String end_hour = String.valueOf(playdto.getEnd_time()/60);
			String end_min = String.valueOf(playdto.getEnd_time()&60);
			playdto.setCinema_addr(cinema_addr);
			if(start_hour.length()==1){
				start_hour = "0"+start_hour;
			}
			if(start_min.length()==1){
				start_min = "0"+start_min;
			}
			if(end_hour.length()==1){
				end_hour = "0"+end_hour;
			}
			if(end_min.length()==1){
				end_min = "0"+end_min;
			}
			dto.setStart(start_hour+":"+start_min);
			dto.setEnd(end_hour+":"+end_min);
			dto.setPlay_year(playdto.getPlay_year());
			dto.setPlay_month(playdto.getPlay_month());
			dto.setPlay_day(playdto.getPlay_day());
			list.add(dto);
		}
		if(list.size()==0){
			ModelAndView mav = new ModelAndView("client/message");
			mav.addObject("msg","예매내역이 없습니다.");
			mav.addObject("url","kgvindex.mo");
			return mav;
		}
		ModelAndView mav = new ModelAndView("client/reservation/listreservation");
		mav.addObject("clist",list);
		if(id==null) {
			mav.addObject("name",req.getParameter("name"));
			mav.addObject("hp1",req.getParameter("tel1"));
			mav.addObject("hp2",req.getParameter("tel2"));
			mav.addObject("hp3",req.getParameter("tel3"));
		}
		return mav;
	}
	
	@RequestMapping(value="/cancel.mo")
	public ModelAndView cancel(@RequestParam int credit_num,HttpServletRequest req) throws Exception{
		Calendar cal = Calendar.getInstance();
		CreditDTO cdto = creditDAO.getcreditdto(credit_num);
		Theater_playDTO tdto = theaterDAO.getreserinfo(cdto.getPlay_num());
		int to_year = cal.get(Calendar.YEAR);
		int to_month = cal.get(Calendar.MONTH)+1;
		int to_day = cal.get(Calendar.DAY_OF_MONTH);
		int now_hour = cal.get(Calendar.HOUR_OF_DAY);
		int now_min = cal.get(Calendar.MINUTE);
		int nowtime = now_hour*60+now_min;
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		ModelAndView mav = new ModelAndView("client/message");
		
		if(id==null) {
			String name = req.getParameter("name");
			String tel1 = req.getParameter("hp1");
			String tel2 = req.getParameter("hp2");
			String tel3 = req.getParameter("hp3");
			mav.addObject("name",name);
			mav.addObject("tel1",tel1);
			mav.addObject("tel2",tel2);
			mav.addObject("tel3",tel3);
			if(tdto.getPlay_year()<to_year){
				mav.addObject("msg","취소가 불가능합니다.");
				mav.addObject("url","viewreser.mo?name="+name+"&tel1="+tel1+"&tel2="+tel2+"&tel3="+tel3);
				return mav;
			}else if(tdto.getPlay_year()==to_year && tdto.getPlay_month()<to_month){
				mav.addObject("msg","취소가 불가능합니다.");
				mav.addObject("url","viewreser.mo?name="+name+"&tel1="+tel1+"&tel2="+tel2+"&tel3="+tel3);
				return mav;
			}else if(tdto.getPlay_year()==to_year && tdto.getPlay_month()==to_month && tdto.getPlay_day()<to_day){
				mav.addObject("msg","취소가 불가능합니다.");
				mav.addObject("url","viewreser.mo?name="+name+"&tel1="+tel1+"&tel2="+tel2+"&tel3="+tel3);
				return mav;
			}else if(tdto.getPlay_year()==to_year && tdto.getPlay_month()==to_month && tdto.getPlay_day()==to_day && tdto.getStart_time()<nowtime){
				mav.addObject("msg","취소가 불가능합니다.");
				mav.addObject("url","viewreser.mo?name="+name+"&tel1="+tel1+"&tel2="+tel2+"&tel3="+tel3);
				return mav;
			}else if(tdto.getPlay_year()==to_year && tdto.getPlay_month()==to_month && tdto.getPlay_day()==to_day && tdto.getStart_time()>=nowtime){
				if(tdto.getStart_time()<(nowtime-10)){
					mav.addObject("msg","취소가 불가능합니다.");
					mav.addObject("url","viewreser.mo?name="+name+"&tel1="+tel1+"&tel2="+tel2+"&tel3="+tel3);
					return mav;
				}
			}
			theaterDAO.refundseat(cdto.getPlay_num(), cdto.getSeat_row(), cdto.getSeat_num());
			int res = creditDAO.refundcredit(credit_num);
			mav.addObject("msg","취소되었습니다.");
			mav.addObject("url","viewreser.mo?name="+name+"&tel1="+tel1+"&tel2="+tel2+"&tel3="+tel3);
			return mav;
		}else {
			if(tdto.getPlay_year()<to_year){
				System.out.println("1");
				mav.addObject("msg","취소가 불가능합니다.");
				mav.addObject("url","viewreser.mo");
				return mav;
			}else if(tdto.getPlay_year()==to_year && tdto.getPlay_month()<to_month){
				System.out.println("2");
				mav.addObject("msg","취소가 불가능합니다.");
				mav.addObject("url","viewreser.mo");
				return mav;
			}else if(tdto.getPlay_year()==to_year && tdto.getPlay_month()==to_month && tdto.getPlay_day()<to_day){
				System.out.println("3");
				mav.addObject("msg","취소가 불가능합니다.");
				mav.addObject("url","viewreser.mo");
				return mav;
			}else if(tdto.getPlay_year()==to_year && tdto.getPlay_month()==to_month && tdto.getPlay_day()==to_day && tdto.getStart_time()<nowtime){
				mav.addObject("msg","취소가 불가능합니다.");
				mav.addObject("url","viewreser.mo");
				return mav;
			}else if(tdto.getPlay_year()==to_year && tdto.getPlay_month()==to_month && tdto.getPlay_day()==to_day && tdto.getStart_time()>=nowtime){
				if(tdto.getStart_time()<(nowtime-10)){
					System.out.println("gdgd");
					mav.addObject("msg","취소가 불가능합니다.");
					mav.addObject("url","viewreser.mo");
					return mav;
				}
			}
			theaterDAO.refundseat(cdto.getPlay_num(), cdto.getSeat_row(), cdto.getSeat_num());
			int res = creditDAO.refundcredit(credit_num);
			mav.addObject("msg","취소되었습니다.");
			mav.addObject("url","viewreser.mo");
			return mav;
		}
		
		
	}
	
	@RequestMapping(value="/resetseat.mo")
	public ModelAndView resetseat(HttpServletRequest req) throws Exception{
		int play_num = Integer.parseInt(req.getParameter("play_num"));
		int selectseat = Integer.parseInt(req.getParameter("selectseat"));
		HttpSession session = req.getSession();
		List<Theater_seatDTO> list = new ArrayList<Theater_seatDTO>();
		session.setAttribute("seatlist", list);
		ModelAndView mav = new ModelAndView("selectseat.mo?play_num="+play_num+"&selectseat="+selectseat);
		return mav;
	}
}
