package movie.admin.member.dao;

import java.util.List;

import com.oreilly.servlet.MultipartRequest;

import movie.admin.member.dto.AdminDTO;
import movie.admin.member.dto.MemberDTO;

public interface MemberDAO {

	public List<MemberDTO> listMember();
	public int deleteMember(int user_num);
	public MemberDTO getMember(int user_num);
	public int updateMember(MemberDTO dto);
	public List<MemberDTO> findMember(String search, String searchString);
	public List<AdminDTO> checkadminId(String admin_id);
	public int insertAdmin(AdminDTO dto);
	public int deleteAdmin(int cinema_num);
	public List<AdminDTO> adminLogin(String admin_id,String admin_passwd);
	public AdminDTO getAdmin(int cinema_num);
	public AdminDTO idgetAdmin(String admin_id);
	public List<MemberDTO> clientcheckId(String id);
	public int insertmember(MultipartRequest mr);
	public List<MemberDTO> loginmember(String id,String passwd);
	public MemberDTO getMemberInfo(String id);
	public int updateProfileImage(MultipartRequest mr);
	public int updateProfile(MemberDTO dto);
	public int updatePasswd(String id,String passwd);
	public String findPasswd(String id, String email, String hp1, String hp2, String hp3);
	public String findId(String name, String email);
	public boolean checnmail(String email);
	
}
