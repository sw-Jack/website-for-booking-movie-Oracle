package movie.admin.product.dao;

import java.util.List;

import com.oreilly.servlet.MultipartRequest;

import movie.admin.product.dto.ProductDTO;
import movie.admin.product.mapper.ProductMapper;


public class ProductDAOImpl implements ProductDAO {
	
	private SetDTO set = new SetDTO();
	@Override
	public int insertProduct(MultipartRequest mr) {
		String pcategory =mr.getParameter("pcategory");
		String pimage=mr.getFilesystemName("pimage");
		ProductDTO dto = set.setDTO(mr, pcategory, pimage);
		return ProductMapper.insertProduct(dto);
	}

	@Override
	public int deleteProduct(int pnum) {
		return ProductMapper.deleteProduct(pnum);
	}

	@Override
	public int updateProduct(MultipartRequest mr) {
		String pimage = null,pcategory = null;
		if(mr.getFilesystemName("pimage")==null){
			pimage = mr.getParameter("pimage2");
		}else{
			pimage = mr.getFilesystemName("pimage");
		}
		ProductDTO dto = set.setDTO(mr, pcategory, pimage);
		return ProductMapper.updateProduct(dto);
	}

	@Override
	public List<ProductDTO> listProduct() {
		return ProductMapper.listProduct();
	}

	@Override
	public ProductDTO getProduct(int pnum) {
		return ProductMapper.getProd(pnum);
	}
	
	@Override
	public List<ProductDTO> findProduct(String search, String searchString) {
		return ProductMapper.findProduct(search, searchString);
	}
	
	private class SetDTO{
		ProductDTO dto;
		public SetDTO(){
			dto = new ProductDTO();
		}
		public ProductDTO setDTO(MultipartRequest mr,String pcategory,String pimage){
			String pnum = mr.getParameter("pnum");
			if(pnum==null){
				pnum = "0";
			}
			dto.setPcategory(pcategory);
			dto.setPnum(Integer.parseInt(pnum));
			dto.setPname(mr.getParameter("pname"));
			dto.setPprice(Integer.parseInt(mr.getParameter("pprice")));
			dto.setPqty(Integer.parseInt(mr.getParameter("pqty")));
			dto.setPimage(pimage);
			dto.setPpoint(Integer.parseInt(mr.getParameter("ppoint")));
			dto.setPcontent(mr.getParameter("pcontent"));
			return dto;
		}
	}
	@Override
	public List<ProductDTO> storeList(String ccode) {
		// TODO Auto-generated method stub
		return ProductMapper.cateProd(ccode);
	}
	

	

}
