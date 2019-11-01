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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import butterknife.ButterKnife;
import co.kr.itforone.shiningtour.ChromeManager;
import co.kr.itforone.shiningtour.R;
import co.kr.itforone.shiningtour.ViewManager;

public class frag_coupon extends Fragment {
    public WebView webView;
    String category;
    DrawerLayout drawer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        //getActivity().getWindow().setSoftInputMode();
        final View view = inflater.inflate(R.layout.activity_webview,container,false);

        ButterKnife.bind(this, view);

        webView = (WebView)view.findViewById(R.id.webView);
        drawer = getActivity().findViewById(R.id.drawer_layout);
        webView.setWebChromeClient(new ChromeManager());
        webView.setWebViewClient(new ViewManager());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        SharedPreferences pref = getActivity().getSharedPreferences("pref_lang", getActivity().MODE_PRIVATE);
        String flag_lang = pref.getString("lang", "");
        if(flag_lang.equals("kor"))
            category = getString(R.string.coupon);
        else
            category = getString(R.string.coupon_eng);

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
}
