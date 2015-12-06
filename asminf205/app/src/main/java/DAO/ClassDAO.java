package DAO;

import java.util.ArrayList;
import java.util.List;

import model.LopHoc;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.DbHelper;


public class ClassDAO {
	SQLiteDatabase db;
	
	public ClassDAO(Activity activity) {
		db = new DbHelper(activity).getWritableDatabase();
	}
	
	public void insert(LopHoc lh) {
		ContentValues values = new ContentValues();
		
		values.put("malop", lh.MaLop);
		values.put("tenlop", lh.TenLop);
		db.insert("lophoc", null, values);
	}
	
	public void update(LopHoc lh) {
		
		ContentValues values = new ContentValues();
		values.put("malop", lh.MaLop);
		values.put("tenlop", lh.TenLop);
		 db.update("lophoc", values, "malop=?", new String[]{lh.MaLop});
	}
	
	public void delete(String malop) {
		db.delete("lophoc", "malop=?", new String[]{malop});
	}
	

	public LopHoc getLop(String malop) {
		String sql = "SELECT * FROM lophoc WHERE malh=?";
		List<LopHoc> list = getBySql(sql, malop);
		return list.get(0);
	}
	
	public List<LopHoc> getLop() {
		String sql = "SELECT * FROM lophoc";
		return getBySql(sql);
	}
	
	public List<LopHoc> getBySql(String sql, String...args) {
		List<LopHoc> list = new ArrayList<LopHoc>();
		
		Cursor c = db.rawQuery(sql, args);
		while (c.moveToNext()){
			LopHoc  lh = new LopHoc();
			lh.MaLop = c.getString(c.getColumnIndex("malop"));
			lh.TenLop = c.getString(c.getColumnIndex("tenlop"));

			list.add(lh);
		} 
		c.close();
		return list;
	}
}
