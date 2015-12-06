package model;

import java.io.Serializable;

public class LopHoc implements Serializable{
	public String MaLop;

	public String TenLop;
	
	
	public String getMaLop() {
		return MaLop;
	}
	public void setMaLop(String maLop) {
		MaLop = maLop;
	}
	public String getTenLop() {
		return TenLop;
	}
	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	
}
