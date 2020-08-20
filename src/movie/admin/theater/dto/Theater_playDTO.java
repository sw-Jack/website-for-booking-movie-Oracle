package movie.admin.theater.dto;

public class Theater_playDTO {
	private int play_year;
	private int play_month;
	private int play_day;
	private int start_time;
	private int end_time;
	private int movie_num;
	private int cinema_num;
	private int theater_stage;
	private int play_num;
	transient String movie_name;
	transient int start_time_hour;
	transient int start_time_min;
	transient int end_time_hour;
	transient int end_time_min;
	transient String start;
	transient String end;
	transient String cinema_addr;
	transient int seatcount;
	public int getSeatcount() {
		return seatcount;
	}
	public void setSeatcount(int seatcount) {
		this.seatcount = seatcount;
	}
	public String getCinema_addr() {
		return cinema_addr;
	}
	public void setCinema_addr(String cinema_addr) {
		this.cinema_addr = cinema_addr;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getStart_time_hour() {
		return start_time_hour;
	}
	public void setStart_time_hour(int start_time_hour) {
		this.start_time_hour = start_time_hour;
	}
	public int getStart_time_min() {
		return start_time_min;
	}
	public void setStart_time_min(int start_time_min) {
		this.start_time_min = start_time_min;
	}
	public int getEnd_time_hour() {
		return end_time_hour;
	}
	public void setEnd_time_hour(int end_time_hour) {
		this.end_time_hour = end_time_hour;
	}
	public int getEnd_time_min() {
		return end_time_min;
	}
	public void setEnd_time_min(int end_time_min) {
		this.end_time_min = end_time_min;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public int getPlay_num() {
		return play_num;
	}
	public void setPlay_num(int play_num) {
		this.play_num = play_num;
	}
	public int getPlay_year() {
		return play_year;
	}
	public void setPlay_year(int play_year) {
		this.play_year = play_year;
	}
	public int getPlay_month() {
		return play_month;
	}
	public void setPlay_month(int play_month) {
		this.play_month = play_month;
	}
	public int getPlay_day() {
		return play_day;
	}
	public void setPlay_day(int play_day) {
		this.play_day = play_day;
	}
	public int getStart_time() {
		return start_time;
	}
	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}
	public int getEnd_time() {
		return end_time;
	}
	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}
	public int getMovie_num() {
		return movie_num;
	}
	public void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}
	public int getCinema_num() {
		return cinema_num;
	}
	public void setCinema_num(int cinema_num) {
		this.cinema_num = cinema_num;
	}
	public int getTheater_stage() {
		return theater_stage;
	}
	public void setTheater_stage(int theater_stage) {
		this.theater_stage = theater_stage;
	}
}
