package model;

import java.io.Serializable;

public class Student implements Serializable{
	public String MaSV;
	public String TenSV;
	public String MaLop;
	//public Boolean Gioitinh;

	
	
	
	public String getMaSV() {
		return MaSV;
	}
	public void setMaSV(String maSV) {
		MaSV = maSV;
	}
	public String getTenSV() {
		return TenSV;
	}
	public void setTenSV(String tenSV) {
		TenSV = tenSV;
	}
	
	public String getMaLop() {
		return MaLop;
	}
	public void setMaLop(String maLop) {
		MaLop = maLop;
	}
//	public Boolean getGioitinh() {
//		return Gioitinh;
//	}
//	public void setGioitinh(Boolean gioiTinh) {
//		Gioitinh = gioiTinh;
//	}
	
	
	
}
