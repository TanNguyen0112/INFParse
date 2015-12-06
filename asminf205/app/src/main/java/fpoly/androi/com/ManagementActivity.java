package fpoly.androi.com;

import java.util.ArrayList;
import java.util.List;

import model.LopHoc;
import model.Student;
import DAO.ClassDAO;
import DAO.StudentDAO;
import adapter.ClassAdapter;
import adapter.StudentAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Build;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ManagementActivity extends ActionBarActivity {
	ClassDAO daoclass;
	StudentDAO daostudent;
	SQLiteDatabase db;
	ClassAdapter adapterclass;
	StudentAdapter adapterstudent;
	Button btnthemsv, btnxoa;

	ListView lvclass;
	List<LopHoc> livClass;
	List<Student> livStudent;
	ArrayAdapter<String> adapClass;
	EditText txtmasv, txttensv;
	Spinner spnClass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management);
		daoclass = new ClassDAO(this);
		daostudent = new StudentDAO(this);
		spnClass = (Spinner)findViewById(R.id.spinclass);

		txtmasv = (EditText)findViewById(R.id.edtcodestudent);
		txttensv = (EditText)findViewById(R.id.edtnamestudent);
		btnthemsv = (Button)findViewById(R.id.btnaddstudent);
		btnxoa = (Button)findViewById(R.id.btnresetstudent);
		spinerClass();



		btnthemsv.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(txtmasv.getText().toString().trim().equalsIgnoreCase("")){
					Toast.makeText(ManagementActivity.this, "Chưa Nhập Mã Sinh Viên", Toast.LENGTH_LONG).show();
				}
				else if(txttensv.getText().toString().trim().equalsIgnoreCase("")){
					Toast.makeText(ManagementActivity.this, "Chưa nhận tên Sinh Viên ", Toast.LENGTH_LONG).show();
				}
				else{
					final String spClass = spnClass.getSelectedItem().toString();

					Student sv = new Student();
					sv.MaSV = txtmasv.getText().toString();
					sv.TenSV = txttensv.getText().toString();
					sv.MaLop = spClass;
					ParseObject gclass = new ParseObject("Student");
					gclass.put("MaSV", sv.MaSV);
					gclass.put("TenSV", sv.TenSV);
					gclass.put("MaLop", sv.MaLop);
					gclass.saveInBackground();
					daostudent.insert(sv);
					Toast.makeText(ManagementActivity.this, "Thêm Sinh Viên Thành Công", Toast.LENGTH_LONG).show();
				}}
		});

		btnxoa.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				txtmasv.setText("");
				txttensv.setText("");
			}
		});

		btnxoa.setOnLongClickListener(new Button.OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				String masv  = txtmasv.getText().toString();
				daostudent.delete(masv);
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.management, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void spinerClass(){
		livClass = daoclass.getLop();
		String[] arrayClass = new String[livClass.size()];
		for(int i=0; i<livClass.size(); i++){
			arrayClass[i]= livClass.get(i).TenLop;
		}
		adapClass = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayClass);
		spnClass.setAdapter(adapClass);
	}

}
