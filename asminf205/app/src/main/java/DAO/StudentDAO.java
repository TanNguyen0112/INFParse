package DAO;

import java.util.ArrayList;
import java.util.List;

import model.Student;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.DbHelper;

public class StudentDAO {
SQLiteDatabase db;
	
	public StudentDAO(Activity activity) {
		db = new DbHelper(activity).getWritableDatabase();
	}
	
	public void insert(Student sv) {
		ContentValues values = new ContentValues();
		values.put("masv", sv.MaSV);
		values.put("tensv", sv.TenSV);
		values.put("malop", sv.MaLop);
		
		
		db.insert("student", null, values);
	}
	
	public void update(Student sv) {
		ContentValues values = new ContentValues();
		
		values.put("masv", sv.MaSV);
		values.put("tensv", sv.TenSV);
		values.put("malop", sv.MaLop);
		
		db.update("student", values, "masv=?", new String[]{String.valueOf(sv.MaSV)});
	}
	
	public void delete(String masv) {
		db.delete("student", "masv=?", new String[]{String.valueOf(masv)});
	}
	
	public Student getStudent(String masv) {
		String sql = "SELECT * FROM student WHERE masv=?";
		List<Student> list = getByStudent(sql);
		return list.get(0);
	}
	
	public List<Student> getStudent() {
	String sql = "SELECT * FROM student";
		return getByStudent(sql);
	}

	public List<Student> getByStudent(String sql, String...args) {
		List<Student> list = new ArrayList<Student>();
		
		Cursor c = db.rawQuery(sql, args);
		while (c.moveToNext()){
			Student sv = new Student();
			sv.MaSV = c.getString(c.getColumnIndex("masv"));
			sv.TenSV = c.getString(c.getColumnIndex("tensv"));
			sv.MaLop = c.getString(c.getColumnIndex("malop"));
			
			
			list.add(sv);
		} 
		c.close();
		return list;
	}
}
