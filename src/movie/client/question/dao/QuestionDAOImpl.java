package movie.client.question.dao;

import java.util.List;

import movie.client.question.dto.QuestionDTO;
import movie.client.question.mapper.QuestionMapper;

public class QuestionDAOImpl implements QuestionDAO {

	@Override
	public int insertQuestion(QuestionDTO dto) {
		return QuestionMapper.insertQuestion(dto);
		
	}

	@Override
	public List<QuestionDTO> listQuestion(int user_num) {
		return QuestionMapper.questionList(user_num);
	}

	@Override
	public QuestionDTO getQuestion(int question_num) {
		return QuestionMapper.getQuestion(question_num);
	}

	@Override
	public int deleteQuestion(int question_num) {
		return QuestionMapper.deleteQuestion(question_num);
	}

	@Override
	public int updateQuestion(QuestionDTO dto) {
		return QuestionMapper.updateQuestion(dto);
	}

	@Override
	public QuestionDTO getcquestion(String name) {
		// TODO Auto-generated method stub
		return QuestionMapper.getcquestion(name);
	}

	

}
