package movie.admin.member.dao;

import java.util.List;

import com.oreilly.servlet.MultipartRequest;

import movie.admin.member.dto.AdminDTO;
import movie.admin.member.dto.MemberDTO;
import movie.admin.member.mapper.MemberMapper;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public List<MemberDTO> listMember() {
		return MemberMapper.listMember();
	}

	@Override
	public int deleteMember(int user_num) {
		return MemberMapper.deleteMember(user_num);
	}

	@Override
	public MemberDTO getMember(int user_num) {
		return MemberMapper.getMember(user_num);
	}

	@Override
	public int updateMember(MemberDTO dto) {
		return MemberMapper.updateMember(dto);
	}

	@Override
	public List<MemberDTO> findMember(String search, String searchString) {
		return MemberMapper.findMember(search, searchString);
	}

	@Override
	public List<AdminDTO> checkadminId(String admin_id) {
		// TODO Auto-generated method stub
		return MemberMapper.checkadminId(admin_id);
	}

	@Override
	public int insertAdmin(AdminDTO dto) {
		// TODO Auto-generated method stub
		return MemberMapper.adminInsert(dto);
	}

	@Override
	public int deleteAdmin(int cinema_num) {
		// TODO Auto-generated method stub
		return MemberMapper.adminDelete(cinema_num);
	}

	@Override
	public List<AdminDTO> adminLogin(String admin_id, String admin_passwd) {
		// TODO Auto-generated method stub
		return MemberMapper.adminLogin(admin_id, admin_passwd);
	}

	@Override
	public AdminDTO getAdmin(int cinema_num) {
		// TODO Auto-generated method stub
		return MemberMapper.getAdmin(cinema_num);
	}

	@Override
	public AdminDTO idgetAdmin(String admin_id) {
		// TODO Auto-generated method stub
		return MemberMapper.idgetAdmin(admin_id);
	}

	@Override
	public List<MemberDTO> clientcheckId(String id) {
		// TODO Auto-generated method stub
		return MemberMapper.clientcheckId(id);
	}

	@Override
	public int insertmember(MultipartRequest mr) {
		// TODO Auto-generated method stub
		MemberDTO dto = new MemberDTO();
		dto.setId(mr.getParameter("id"));
		dto.setName(mr.getParameter("name"));
		dto.setPasswd(mr.getParameter("passwd"));
		dto.setEmail(mr.getParameter("email"));
		dto.setBirth_year(mr.getParameter("birth_year"));
		dto.setBirth_month(mr.getParameter("birth_month"));
		dto.setBirth_day(mr.getParameter("birth_day"));
		dto.setHp1(mr.getParameter("hp1"));
		dto.setHp2(mr.getParameter("hp2"));
		dto.setHp3(mr.getParameter("hp3"));
		dto.setAdrr_code(mr.getParameter("adrr_code"));
		dto.setAdrr(mr.getParameter("adrr"));
		dto.setAddr_detail(mr.getParameter("addr_detail"));
		String member_image = mr.getFilesystemName("member_image");
		if(member_image==null){
			member_image="basicimage.jpg";
		}
		dto.setMember_image(member_image);
		return MemberMapper.insertmember(dto);
	}

	@Override
	public List<MemberDTO> loginmember(String id, String passwd) {
		// TODO Auto-generated method stub
		return MemberMapper.memberlogin(id, passwd);
	}
	@Override
	public MemberDTO getMemberInfo(String id) {
		// TODO Auto-generated method stub
		return MemberMapper.getMemberInfo(id);
	}

	@Override
	public int updateProfileImage(MultipartRequest mr) {
		// TODO Auto-generated method stub
		String image = mr.getFilesystemName("file1");
		int user_num = Integer.parseInt(mr.getParameter("user_num"));
		return MemberMapper.updateProfileImage(image,user_num);
	}

	@Override
	public int updateProfile(MemberDTO dto) {
		// TODO Auto-generated method stub
		return MemberMapper.updateProfile(dto);
	}

	@Override
	public int updatePasswd(String id, String passwd) {
		return MemberMapper.updatePasswd(id, passwd);
	}

	@Override
	public String findPasswd(String id, String email, String hp1, String hp2, String hp3) {
		// TODO Auto-generated method stub
		return MemberMapper.findPasswd(id, email, hp1, hp2, hp3);
	}
	
	@Override
	public String findId(String name, String email) {
		// TODO Auto-generated method stub
		return MemberMapper.findId(name, email);
	}

	@Override
	public boolean checnmail(String email) {
		// TODO Auto-generated method stub
		return MemberMapper.checkmail(email);
	}

}
