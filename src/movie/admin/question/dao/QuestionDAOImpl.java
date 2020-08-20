package movie.admin.question.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import movie.admin.question.dto.QuestionDTO;
import movie.admin.question.mapper.QuestionMapper;

public class QuestionDAOImpl implements QuestionDAO{

	@Override
	public List<QuestionDTO> questionList(int startRow, int endRow){
		// TODO Auto-generated method stub
		return QuestionMapper.questionList(startRow, endRow);
	}

	@Override
	public QuestionDTO getQuestion(int question_num) {
		// TODO Auto-generated method stub
		return QuestionMapper.getQuestion(question_num);
	}

	@Override
	public int answer(QuestionDTO qdto) {
		// TODO Auto-generated method stub
		int question_num = qdto.getQuestion_num();
		if(question_num!=0){
			QuestionMapper.answer(qdto);
		}
		qdto.setRe_step(qdto.getRe_step()+1);
		qdto.setRe_level(qdto.getRe_level()+1);
		return QuestionMapper.answerQuestion(qdto);
	}

	@Override
	public int pagecount() {
		// TODO Auto-generated method stub
		return QuestionMapper.pageCount();
	}

	@Override
	public int deleteQuestion(int question_num) {
		// TODO Auto-generated method stub
		return QuestionMapper.deleteQuestion(question_num);
	}

}
