package movie.client.reservation.dao;

import java.util.List;

import movie.client.reservation.dto.CreditDTO;
import movie.client.reservation.mapper.CreditMapper;

public class CreditDAOImpl implements CreditDAO{

	@Override
	public int insertcredit(CreditDTO dto) {
		// TODO Auto-generated method stub
		return CreditMapper.insertcredit(dto);
	}

	@Override
	public List<CreditDTO> getcredit(String buyer, int hp1, int hp2, int hp3) {
		// TODO Auto-generated method stub
		return CreditMapper.getcredit(buyer, hp1, hp2, hp3);
	}

	@Override
	public List<CreditDTO> memgetcredit(String buyer) {
		// TODO Auto-generated method stub
		return CreditMapper.memgetcredit(buyer);
	}

	@Override
	public CreditDTO getcreditdto(int credit_num) {
		// TODO Auto-generated method stub
		return CreditMapper.getcreditdto(credit_num);
	}

	@Override
	public int refundcredit(int credit_num) {
		// TODO Auto-generated method stub
		return CreditMapper.refundcredit(credit_num);
	}
	
	
}
