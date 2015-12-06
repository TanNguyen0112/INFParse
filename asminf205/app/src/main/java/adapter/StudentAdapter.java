package adapter;

import fpoly.androi.com.R;

import java.util.List;


import model.Student;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StudentAdapter extends ArrayAdapter<Student>{
	public StudentAdapter(Context context, List<Student> reports) {
		super(context, R.layout.item_student, reports);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		Student sv = getItem(position);
		
		LayoutInflater inflater =((Activity) getContext()).getLayoutInflater();
		View row = inflater.inflate(R.layout.item_student, null, true);
		
		TextView lblTensv = (TextView) row.findViewById(R.id.lblName);
		TextView lblLop = (TextView) row.findViewById(R.id.lbllop);
		TextView lblMa = (TextView) row.findViewById(R.id.lblMa);
		
		
		
		
		lblTensv.setText(sv.TenSV);
		lblLop.setText(sv.MaLop);
		lblMa.setText(sv.MaSV);
	
		
		return row;
	}

}
