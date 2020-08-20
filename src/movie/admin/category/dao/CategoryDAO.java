package movie.admin.category.dao;

import java.util.List;

import movie.admin.category.dto.CategoryDTO;

public interface CategoryDAO {
	public int insertCategory(CategoryDTO dto);
	public int deleteCategory(int cnum);
	public List<CategoryDTO> listCategory();

}
