package movie.admin.question.dao;

import java.util.List;

import movie.admin.question.dto.QuestionDTO;

public interface QuestionDAO {
	public List<QuestionDTO> questionList(int startRow, int endRow);
	public QuestionDTO getQuestion(int question_num);
	public int answer(QuestionDTO qdto); 
	public int pagecount();
	public int deleteQuestion(int question_num);
}
