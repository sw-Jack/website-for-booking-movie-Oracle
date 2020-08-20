package movie.client.question.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.client.question.dto.QuestionDTO;

public class QuestionMapper {
	
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
	
	public static int insertQuestion(QuestionDTO dto) {
		SqlSession session= sqlMapper.openSession();
		int res= session.insert("insertQuestion",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<QuestionDTO> questionList(int user_num){
		SqlSession session= sqlMapper.openSession();
		List<QuestionDTO> questionList =session.selectList("listQuestion",user_num);
		session.close();
		return questionList;
	}
	
	public static QuestionDTO getQuestion(int question_num){
		SqlSession session= sqlMapper.openSession();
		QuestionDTO dto=session.selectOne("contentQuestion",question_num);
		session.close();
		return dto;
	}
	
	public static int updateQuestion(QuestionDTO dto){
		SqlSession session= sqlMapper.openSession();
		int res= session.update("updateQuestion",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int deleteQuestion(int question_num) {
		SqlSession session= sqlMapper.openSession();
		int res= session.delete("deleteQuestion2",question_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static QuestionDTO getcquestion(String name){
		SqlSession session= sqlMapper.openSession();
		QuestionDTO dto = session.selectOne("getcquestion",name);
		session.close();
		return dto;
	}

}
