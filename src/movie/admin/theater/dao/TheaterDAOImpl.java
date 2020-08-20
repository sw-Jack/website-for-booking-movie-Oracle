package movie.admin.theater.dao;

import java.util.List;

import movie.admin.theater.dto.TheaterDTO;
import movie.admin.theater.dto.Theater_playDTO;
import movie.admin.theater.dto.Theater_seatDTO;
import movie.admin.theater.mapper.TheaterMapper;

public class TheaterDAOImpl implements TheaterDAO{

	@Override
	public int insertTheater(TheaterDTO dto) {
		// TODO Auto-generated method stub
		return TheaterMapper.insertTheater(dto);
	}

	@Override
	public List<TheaterDTO> listTheater(int cinema_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.listTheater(cinema_num);
	}

	@Override
	public int deleteTheater(int cinema_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.deleteTheater(cinema_num);
	}

	@Override
	public List<TheaterDTO> alllistTheater() {
		// TODO Auto-generated method stub
		return TheaterMapper.alllistTheater();
	}

	@Override
	public List<Theater_playDTO> timecalc(int theater_stage, int play_year, int play_month, int play_day,int cinema_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.timecalc(theater_stage, play_year, play_month, play_day,cinema_num);
	}

	@Override
	public int insertTheaterplay(Theater_playDTO dto) {
		// TODO Auto-generated method stub
		return TheaterMapper.insertTheaterplay(dto);
	}

	@Override
	public TheaterDTO getTheater(int cinema_num, int theater_stage) {
		// TODO Auto-generated method stub
		return TheaterMapper.getTheater(cinema_num, theater_stage);
	}

	@Override
	public int deleteTheater1(int cinema_num, int theater_stage) {
		// TODO Auto-generated method stub
		return TheaterMapper.deleteTheater1(cinema_num, theater_stage);
	}

	@Override
	public int updateTheater(TheaterDTO dto) {
		// TODO Auto-generated method stub
		return TheaterMapper.updateTheater(dto);
	}

	@Override
	public int countTheater(int cinema_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.countTheater(cinema_num);
	}

	@Override
	public List<TheaterDTO> checkTheater(int cinema_num, int theater_stage) {
		// TODO Auto-generated method stub
		return TheaterMapper.checkTheater(cinema_num, theater_stage);
	}

	@Override
	public int getSize(int cinema_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.getSize(cinema_num);
	}

	@Override
	public int deletePlay(int cinema_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.deletePlay(cinema_num);
	}

	@Override
	public List<Theater_playDTO> getplay(int play_year, int play_month, int play_day, int cinema_num,int movie_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.getplay(play_year, play_month, play_day, cinema_num,movie_num);
	}

	@Override
	public List<Theater_playDTO> listtheaterplay() {
		// TODO Auto-generated method stub
		return TheaterMapper.listtheaterplay();
	}

	@Override
	public Theater_playDTO getreserinfo(int play_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.getreserinfo(play_num);
	}

	@Override
	public int deleteplay(int movie_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.deleteplay(movie_num);
	}

	@Override
	public List<Theater_seatDTO> soldseat(int play_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.soldseat(play_num);
	}

	@Override
	public List<Theater_playDTO> listplayingmovie(int play_year, int play_month, int play_day, int cinema_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.listplayingmovie(play_year, play_month, play_day, cinema_num);
	}

	@Override
	public List<Theater_playDTO> getplist(int movie_num, int cinema_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.getplist(movie_num, cinema_num);
	}

	@Override
	public int sellseat(Theater_seatDTO dto) {
		// TODO Auto-generated method stub
		return TheaterMapper.sellseat(dto);
	}

	@Override
	public int refundseat(int play_num, int seat_row, int seat_num) {
		// TODO Auto-generated method stub
		return TheaterMapper.refundseat(play_num, seat_row, seat_num);
	}

}
