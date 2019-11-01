package Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class frag_logout extends Fragment {
    public WebView webView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        //getActivity().getWindow().setSoftInputMode();
        final View view = inflater.inflate(R.layout.activity_webview,container,false);
        ButterKnife.bind(this, view);


        webView = (WebView)view.findViewById(R.id.webView);

        webView.setWebChromeClient(new ChromeManager());
        webView.setWebViewClient(new ViewManager());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.loadUrl(getString(R.string.logout));
        // Inflate the layout for this fragment

        return view;
    }
}
