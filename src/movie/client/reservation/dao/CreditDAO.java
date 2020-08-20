package movie.client.reservation.dao;

import java.util.List;

import movie.client.reservation.dto.CreditDTO;

public interface CreditDAO {
	public int insertcredit(CreditDTO dto);
	public List<CreditDTO> getcredit(String buyer,int hp1,int hp2,int hp3);
	public List<CreditDTO> memgetcredit(String buyer);
	public CreditDTO getcreditdto(int credit_num);
	public int refundcredit(int credit_num);
}
