package movie.client.reservation.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.client.reservation.dto.CreditDTO;

public class CreditMapper {
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
	
	public static int insertcredit(CreditDTO dto){
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("insertcredit",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<CreditDTO> getcredit(String buyer,int hp1,int hp2,int hp3){
		SqlSession session = sqlMapper.openSession();
		Map map = new HashMap();
		map.put("buyer", buyer);
		map.put("hp1", hp1);
		map.put("hp2", hp2);
		map.put("hp3", hp3);
		List<CreditDTO> list = session.selectList("getcredit",map);
		session.close();
		return list;
	}
	public static List<CreditDTO> memgetcredit(String buyer){
		SqlSession session = sqlMapper.openSession();
		List<CreditDTO> list = session.selectList("memgetcredit",buyer);
		session.close();
		return list;
	}
	
	public static CreditDTO getcreditdto(int credit_num){
		SqlSession session = sqlMapper.openSession();
		CreditDTO dto = session.selectOne("getcreditdto",credit_num);
		session.close();
		return dto;
	}
	
	public static int refundcredit(int credit_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("refundcredit",credit_num);
		session.commit();
		session.close();
		return res;
	}
}
