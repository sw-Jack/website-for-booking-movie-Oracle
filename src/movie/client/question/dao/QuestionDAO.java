package movie.client.question.dao;

import java.util.List;

import movie.client.question.dto.QuestionDTO;



public interface QuestionDAO {
	
	public int insertQuestion(QuestionDTO dto);
	public List<QuestionDTO> listQuestion(int user_num);
	public QuestionDTO getQuestion(int question_num);
	public int deleteQuestion(int question_num);
	public int updateQuestion(QuestionDTO dto);
	public QuestionDTO getcquestion(String name);

}
