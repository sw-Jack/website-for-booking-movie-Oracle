package movie.admin.cinema.dao;

import java.util.List;

import movie.admin.cinema.dto.CinemaDTO;
import movie.admin.cinema.mapper.CinemaMapper;
import movie.admin.member.dto.AdminDTO;

public class CinemaDAOImpl implements CinemaDAO{
	
	@Override
	public int insertCinema(CinemaDTO dto) {
		// TODO Auto-generated method stub
		return CinemaMapper.insertCinema(dto);
	}

	@Override
	public CinemaDTO getCinema(String cinema_addr) {
		// TODO Auto-generated method stub
		return CinemaMapper.getCinema(cinema_addr);
	}

	@Override
	public List<CinemaDTO> listCinema() {
		// TODO Auto-generated method stub
		return CinemaMapper.listCinema();
	}

	@Override
	public CinemaDTO viewCinema(int cinema_num) {
		// TODO Auto-generated method stub
		return CinemaMapper.viewCinema(cinema_num);
	}

	@Override
	public int deleteCinema(int cinema_num) {
		// TODO Auto-generated method stub
		return CinemaMapper.deleteCinema(cinema_num);
	}

	@Override
	public int sizeupCinema(int cinema_num) {
		// TODO Auto-generated method stub
		return CinemaMapper.sizeupCinema(cinema_num);
	}

	@Override
	public int sizedownCinema(int cinema_num) {
		// TODO Auto-generated method stub
		return CinemaMapper.sizedownCinema(cinema_num);
	}

	@Override
	public List<CinemaDTO> checkCinema(String cinema_addr) {
		// TODO Auto-generated method stub
		return CinemaMapper.checkCinema(cinema_addr);
	}

	@Override
	public String getAddr(int cinema_num) {
		// TODO Auto-generated method stub
		return CinemaMapper.getAddr(cinema_num);
	}



}
