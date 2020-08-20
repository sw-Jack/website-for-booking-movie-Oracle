package movie.admin.question.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.admin.question.dao.QuestionDAO;
import movie.admin.question.dto.QuestionDTO;

@Controller
public class QuestionController {
	@Autowired
	public QuestionDAO questionDAO;
	
	public void setQuestionDAO(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

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
}
