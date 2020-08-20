package movie.admin.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.admin.member.dao.MemberDAO;
import movie.admin.member.dto.MemberDTO;
@Controller
public class MemberController {
	@Autowired
	private MemberDAO memberDAO;
	
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
		mav.setViewName("member/memberUpdate");
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
	
	

}
