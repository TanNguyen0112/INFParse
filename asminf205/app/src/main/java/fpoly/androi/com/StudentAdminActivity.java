package fpoly.androi.com;



import java.util.ArrayList;
import java.util.List;

import model.LopHoc;

import DAO.ClassDAO;
import DAO.StudentDAO;
import adapter.ClassAdapter;
import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;


public class StudentAdminActivity extends ActionBarActivity {
	ClassDAO daoclass;
	SQLiteDatabase db;
	ClassAdapter adapterclass;
	Button btnxemLop, btnaddClass, btmana;

	ListView lvclass;
	List<LopHoc> livClass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_admin);


		daoclass = new ClassDAO(this);


		btmana = (Button)findViewById(R.id.btnmana);
		btnaddClass = (Button)findViewById(R.id.btnaddclass);
		btnxemLop = (Button)findViewById(R.id.btnxemdslop);
		btnaddClass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(StudentAdminActivity.this);
				dialog.setContentView(R.layout.dialog_add_class);
				dialog.setTitle("Thêm Lớp");
				// Lấy Control
				final EditText txtMaLop = (EditText)dialog.findViewById(R.id.edtcodeclass);
				final EditText txtTenLop = (EditText)dialog.findViewById(R.id.edtnameclass);
				final Button btThemLop = (Button)dialog.findViewById(R.id.btnaddclassdia);
				final Button btXoaTrang = (Button)dialog.findViewById(R.id.btnresetclass);


				lvclass= (ListView)dialog.findViewById(R.id.lvClass);
				loadClass();
				lvclass.setOnItemClickListener(new OnItemClickListener() {

					final Button btsua = (Button)dialog.findViewById(R.id.btnaddclassdia);
					final Button btdelete = (Button)dialog.findViewById(R.id.btnresetclass);
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
											int position, long arg3) {
						// TODO Auto-generated method stub
						LopHoc lh = adapterclass.getItem(position);
						txtMaLop.setText(lh.getMaLop());
						txtTenLop.setText(lh.getTenLop());
						dialog.setTitle("Sửa Lớp");
						btsua.setText("Sửa");
						btdelete.setText("Xóa");

						btsua.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								try{
									LopHoc lh = new LopHoc();
									lh.setMaLop(txtMaLop.getText().toString());
									lh.setTenLop(txtTenLop.getText().toString());
									ParseQuery<ParseObject> query = ParseQuery.getQuery("LopHoc");
									query.whereEqualTo("MaLop", lh.MaLop);
									query.getFirstInBackground(new GetCallback<ParseObject>() {
										@Override
										public void done(ParseObject object, ParseException e) {
											if (e == null) {
												object.put("MaLop", txtMaLop.getText().toString());
												object.put("TenLop", txtTenLop.getText().toString());

												object.saveInBackground();

												finish();
											} else {
												Toast.makeText(StudentAdminActivity.this, "Lỗi !", Toast.LENGTH_LONG).show();
											}
										}
									});
									daoclass.update(lh);

									Toast.makeText(StudentAdminActivity.this, "Đã cập nhật thành công", Toast.LENGTH_SHORT).show();
									loadClass();
								}
								catch(Exception e){
									Toast.makeText(StudentAdminActivity.this, "Cập Nhật Thất bại", Toast.LENGTH_SHORT).show();
								}
							}
						});
						dialog.show();
						btdelete.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								try{
									String code = txtMaLop.getText().toString();
									ParseQuery<ParseObject> query = ParseQuery.getQuery("LopHoc");
									query.whereEqualTo("MaLop", code);
									query.getFirstInBackground(new GetCallback<ParseObject>() {
										@Override
										public void done(ParseObject object, ParseException e) {
											if(e==null){
												try {
													object.delete();
												} catch (ParseException e1) {
													e1.printStackTrace();
													Toast.makeText(StudentAdminActivity.this, "Lỗi !",Toast.LENGTH_LONG).show();
												}
												object.saveInBackground();
												//Toast.makeText(StudentAdminActivity.this, "Xóa tin thành công!",Toast.LENGTH_LONG).show();
												finish();
											}else {
												Toast.makeText(StudentAdminActivity.this, "Lỗi !",Toast.LENGTH_LONG).show();
											}
										}
									});

									daoclass.delete(code);

									Toast.makeText(StudentAdminActivity.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
									loadClass();
								}
								catch(Exception e){
									Toast.makeText(StudentAdminActivity.this, "Xóa Thất bại", Toast.LENGTH_SHORT).show();
								}



							}
						});

					}
				});

				dialog.show();


				btThemLop.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						try{
							LopHoc lh = new LopHoc();
							lh.MaLop = txtMaLop.getText().toString();
							lh.TenLop = txtTenLop.getText().toString();


							if(txtMaLop.getText().toString().trim().equals("")){
								Toast.makeText(StudentAdminActivity.this, "Chưa nhập mã lớp", Toast.LENGTH_SHORT).show();
							}else if(txtTenLop.getText().toString().trim().equals("")){
								Toast.makeText(StudentAdminActivity.this, "Chưa nhập tên lớp", Toast.LENGTH_SHORT).show();
							} else {
								ParseObject gclass = new ParseObject("LopHoc");
								gclass.put("MaLop", lh.MaLop);
								gclass.put("TenLop", lh.TenLop);
								gclass.saveInBackground();
								daoclass.insert(lh);
								loadClass();
								Toast.makeText(StudentAdminActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

							}
						}catch(Exception e){
							Toast.makeText(StudentAdminActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
						}
					}
				});
				btXoaTrang.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						txtMaLop.setText("");
						txtTenLop.setText("");
					}
				});

				dialog.show();

			}
		});

		btnxemLop.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(StudentAdminActivity.this, ListClassActivity.class);
				startActivity(intent);

			}
		});

		btmana.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StudentAdminActivity.this, ManagementActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student_admin, menu);
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

	public void loadClass(){
		daoclass = new ClassDAO(this);
		List<LopHoc> lh = daoclass.getLop();
		adapterclass = new ClassAdapter(this, lh);
		lvclass.setAdapter(adapterclass);
	}

}
