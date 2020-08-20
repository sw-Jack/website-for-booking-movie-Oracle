package movie.admin.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;

import movie.admin.category.dao.CategoryDAO;
import movie.admin.category.dto.CategoryDTO;
import movie.admin.product.dao.ProductDAO;
import movie.admin.product.dto.ProductDTO;

@Controller
public class ProductController {
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	
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

}
