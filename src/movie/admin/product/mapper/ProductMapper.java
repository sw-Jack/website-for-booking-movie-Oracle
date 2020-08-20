package movie.admin.product.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.admin.member.dto.MemberDTO;
import movie.admin.product.dto.ProductDTO;

public class ProductMapper {
	private static SqlSessionFactory sqlMapper;

	static {
		try {
			String resource = "SqlMapConfig.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			reader.close(); 
		} catch (IOException e) {
			// Fail fast.
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}
	
	public static int insertProduct(ProductDTO dto) {
		SqlSession session=sqlMapper.openSession();
		int res= session.insert("insertProd",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<ProductDTO> listProduct(){
		SqlSession session =sqlMapper.openSession();
		List<ProductDTO> list=session.selectList("listProd");
		session.close();
		return list;
	}
	
	public static ProductDTO getProd(int pnum) {
		SqlSession session= sqlMapper.openSession();
		ProductDTO dto=session.selectOne("getProd",pnum);
		session.close();
		return dto;
	}
	
	public static int updateProduct(ProductDTO dto){
		SqlSession session = sqlMapper.openSession();
		int res = session.update("updateProd",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int deleteProduct(int pnum){
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("deleteProd",pnum);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<ProductDTO> findProduct(String search, String searchString){
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("search", search);
		map.put("searchString", searchString);
		SqlSession session= sqlMapper.openSession();
		List<ProductDTO> list= session.selectList("findProd",map);
		session.close();
		return list;
	}
	public static List<ProductDTO> cateProd(String ccode){
		SqlSession session = sqlMapper.openSession();
		List<ProductDTO> list = session.selectList("cateProd",ccode);
		session.close();
		return list;
	}

}
