package movie.admin.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.admin.board.dao.BoardDAO;
import movie.admin.board.dto.NoticeDTO;

@Controller	
public class BoardController {
	
	@Autowired
	private BoardDAO boardDAO;
	
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
}
