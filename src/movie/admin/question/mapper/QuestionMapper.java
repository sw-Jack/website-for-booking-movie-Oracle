package movie.admin.question.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.admin.question.dto.QuestionDTO;

public class QuestionMapper {
	private static SqlSessionFactory sqlMapper;
	// 시작을 나타낼때 씀
	static {
		try {//xml파일을 자바에서 읽어오게끔해줌
			String resource = "SqlMapConfig.xml"; 
			Reader reader = Resources.getResourceAsReader(resource); 
			sqlMapper = new SqlSessionFactoryBuilder().build(reader); 
			reader.close();
		} catch (IOException e) {
			// Fail fast.
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}
	public static List<QuestionDTO> questionList(int startRow, int endRow){
		SqlSession session = sqlMapper.openSession();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<QuestionDTO> questionList = session.selectList("questionList", map);
		session.close();
		return questionList;
	}
	public static int pageCount(){
		SqlSession session = sqlMapper.openSession();
		int res = session.selectOne("pageCount");
		session.close();
		return res;
	}
	public static QuestionDTO getQuestion(int question_num){
		SqlSession session = sqlMapper.openSession();
		QuestionDTO qdto = session.selectOne("getQuestion", question_num);
		session.close();
		return qdto;
	}
	public static int deleteQuestion(int question_num){
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("deleteQuestion", question_num);
		session.commit();
		session.close();
		return res;
	}
	public static int answerQuestion(QuestionDTO qdto){
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("answerQuestion", qdto);
		session.commit();
		session.close();
		return res;
	}
	public static int answer(QuestionDTO qdto){
		SqlSession session = sqlMapper.openSession();
		int res = session.update("answer", qdto);
		session.commit();
		session.close();
		return res;
	}
	public static int selectQuestion(){
		SqlSession session = sqlMapper.openSession();
		int res = session.selectOne("selectQuestion");
		session.close();
		return res;
	}
}
