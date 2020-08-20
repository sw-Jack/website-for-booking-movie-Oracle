package movie.admin.member.mapper;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import movie.admin.member.dto.AdminDTO;
import movie.admin.member.dto.MemberDTO;

public class MemberMapper {
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
	
	public static List<MemberDTO> listMember(){
		SqlSession session=sqlMapper.openSession();
		List<MemberDTO> list=session.selectList("listMember");
		session.close();
		return list;
	}
	
	public static MemberDTO getMember(int user_num) {
		SqlSession session=sqlMapper.openSession();
		MemberDTO dto=session.selectOne("getMember",user_num);
		session.close();
		return dto;
	}
	
	public static List<MemberDTO> findMember(String search, String searchString){
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("search", search);
		map.put("searchString", searchString);
		SqlSession session= sqlMapper.openSession();
		List<MemberDTO> list= session.selectList("findMember",map);
		session.close();
		return list;
	}
	
	public static int updateMember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		int res=session.update("updateMember",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int deleteMember(int user_num) {
		SqlSession session=sqlMapper.openSession();
		int res= session.delete("deleteMember",user_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<AdminDTO> checkadminId(String admin_id){
		SqlSession session = sqlMapper.openSession();
		List<AdminDTO> list = session.selectList("checkadminId",admin_id);
		session.close();
		return list;
	}
	
	public static int adminInsert(AdminDTO dto) {
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("adminInsert",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int adminDelete(int cinema_num) {
		SqlSession session = sqlMapper.openSession();
		int res = session.delete("adminDelete",cinema_num);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<AdminDTO> adminLogin(String admin_id,String admin_passwd){
		SqlSession session = sqlMapper.openSession();
		Map<String,String> map = new HashMap<String,String>();
		map.put("admin_id", admin_id);
		map.put("admin_passwd", admin_passwd);
		List<AdminDTO> list = session.selectList("adminLogin",map);
		session.close();
		return list;
	}
	
	public static AdminDTO getAdmin(int cinema_num){
		SqlSession session = sqlMapper.openSession();
		AdminDTO dto = session.selectOne("getAdmin",cinema_num);
		session.close();
		return dto;
	}
	
	public static AdminDTO idgetAdmin(String admin_id){
		SqlSession session = sqlMapper.openSession();
		AdminDTO dto = session.selectOne("idgetAdmin",admin_id);
		session.close();
		return dto;
	}
	
	public static List<MemberDTO> clientcheckId(String id){
		SqlSession session = sqlMapper.openSession();
		List<MemberDTO> list = session.selectList("clientcheckid",id);
		session.close();
		return list;
	}
	
	public static int insertmember(MemberDTO dto) {
		SqlSession session = sqlMapper.openSession();
		int res = session.insert("insertmember",dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static List<MemberDTO> memberlogin(String id,String passwd){
		SqlSession session = sqlMapper.openSession();
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("passwd", passwd);
		List<MemberDTO> list = session.selectList("loginmember",map);
		session.close();
		return list;
	}
	
	public static MemberDTO getMemberInfo(String id) {
		SqlSession session = sqlMapper.openSession();
		MemberDTO dto = session.selectOne("getMemberInfo", id);
		session.close();
		return dto;
	}
	
	public static int updateProfileImage(String member_image,int user_num){
		SqlSession session = sqlMapper.openSession();
		Map map = new HashMap();
		map.put("member_image", member_image);
		map.put("user_num", user_num);
		int res = session.update("updateProfileImage", map);
		session.commit();
		session.close();
		return res;
	}
	
	public static int updateProfile(MemberDTO dto){
		SqlSession session = sqlMapper.openSession();
		int res = session.update("updateProfile", dto);
		session.commit();
		session.close();
		return res;
	}
	
	public static int updatePasswd(String id,String passwd) {
		SqlSession session=sqlMapper.openSession();
		Map<String,String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("passwd",passwd);
		int res= session.update("updatepasswd",map);
		session.commit();
		session.close();
		return res;
	}
	
	public static String findPasswd(String id, String email, String hp1, String hp2, String hp3){
		SqlSession session = sqlMapper.openSession();
		Map map = new HashMap();
		map.put("id", id);
		map.put("email", email);
		map.put("hp1", hp1);
		map.put("hp2", hp2);
		map.put("hp3", hp3);
		String dto = (String) session.selectOne("findPasswd", map);
		session.close();
		return dto;
	}
	
	public static String findId(String name, String email) {
		SqlSession session=sqlMapper.openSession();
		Map<String, String> map= new HashMap<String,String>();
		map.put("name", name);
		map.put("email", email);
		String id=session.selectOne("findid",map);
		session.close();
		return id;
	}
	
	public static boolean checkmail(String email) {
		SqlSession session=sqlMapper.openSession();
		List<MemberDTO> list = session.selectList("checkmail",email);
		session.close();
		if(list.size()>0) {
			return false;
		}else {
			return true;
		}
	}
}
