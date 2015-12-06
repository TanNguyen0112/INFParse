package fpoly.androi.com;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

import com.parse.Parse;

public class MainActivity extends ActionBarActivity {
private Button btlogin, btreset;
private  EditText txtUser, txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		Parse.enableLocalDatastore(this);

		Parse.initialize(this, "a93SUP4GrIKXRf8Hr0Khow57hlaholpuLVpMDpIZ", "BfSj2rdGtdgi4ep3xinn7UzOatKgCCnhkrknLXKf");

		txtUser = (EditText)findViewById(R.id.edtuser);
        txtPass = (EditText)findViewById(R.id.edtpass);
        btlogin = (Button)findViewById(R.id.btnlogin);
        
        btreset = (Button)findViewById(R.id.btnreset);
        btlogin.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String User, Pass;
		    	User = txtUser.getText().toString();
		    	Pass = txtPass.getText().toString();
		    	
		    	if(User.toString().equals("user")){
		    		if(Pass.toString().equals("user")){
		    			Intent intent = new Intent(MainActivity.this, UserActivity.class);
		    	    	startActivity(intent);
		    		}
		    		else if(Pass.toString() !="user"){
		    			Toast.makeText(MainActivity.this, "Nhập Sai Pass", Toast.LENGTH_LONG).show();
		    		}
		    	}
		    	else if(User.toString().equals("admin")){
		    		if(Pass.toString().equals("admin")){
		    			Intent intent = new Intent(MainActivity.this, AdminActivity.class);
		    	    	startActivity(intent);
		    		}
		    		else if(Pass.toString() !="admin"){
		    			Toast.makeText(MainActivity.this, "Nhập Sai Pass", Toast.LENGTH_LONG).show();
		    		}
		    	}
		    	
		    	else if(User.toString().trim().equalsIgnoreCase("")){
		    		Toast.makeText(MainActivity.this, "Chưa Nhập User", Toast.LENGTH_LONG).show();
		    	}
		    	else if(Pass.toString().trim().equalsIgnoreCase("")){
		    		Toast.makeText(MainActivity.this, "Chưa Nhập Pass", Toast.LENGTH_LONG).show();
		    	}
		    	
		    	
		    	else{
		    		Toast.makeText(MainActivity.this, "  Nhập Sai User Hoặc Pass", Toast.LENGTH_LONG).show();
		    	}
			}
		});
btreset.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				txtUser.setText("");
				txtPass.setText("");
			}
});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
