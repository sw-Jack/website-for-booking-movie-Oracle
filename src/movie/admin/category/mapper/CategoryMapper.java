package movie.admin.category.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.admin.category.dto.CategoryDTO;

public class CategoryMapper {
	
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
	
	public static int insertCategory(CategoryDTO dto) {
		SqlSession session= sqlMapper.openSession();
		int res = session.insert("cateInsert",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<CategoryDTO> listCategory(){
		SqlSession session =sqlMapper.openSession();
		List<CategoryDTO> list=session.selectList("cateList");
		session.close();
		return list;
		
	}
	
	public static int deleteCategory(int cnum) {
		SqlSession session =sqlMapper.openSession();
		int res= session.delete("cateDelete",cnum);
		session.commit();
		session.close();
		return res;
	}

}
