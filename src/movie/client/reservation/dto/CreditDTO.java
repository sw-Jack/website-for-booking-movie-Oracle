package movie.client.reservation.dto;

public class CreditDTO {
	private int credit_num;
	private int play_num;
	private int seat_row;
	private int seat_num;
	private String buyer;
	private String hp1;
	private String hp2;
	private String hp3;
	transient String title;
	transient int theater_stage;
	transient String cinema_addr;
	transient String start;
	transient String end;
	transient int play_year;
	transient int play_month;
	transient int play_day;
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
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getTheater_stage() {
		return theater_stage;
	}
	public void setTheater_stage(int theater_stage) {
		this.theater_stage = theater_stage;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getHp1() {
		return hp1;
	}
	public void setHp1(String hp1) {
		this.hp1 = hp1;
	}
	public String getHp2() {
		return hp2;
	}
	public void setHp2(String hp2) {
		this.hp2 = hp2;
	}
	public String getHp3() {
		return hp3;
	}
	public void setHp3(String hp3) {
		this.hp3 = hp3;
	}
	public int getCredit_num() {
		return credit_num;
	}
	public void setCredit_num(int credit_num) {
		this.credit_num = credit_num;
	}
	public int getPlay_num() {
		return play_num;
	}
	public void setPlay_num(int play_num) {
		this.play_num = play_num;
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
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
}
