package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
	public static final String DB_NAME = "QuanLy";
	public static final int DB_VERSION = 3;
	
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableSql = 
				"CREATE TABLE lophoc (" +
				"malop TEXT PRIMARY KEY,  " +
				
				"tenlop TEXT NOT NULL)";
		db.execSQL(createTableSql);
		String createTableSql2 = 
				"CREATE TABLE student (" +
				"masv TEXT PRIMARY KEY,  " +
				"tensv TEXT NOT NULL,"+
				"malop TEXT NOT NULL	REFERENCES tenlop(malop))";
		db.execSQL(createTableSql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropTableSql = "DROP TABLE IF EXISTS lophoc";
		db.execSQL(dropTableSql);
		
		String dropTableSql2 = "DROP TABLE IF EXISTS student";
		db.execSQL(dropTableSql2);
		onCreate(db);
	}
}

