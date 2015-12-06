package fpoly.androi.com;

import java.util.ArrayList;
import java.util.List;

import model.LopHoc;
import model.Student;
import DAO.ClassDAO;
import DAO.StudentDAO;
import adapter.ClassAdapter;
import adapter.StudentAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ListStudentActivity extends ActionBarActivity {
//	StudentDAO daoclass;
//	StudentAdapter adapterClass;
	//List<Student> livClass;

	ListView lvstudent;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_list_student);
			lvstudent = (ListView) findViewById(R.id.listVStudent);
			final ArrayList<String> listclassa = new ArrayList<String>();



			//parse, get lophoc
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");

			query.findInBackground(new FindCallback<ParseObject>() {
				public void done(List<ParseObject> scoreList, ParseException e) {
					if (e == null) {
						for(ParseObject sv : scoreList){
							listclassa.add( sv.getString("TenSV") +"      " +sv.getString("MaSV"));
						}
						ArrayAdapter adater = new ArrayAdapter(
								ListStudentActivity.this, android.R.layout.simple_list_item_1,
								listclassa
						);
						lvstudent.setAdapter(adater);
					} else {
						Log.d("score", "Error: " + e.getMessage());
					}
				}
			});

//
//			daoclass = new StudentDAO(this);
//			livClass = daoclass.getStudent();
			
			
//			adapterClass = new StudentAdapter(this, livClass);
//			lvlop.setAdapter(adapterClass);
//
//			lvlop.setOnItemClickListener(new OnItemClickListener(){
//				@Override
//				public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
//					
//					String malop = livClass.get(position).MaLop;
//
//					Intent intent = new Intent( ListStudentActivity.this, ListClassActivity.class);
//					
//					Bundle b = new Bundle();
//					
//					b.putString("malop", malop);
//					intent.putExtra("lop", b);
//					startActivity(intent);
//				}
//			});
//			


		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_student, menu);
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

	

}
