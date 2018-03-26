package com.mncdigital.sso;

import android.os.*;
import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.util.*;
import android.net.*;
import android.net.http.*;

public class MainActivity extends AppCompatActivity
{
	public void dp(String s) { Log.d("JimBas", s); }

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Intent intent = getIntent();
		if(intent != null) {
			String saction = intent.getAction();
			if(saction.equals("com.mncdigital.sso")) {
				Bundle bundle = intent.getExtras();
				if(bundle != null) {
					((TextView)findViewById(R.id.tvuuid)).setText("uuid:\n" + bundle.getString("uuid"));
					((TextView)findViewById(R.id.tvusername)).setText("username:\n" + bundle.getString("username"));
					((TextView)findViewById(R.id.tvstatus)).setText("status:\n" + bundle.getString("status"));
					((TextView)findViewById(R.id.tvtoken)).setText("token:\n" + bundle.getString("token"));
				}
			}
		}
	}

	private void launchBrowser(String smode) {
    	String ck = "QiGJIvlP9noC4fhx";
    	String r = "com.mncdigital.sso";
    	String pf = "android";
        String surl = "https://account.mncdigital.id/"+smode+"?ck="+ck + "&r="+r +"&pf="+pf;
        boolean bsuccess = false;
        String[] browsers = {
        	"com.android.browser",
        	"com.android.chrome",
        	"com.UCMobile.intl",
        	"org.mozilla.firefox",
        };
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(surl));
		int i=-1,j=browsers.length;
		while(!bsuccess) {
			if(++i == j) {
				intent.setPackage(null);
            	startActivity(intent);
				break;
			}
			intent.setPackage(browsers[i]);
			bsuccess = true;
			try {
				startActivity(intent);
			} catch(ActivityNotFoundException e) {
				bsuccess = false;
			}
		}
	}
	public void onClickLogin(View view) {
		launchBrowser("login");
	}
	public void onClickProfile(View view) {
		launchBrowser("profile");
	}
	public void onClickLogout(View view) {
		launchBrowser("logout");
	}
}
