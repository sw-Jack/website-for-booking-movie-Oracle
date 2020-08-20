package movie.client.question.controller;

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

import movie.admin.member.dao.MemberDAO;
import movie.admin.member.dto.MemberDTO;
import movie.client.question.dao.QuestionDAO;
import movie.client.question.dto.QuestionDTO;
@Controller
public class QuestionController {
	
	@Autowired
	public QuestionDAO cquestionDAO;
	@Autowired
	public MemberDAO memberDAO;
	
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
	public ModelAndView updateQuestionF(@RequestParam int question_num) throws Exception{
		QuestionDTO dto=cquestionDAO.getQuestion(question_num);
		ModelAndView mav= new ModelAndView();
		mav.setViewName("client/board/question_update");
		mav.addObject("getQuestion", dto);
		return mav;
	}
	
	@RequestMapping(value="/cquestion_updateP.mo", method=RequestMethod.POST)
	public ModelAndView updateQuestionP(QuestionDTO dto,@RequestParam String id) throws Exception{
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
	

}
