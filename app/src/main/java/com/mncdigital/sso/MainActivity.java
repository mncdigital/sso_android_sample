package com.mncdigital.sso;

import android.os.*;
import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;
import android.util.*;
import android.net.*;
import android.net.http.*;

import android.webkit.*;
import android.app.*;
import android.view.ViewGroup.*;
import android.graphics.*;
import android.content.DialogInterface.*;

public class MainActivity extends AppCompatActivity
{
	public void dp(String s) { Log.d("JimBas", s); }

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Intent intent = getIntent();
		// if(intent != null) {
		// 	String saction = intent.getAction();
		// 	if(saction.equals("com.mncdigital.sso")) {
		// 		Bundle bundle = intent.getExtras();
		// 		if(bundle != null) {
		// 			((TextView)findViewById(R.id.tvuuid)).setText("uuid:\n" + bundle.getString("uuid"));
		// 			((TextView)findViewById(R.id.tvusername)).setText("username:\n" + bundle.getString("username"));
		// 			((TextView)findViewById(R.id.tvstatus)).setText("status:\n" + bundle.getString("status"));
		// 			((TextView)findViewById(R.id.tvtoken)).setText("token:\n" + bundle.getString("token"));
		// 		}
		// 	}
		// }
		init("profile");
		new LoadUrlInUiModeTask().execute(new String[]{"https://account.mncdigital.id"});
	}

	// private void launchBrowser(String smode) {
 //    	String ck = "QiGJIvlP9noC4fhx";
 //    	String r = "com.mncdigital.sso";
 //    	String pf = "android";
 //        String surl = "https://account.mncdigital.id/"+smode+"?ck="+ck + "&r="+r +"&pf="+pf;
 //        boolean bsuccess = false;
 //        String[] browsers = {
 //        	"com.android.browser",
 //        	"com.android.chrome",
 //        	"com.UCMobile.intl",
 //        	"org.mozilla.firefox",
 //        };
	// 	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(surl));
	// 	int i=-1,j=browsers.length;
	// 	while(!bsuccess) {
	// 		if(++i == j) {
	// 			intent.setPackage(null);
 //            	startActivity(intent);
	// 			break;
	// 		}
	// 		intent.setPackage(browsers[i]);
	// 		bsuccess = true;
	// 		try {
	// 			startActivity(intent);
	// 		} catch(ActivityNotFoundException e) {
	// 			bsuccess = false;
	// 		}
	// 	}
	// }
	public void onClickLogin(View view) {
		// launchBrowser("login");
	}
	public void onClickProfile(View view) {
		// launchBrowser("profile");
	}
	public void onClickLogout(View view) {
		// launchBrowser("logout");
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	private Context mContext = null;
	private WebView mWebview = null;
	private WebView mWebviewPop = null;
	private RelativeLayout mMainLayout = null;
	private Dialog mHtmlDlg = null;

	@SuppressWarnings("deprecation")
	private void init(String s)
	{
		mContext = MainActivity.this;
		// our main layout
        mMainLayout = new RelativeLayout(mContext);
		// GradientDrawable rlshape =  new GradientDrawable();
		// rlshape.setCornerRadius(13);
		// //rlshape.setStroke(2, Color.BLUE);
		// setBkgndCompat(mMainLayout, rlshape);

		// prepare webview
	    mWebview = new WebView(mContext);
		RelativeLayout.LayoutParams wvlp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mWebview.setLayoutParams(wvlp);
		WebSettings webSettings = mWebview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setAppCacheEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setSupportMultipleWindows(true);
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
    	webSettings.setUseWideViewPort(true);
    	webSettings.setLoadWithOverviewMode(true);
    	webSettings.setUserAgentString("Chrome/56.0.0.0 Mobile");
		mWebview.setInitialScale(1);
		mWebview.setWebViewClient(new UriWebViewClient());
		mWebview.setWebChromeClient(new UriChromeClient());
		mWebview.addJavascriptInterface(mobjIGamespark, "IGamespark");
		mWebview.clearView();
		mMainLayout.addView(mWebview);

		// setup cookie
		CookieManager cookieManager = CookieManager.getInstance(); 
		cookieManager.setAcceptCookie(true);
		// di disable karena tiba2 crash dan exit!

		// prepare the dialog
		mHtmlDlg = new Dialog(mContext);
		mHtmlDlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mHtmlDlg.setCancelable(true);
		mHtmlDlg.setOnDismissListener(new OnDismissListener() { @Override public void onDismiss(DialogInterface dialog) { 
			// dp("gamespark dialog dismissed");
			// if(mbProfileShown) {
			// 	initAssistive();
			// 	mbProfileShown = false;
			// }
			// dp("msUserId: " + msUserId);
			// if(mnReturnStatus == -1 && nTag == GPEXEC_LOGIN && (msUserId == null || msUserId.equals(""))) {
			// 	Toast.makeText(mContext, "Gamespark Login User Canceled!", Toast.LENGTH_LONG).show();
			// 	mnReturnStatus = GPRET_LOGIN_CANCELED;
			// 	msReturnMessage = "user canceled";
			// 	new RunOnUiModeTask().execute(new String[]{"onReturnToDev"});
			// }
		}});
		mHtmlDlg.setOnKeyListener(new Dialog.OnKeyListener() {
		  @Override public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
			if(keyCode == KeyEvent.KEYCODE_BACK) {
				//dp("backButtonPressed");
				// if(mbFacebookLogin && mWebviewPop != null) {
				// 	removeWebviewPop(null);
				// 	mWebview.reload();
				// 	mbFacebookLogin = false;
				// 	return true;
				// }
				// if(mWebview != null && mWebview.canGoBack()) {
				// 	mWebview.goBack();
				// 	return true;
				// }
				// mnReturnStatus = GPRET_LOGIN_CANCELED;
				// msReturnMessage = "User canceled";
				// onReturnToDev(null);
				return true;
			}
			return false;
		}});
		RelativeLayout.LayoutParams dlglp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		dlglp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		dlglp.addRule(RelativeLayout.CENTER_VERTICAL);
	    mHtmlDlg.setContentView(mMainLayout, dlglp);
	    // this is to solve problem that page input box covered by keyboard on landscape screen
		mHtmlDlg.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    	// mbDlgShown = false;
    	// mbFacebookLogin = false;

		mHtmlDlg.show();//bgcolor=\"#E6E6FA\"
		String html = "<!DOCTYPE html><html><head><title>__"+s+"__</title></head><body><p align=\"center\"><img src=\"data:image/gif;base64,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\"></p><p align=\"center\">Loading Please Wait...</p>";
		mWebview.loadData(html ,"text/html", "UTF-8");

		// mnReturnStatus = -1;
	}

	private class UriWebViewClient extends WebViewClient
	{
		@Override public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			String host = Uri.parse(url).getHost();
			dp("shouldOverrideUrlLoading with host: " + host);// + " targetUrl: " + msTargetUrl);
			//if(host.equals(msTargetUrl)) {
			//	if(mWebviewPop != null) {
			//		dp("mWebviewPop removed");
			//		mWebviewPop.setVisibility(View.GONE);
			//		mMainLayout.removeView(mWebviewPop);
			//		mWebviewPop = null;
			//	}
			//	return false;
			//}
			//if(host.indexOf(mfsUrlFilter) != -1) {
				return false;
			//}
			// Otherwise, the link is not for a page on my site, so launch
			// another Activity that handles URLs
			//dp("starting intent activity");
			//Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			//mContext.startActivity(intent);
			//return true;
		}
		@Override public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
		{
			//dp("UriWebViewClient onReceivedSslError");
			//super.onReceivedSslError(view, handler, error);
		}
		@Override public void onPageStarted(WebView view, String url, Bitmap favicon)
		{
			if(url.indexOf("<title>__") != -1 && url.indexOf("__</title>") != -1) {
			} else {
				dp("UriWebViewClient onPageStarted URL: " + url);
			}
		}
		@Override public void onPageFinished(WebView view, String url)
		{
			boolean bprint = true;
			if(url.indexOf("facebook.com") != -1) {
				if(url.indexOf("login") != -1) {
					// mbFacebookLogin = true;
				}
			} else if(url.indexOf("<title>__login__</title>") != -1) {
				// new PostTask().execute(new String[]{
				// 	mfsInterfcServer,
				// 	null, // get, kayak browser gitu
				// 	"onReturnExecute"
				// });
				bprint = false;
			} else if(url.indexOf("<title>__profile__</title>") != -1) {
				// String t = msProfileUrl.replace("{product_id}", msProductId);
				// new LoadUrlCustomHeaderInUiModeTask().execute(new String[]{t.replace("{user_id}", msUserId)});
				// mbProfileShown = true;
				bprint = false;
			}
			if(bprint) {
				dp("UriWebViewClient onPageFinished URL: " + url);
			}
			//closeProgDialog();
			//showHtmlDialog();
		}
	}

	class UriChromeClient extends WebChromeClient
	{
		@SuppressWarnings("deprecation")
		public void setSavePassword(WebView wvp)
		{
			wvp.getSettings().setSavePassword(false);
		}

		@Override public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg)
		{
			mWebviewPop = new WebView(mContext);
			mWebviewPop.setVerticalScrollBarEnabled(false);
			mWebviewPop.setHorizontalScrollBarEnabled(false);
			mWebviewPop.setWebViewClient(new UriWebViewClient());
			mWebviewPop.getSettings().setJavaScriptEnabled(true);
			//mWebviewPop.getSettings().setSavePassword(false);
			setSavePassword(mWebviewPop);
			mWebviewPop.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			mMainLayout.addView(mWebviewPop);
			WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
			transport.setWebView(mWebviewPop);
			resultMsg.sendToTarget();
			// dp("mWebviewPop created");
			return true;
		}
		@Override public void onCloseWindow(WebView window)
		{
			// dp("onCloseWindow called");
		}
		@Override public void onProgressChanged(WebView view, int progress)
		{
			//dp("UriChromeClient'onProgressChanged: " + Integer.toString(progress));
			if(progress == 100) {
				//closeProgDialog();
				//showHtmlDialog();
			}
		}
		@Override public boolean onJsAlert(WebView view, String url, String message, JsResult result)
		{
			dp(String.format("UriChromeClient onJsAlert\nURL:%s\nmessage:%s\njsresult:%s", url, message, result.toString()));
			//return true;	// hati2..!!! ini ternyata bikin freeze webview
			return false;
		}
	}

	private Object mobjIGamespark = new Object() {
		@JavascriptInterface public void log(String s) {
			dp("IGamesparkJSLog: " + s);
		}
	};

	private class LoadUrlInUiModeTask extends AsyncTask<String, String, String> 
	{
        @Override protected void onPostExecute(String s) {
			dp("LoadUrlInUiModeTask with URL: " + s);
			//closeProgDialog();
			mWebview.loadUrl(s);
		} @Override protected String doInBackground(String... params){ return params[0]; }
	}
}
