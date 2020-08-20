package movie.admin.theater.dto;

public class TheaterDTO {
	private int theater_stage;
	private String theater_grade;
	private int theater_seatcount;
	private int cinema_num;
	private int seat_row;
	private int seat_num;
	
	public int getTheater_stage() {
		return theater_stage;
	}
	public void setTheater_stage(int theater_stage) {
		this.theater_stage = theater_stage;
	}
	public String getTheater_grade() {
		return theater_grade;
	}
	public void setTheater_grade(String theater_grade) {
		this.theater_grade = theater_grade;
	}
	public int getTheater_seatcount() {
		return theater_seatcount;
	}
	public void setTheater_seatcount(int theater_seatcount) {
		this.theater_seatcount = theater_seatcount;
	}
	public int getCinema_num() {
		return cinema_num;
	}
	public void setCinema_num(int cinema_num) {
		this.cinema_num = cinema_num;
	}
	public int getSeat_row() {
		return seat_row;
	}
	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}
	public int getSeat_num() {
		return seat_num;
	}
	public void setSeat_num(int seat_num) {
		this.seat_num = seat_num;
	}
}
