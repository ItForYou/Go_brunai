package Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.util.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.kr.itforone.shiningtour.ChromeManager;
import co.kr.itforone.shiningtour.R;
import co.kr.itforone.shiningtour.ViewManager;

public class frag_board extends Fragment {
    private WebView webView;
    TextView txt_title;
    ImageView img_title;
    String category;
    DrawerLayout drawer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        //getActivity().getWindow().setSoftInputMode();
        final View view = inflater.inflate(R.layout.activity_webview,container,false);
        txt_title = view.findViewById(R.id.txt_title);
        img_title = view.findViewById(R.id.img_title);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if(args != null){
            category = args.getString("seleted_menu");
            int index_title = category.indexOf('=');
            String title = category.substring(index_title+1, category.length());
            switch (title){
                case "tour" : txt_title.setText("액티비티");
                                txt_title.setVisibility(View.VISIBLE);
                                img_title.setVisibility(View.GONE);
                                break;
                case "tour02" : txt_title.setText("음식점");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "tour03" : txt_title.setText("쇼핑");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "tour04" : txt_title.setText("호텔");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "tour05" : txt_title.setText("관광지");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "tour06" : txt_title.setText("뉴스&이벤트");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "tour07" : txt_title.setText("교통");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "tour08" : txt_title.setText("유학");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "eng_tour" : txt_title.setText("ACTIVITY");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "eng_tour02" : txt_title.setText("FOOD");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "eng_tour03" : txt_title.setText("SHOPPING");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "eng_tour04" : txt_title.setText("HOTEL");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "eng_tour05" : txt_title.setText("TOURIST SPOT");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "eng_tour06" : txt_title.setText("NEWS&EVENT");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "eng_tour07" : txt_title.setText("TRAFIC");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;
                case "eng_tour08" : txt_title.setText("STUDY ABROAD");
                    txt_title.setVisibility(View.VISIBLE);
                    img_title.setVisibility(View.GONE);
                    break;


            }


        }

        webView = (WebView)view.findViewById(R.id.webView);
        drawer = getActivity().findViewById(R.id.drawer_layout);
        // Fragment는 가끔 WebView가 중복되어 Error가 난다. 만일을 위해 WebView를 리셋한다.

        webView.setWebChromeClient(new ChromeManager());
        webView.setWebViewClient(new ViewManager(getActivity()));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        ///settings.setUserAgentString(settings.getUserAgentString()+"//Brunei");
        SharedPreferences pref = getActivity().getSharedPreferences("pref_profile", getActivity().MODE_PRIVATE);
        String mb_id = pref.getString("mb_id", "");
        String mb_pwd = pref.getString("mb_password", "");
        //Toast.makeText(getActivity().getApplicationContext(),mb_id +","+mb_pwd,Toast.LENGTH_LONG).show();

        SharedPreferences lang_pref = getActivity().getSharedPreferences("pref_lang", getActivity().MODE_PRIVATE);
        String lang = lang_pref.getString("lang", "");

        if(lang.equals("kor")) {
            String url = getString(R.string.login_chk);
            String postData = "mb_id="+mb_id+"&mb_password="+mb_pwd;
            webView.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));
        }

        else {
            String url = getString(R.string.login_eng_chk);
            String postData = "mb_id="+mb_id+"&mb_password="+mb_pwd;
            webView.postUrl(url, EncodingUtils.getBytes(postData, "BASE64"));
        }

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
