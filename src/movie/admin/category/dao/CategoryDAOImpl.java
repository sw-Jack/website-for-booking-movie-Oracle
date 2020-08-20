package movie.admin.category.dao;

import java.util.List;

import movie.admin.category.dto.CategoryDTO;
import movie.admin.category.mapper.CategoryMapper;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public int insertCategory(CategoryDTO dto) {
		return CategoryMapper.insertCategory(dto);
	}

	@Override
	public int deleteCategory(int cnum) {
		return CategoryMapper.deleteCategory(cnum);
	}

	@Override
	public List<CategoryDTO> listCategory() {
		return CategoryMapper.listCategory();
	}

}
