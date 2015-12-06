package adapter;

import fpoly.androi.com.R;

import java.util.List;

import model.LopHoc;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

public class ClassAdapter extends ArrayAdapter<LopHoc>{
	public ClassAdapter(Context context, List<LopHoc> reports) {
		super(context, R.layout.item_class, reports);
	}
	
	/**
	 * Được gọi để lấy view của mỗi item trên listview
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LopHoc lh = getItem(position);
		
		LayoutInflater inflater =((Activity) getContext()).getLayoutInflater();
		View row = inflater.inflate(R.layout.item_class, null, true);
		
		TextView lblMaLop = (TextView) row.findViewById(R.id.lblMaLop);
		TextView lblTenLop = (TextView) row.findViewById(R.id.lblTenLop);
		
		lblMaLop.setText(lh.MaLop);
		lblTenLop.setText(lh.TenLop);

		return row;
	}

}
