package fpoly.androi.com;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



import android.widget.ShareActionProvider;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

@SuppressLint("NewApi") public class NewsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
	       
		WebView  webMain = (WebView) findViewById(R.id.webMain);
	        
	        
					webMain.setWebViewClient(new MyBrowser());
					webMain.getSettings().setLoadsImagesAutomatically(true);
					webMain.getSettings().setJavaScriptEnabled(true);
					webMain.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
					webMain.loadUrl("http://www.poly.edu.vn/");
	}
			
		
	

	@SuppressLint("NewApi") @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.news, menu);
				MenuItem shareItem = menu.findItem(R.id.share_option);
				ShareActionProvider mSAP = (ShareActionProvider) shareItem
						.getActionProvider();
				Intent shareIntent = new Intent(Intent.ACTION_SEND);
				shareIntent.setType("text/plain");
				shareIntent.putExtra(Intent.EXTRA_SUBJECT, "FPOLY");
				shareIntent.putExtra(Intent.EXTRA_TEXT, "http://www.poly.edu.vn/" );
				mSAP.setShareIntent(shareIntent);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.share_option) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
		
		
	}

	

}
