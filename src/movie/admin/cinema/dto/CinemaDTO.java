package movie.admin.cinema.dto;

public class CinemaDTO {
	private int cinema_num;
	private String cinema_addr;
	private int cinema_size;//°ü¼ö
	private String cinema_admin;
	
	public int getCinema_num() {
		return cinema_num;
	}
	public void setCinema_num(int cinema_num) {
		this.cinema_num = cinema_num;
	}
	public String getCinema_addr() {
		return cinema_addr;
	}
	public void setCinema_addr(String cinema_addr) {
		this.cinema_addr = cinema_addr;
	}
	public int getCinema_size() {
		return cinema_size;
	}
	public void setCinema_size(int cinema_size) {
		this.cinema_size = cinema_size;
	}
	public String getCinema_admin() {
		return cinema_admin;
	}
	public void setCinema_admin(String cinema_admin) {
		this.cinema_admin = cinema_admin;
	}
}
