package co.kr.itforone.shiningtour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URISyntaxException;


public class ViewManager extends WebViewClient {
    Activity context;
    public ViewManager(Activity context){
        this.context = context;
    }
    public ViewManager(){
    }
/*
    @Override
    public void onPageFinished(WebView view, String url) {
        view.loadUrl("javascript:setToken('" + Common.TOKEN + "','" + Common.getMyDeviceId(this.main) + "')");
        Log.d("mb_id", Common.getPref(main, "ss_mb_id", ""));
    }
*/
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.startsWith("http://pf.")){
                String apppackage = "com.kakao.talk";
                Uri uri = Uri.parse("kakaoplus://plusfriend/friend/@codibook");

                Intent i= new Intent(Intent.ACTION_VIEW,uri);
                i.setPackage(apppackage);

                try {
                   context.startActivity(i);
                } catch (Exception  e) {
                    Toast.makeText(context.getApplicationContext(), "Sorry, KaKaoTalk Apps Not Found", Toast.LENGTH_LONG).show();
                }
            }
            else
            view.loadUrl(url);
            return true;
        }
    }

