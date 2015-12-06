package fpoly.androi.com;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.Build;

public class AdminActivity extends ActionBarActivity {
private ImageButton btstudent, btnews, btmaps, btshare;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
btstudent = (ImageButton)findViewById(R.id.ibtncourse);
btnews = (ImageButton)findViewById(R.id.ibtnnew);
btmaps = (ImageButton)findViewById(R.id.ibtnmaps);
btshare = (ImageButton)findViewById(R.id.ibtnsocial);
	btstudent.setOnClickListener(new ImageButton.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(AdminActivity.this, StudentAdminActivity.class);
		startActivity(intent);
			
		}
	});
	btnews.setOnClickListener(new ImageButton.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(AdminActivity.this, NewsActivity.class);
		startActivity(intent);
			
		}
	});
	
btmaps.setOnClickListener(new ImageButton.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(AdminActivity.this, MapsActivity.class);
		startActivity(intent);
			
		}
	});
btshare.setOnClickListener(new ImageButton.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		Bitmap bitmap = takeScreenshot();
		saveBitmap(bitmap);
		

		String path = saveBitmap(bitmap);
		Intent intent = new Intent(Intent.ACTION_SEND);
	    intent.setType("image/*");
	    File imageFileToShare = new File(path);
	 
	    Uri uri = Uri.fromFile(imageFileToShare);
	    intent.putExtra(Intent.EXTRA_STREAM, uri);
	 
	    startActivity(Intent.createChooser(intent, "Share Image!"));
//		Uri screenshotUri = Uri.parse(path); 

//
//		intent.setType("image/png"); 
//		intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
//
//		startActivity(Intent.createChooser(intent, "Share"));
		
	}
});

	}
	public Bitmap takeScreenshot(){
		View rootView = findViewById(android.R.id.content).getRootView();

		rootView.setDrawingCacheEnabled(true);

		return rootView.getDrawingCache();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin, menu);
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


	public String saveBitmap(Bitmap bitmap){
		File imgPath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(imgPath);
			bitmap.compress(CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
		}
		catch(FileNotFoundException e){
			Log.e("GREC", e.getMessage(), e);
		}
		catch(IOException e){
			Log.e("GREC", e.getMessage(), e);
		}
		return imgPath.getAbsolutePath();
	}

}
