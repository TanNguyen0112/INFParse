package fpoly.androi.com;


import java.util.ArrayList;
import java.util.List;



import model.LopHoc;
import DAO.ClassDAO;
import DAO.StudentDAO;
import adapter.ClassAdapter;
import adapter.StudentAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ListClassActivity extends ActionBarActivity {
ClassDAO daoclass;
ClassAdapter adapterClass;
List<LopHoc> livClass;

ListView lvlop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_class);
		lvlop = (ListView) findViewById(R.id.listclass);
		
		//daoclass = new ClassDAO(this);
		//livClass = daoclass.getLop();


		final ArrayList<String> listclassa = new ArrayList<String>();

		ParseQuery<ParseObject> queery = ParseQuery.getQuery("LopHoc");

		queery.findInBackground(new FindCallback<ParseObject>() {
			public void done(List<ParseObject> scoreList, ParseException e) {
				if (e == null) {
					for (ParseObject lh : scoreList) {
						listclassa.add(lh.getString("TenLop") + "  " + lh.getString("MaLop"));
					}
					ArrayAdapter adater = new ArrayAdapter(
							ListClassActivity.this, android.R.layout.simple_list_item_1,
							listclassa
					);
					lvlop.setAdapter(adater);
				} else {
					Log.d("score", "Error: " + e.getMessage());
				}
			}
		});

		lvlop.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				

				Intent intent = new Intent(ListClassActivity.this, ListStudentActivity.class);
				

				startActivity(intent);
			}
		});
		


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_class, menu);
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
