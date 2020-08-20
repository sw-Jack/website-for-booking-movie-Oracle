package movie.admin.product.dao;

import java.util.List;

import com.oreilly.servlet.MultipartRequest;

import movie.admin.product.dto.ProductDTO;

public interface ProductDAO {
	
	public int insertProduct(MultipartRequest mr);
	public int deleteProduct(int pnum);
	public int updateProduct(MultipartRequest mr);
	public List<ProductDTO> listProduct();
	public ProductDTO getProduct(int pnum);
	public List<ProductDTO> findProduct(String search, String searchString);
	public List<ProductDTO> storeList(String ccode);

}
