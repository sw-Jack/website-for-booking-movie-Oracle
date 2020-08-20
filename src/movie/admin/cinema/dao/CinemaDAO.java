package movie.admin.cinema.dao;

import java.util.List;

import movie.admin.cinema.dto.CinemaDTO;
import movie.admin.member.dto.AdminDTO;

public interface CinemaDAO {
	public int insertCinema(CinemaDTO dto);
	public CinemaDTO getCinema(String cinema_addr);
	public List<CinemaDTO> listCinema();
	public CinemaDTO viewCinema(int cinema_num);
	public int deleteCinema(int cinema_num);
	public int sizeupCinema(int cinema_num);
	public int sizedownCinema(int cinema_num);
	public List<CinemaDTO> checkCinema(String cinema_addr);
	public String getAddr(int cinema_num);
}
