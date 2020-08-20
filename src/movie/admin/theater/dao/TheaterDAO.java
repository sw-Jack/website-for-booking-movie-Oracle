package movie.admin.theater.dao;

import java.util.List;

import movie.admin.theater.dto.TheaterDTO;
import movie.admin.theater.dto.Theater_playDTO;
import movie.admin.theater.dto.Theater_seatDTO;

public interface TheaterDAO {
	public int insertTheater(TheaterDTO dto);
	public List<TheaterDTO> listTheater(int cinema_num);
	public int deleteTheater(int cinema_num);
	public List<TheaterDTO> alllistTheater();
	public List<Theater_playDTO> timecalc(int theater_stage,int play_year,int play_month,int play_day,int cinema_num);
	public int insertTheaterplay(Theater_playDTO dto);
	public TheaterDTO getTheater(int cinema_num, int theater_stage);
	public int deleteTheater1(int cinema_num, int theater_stage);
	public int updateTheater(TheaterDTO dto);
	public int countTheater(int cinema_num);
	public List<TheaterDTO> checkTheater(int cinema_num,int theater_stage);
	public int getSize(int cinema_num);
	public int deletePlay(int cinema_num);
	public List<Theater_playDTO> getplay(int play_year,int play_month,int play_day,int cinema_num,int movie_num);
	public List<Theater_playDTO> listtheaterplay();
	public Theater_playDTO getreserinfo(int play_num);
	public int deleteplay(int movie_num);
	public List<Theater_seatDTO> soldseat(int play_num);
	//추가
	public List<Theater_playDTO> listplayingmovie(int play_year,int play_month,int play_day,int cinema_num);
	public List<Theater_playDTO> getplist(int movie_num,int cinema_num);
	public int sellseat(Theater_seatDTO dto);
	public int refundseat(int play_num,int seat_row,int seat_num);
	
	
}
