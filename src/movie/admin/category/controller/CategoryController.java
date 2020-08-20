package movie.admin.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import movie.admin.category.dao.CategoryDAO;
import movie.admin.category.dto.CategoryDTO;


@Controller
public class CategoryController {
	@Autowired
	private CategoryDAO categoryDAO;
	
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

}
