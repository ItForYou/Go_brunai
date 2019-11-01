package Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import butterknife.ButterKnife;
import co.kr.itforone.shiningtour.ChromeManager;
import co.kr.itforone.shiningtour.LoginRequest;
import co.kr.itforone.shiningtour.R;
import co.kr.itforone.shiningtour.ViewManager;

public class frag_register extends Fragment {
    public WebView webView;
    String category;
    DrawerLayout drawer;
    TextView txtName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        //getActivity().getWindow().setSoftInputMode();
        final View view = inflater.inflate(R.layout.activity_webview,container,false);

        ButterKnife.bind(this, view);

        webView = (WebView)view.findViewById(R.id.webView);
        drawer = getActivity().findViewById(R.id.drawer_layout);
        txtName = getActivity().findViewById(R.id.txt_name);
        // Fragment는 가끔 WebView가 중복되어 Error가 난다. 만일을 위해 WebView를 리셋한다.

        webView.setWebChromeClient(new ChromeManager());
        webView.setWebViewClient(new ViewManager());
        webView.addJavascriptInterface(new WebJavascriptEvent(), "Android");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        SharedPreferences pref = getActivity().getSharedPreferences("pref_lang", getActivity().MODE_PRIVATE);
        String flag_lang = pref.getString("lang", "");
        if(flag_lang.equals("kor"))
            category = getString(R.string.register);
        else
            category = getString(R.string.register_eng);

        webView.loadUrl(category);
        // Inflate the layout for this fragment

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (drawer.isDrawerOpen(Gravity.END))
                    {
                        drawer.closeDrawer(Gravity.END);
                        return true;
                    }
                    if (webView.canGoBack())
                    {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }


    public void setLogin(String id, final String password) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        txtName.setText(jsonResponse.getString("mb_name"));
                        SharedPreferences pref = getActivity().getSharedPreferences("pref_profile", getActivity().MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("mb_id", jsonResponse.getString("mb_id"));
                        editor.putString("mb_password", password);
                        editor.putString("mb_name", jsonResponse.getString("mb_name"));
                        editor.putString("mb_2", jsonResponse.getString("mb_2"));
                        editor.commit();
                        getActivity().finish();
                        startActivity(getActivity().getIntent());
                        return;
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(),"실패",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginrequest = new LoginRequest(id, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(loginrequest);
    }

    private class WebJavascriptEvent
    {
        @JavascriptInterface
        public void login(String id, String password)
        {
            setLogin(id, password);
        }
    }
}
