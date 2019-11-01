package co.kr.itforone.shiningtour;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.support.design.widget.NavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.GravityCompat;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.text.Html;
        import android.text.method.LinkMovementMethod;
        import android.util.Base64;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.MenuItem;
        import android.view.View;
        import android.webkit.WebView;
        import android.widget.FrameLayout;
        import android.widget.TableRow;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.toolbox.Volley;
        import com.bumptech.glide.Glide;

        import org.json.JSONObject;

        import java.io.ByteArrayOutputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;

        import Fragment.*;
        import butterknife.BindView;
        import butterknife.ButterKnife;
        import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Activity 제어
    private ActivityManager am = ActivityManager.getInstance();
    private long backPrssedTime = 0;
    SharedPreferences pref_login;
    String profile_img,mb_id,mb_name,mb_2,notice_login,insta_id,popup_subject,popup_content,popup_link;
    @BindView(R.id.drawer_layout)    DrawerLayout drawer;
    @BindView(R.id.content)         FrameLayout frm_content;
    @BindView(R.id.txt_name)    TextView txt_name;
    @BindView(R.id.img_profile) CircleImageView img_profile;
    @BindView(R.id.private_policy)    TextView private_policy;
    @BindView(R.id.service_policy)    TextView service_policy;
    @BindView(R.id.side_coupon)    FrameLayout side_coupon;
    @BindView(R.id.side_favorite)    FrameLayout side_favorite;
    @BindView(R.id.side_login)    FrameLayout side_login;
    @BindView(R.id.side_profile)    FrameLayout side_profile;
    @BindView(R.id.side_register)    FrameLayout side_register;
    @BindView(R.id.side_schedule)    FrameLayout side_schedule;
    @BindView(R.id.txt_guide)    TextView txt_guide;
    @BindView(R.id.txt_introcompany)    TextView txt_introcompany;
    @BindView(R.id.txt_logout)    TextView txt_logout;
    @BindView(R.id.txt_modprofile)    TextView txt_modprofile;
    @BindView(R.id.txtside_coupon)    TextView txtside_coupon;
    @BindView(R.id.txtside_favorite)    TextView txtside_favorite;
    @BindView(R.id.txtside_login)    TextView txtside_login;
    @BindView(R.id.txtside_profile)    TextView txtside_profile;
    @BindView(R.id.txtside_qna)    TextView txtside_qna;
    @BindView(R.id.txtside_register)    TextView txtside_register;
    @BindView(R.id.txtside_schedule)    TextView txtside_schedule;
    @BindView(R.id.txt_insta)    TextView txt_insta;
    @BindView(R.id.copyright)    TextView copyright;


    Bundle bundle = new Bundle();
    Fragment frg = new frag_board();
    String lang_flg ="";
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        am.addActivity(this);
        ButterKnife.bind(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //kor , eng receive
        Intent i = getIntent();
        lang_flg = i.getStringExtra("lang_flg");
        if(lang_flg.equals("eng")){
        txt_logout.setText("logout");
        txt_introcompany.setText("about");
        private_policy.setText("Private_Policy");
        service_policy.setText("Term of Service");
        txt_modprofile.setText("Porofile");
        txt_name.setText("login");
        txtside_login.setText("login");
        txtside_register.setText("SignUp");
        txtside_profile.setText("Profile");
        txtside_favorite.setText("FavoriteLIst");
        txtside_coupon.setText("Coupon");
        txtside_schedule.setText("Schedule");
        txtside_qna.setText("Qna");
        txt_guide.setText("Reserve & Consulting (9:00 ~ 21:00)");
        copyright.setText("Copyright@ShiningTour All rights reserved.");
        notice_login = "Please login";
        txt_insta.setText("gobrunei_bn");
        insta_id = "gobrunei_bn";
    }

    else
            {
            txt_logout.setText("로그아웃");
            txt_introcompany.setText("회사소개");
            private_policy.setText("개인정보취급방침");
            service_policy.setText("서비스이용약관");
            txt_modprofile.setText("프로필 수정");
            txt_name.setText("로그인");
            txt_insta.setText("gobrunei_insta");
            txtside_login.setText("로그인");
            txtside_register.setText("회원가입");
            txtside_profile.setText("프로필 및 수정");
            txtside_favorite.setText("관심페이지 보관함");
            txtside_coupon.setText("쿠폰함");
            txtside_schedule.setText("일정목록");
            txtside_qna.setText("자주묻는질문");
            txt_guide.setText("예약 및 상담 문의 (09:00 ~ 21:00)");
            copyright.setText("Copyright@샤이닝투어 All rights reserved.");
             notice_login = "로그인을 해야합니다.";
             insta_id = "gobrunei_insta";

            }

        pref_login = getSharedPreferences("pref_profile", MODE_PRIVATE);
        mb_id = pref_login.getString("mb_id", "");
        mb_name = pref_login.getString("mb_name", "");
        mb_2 = pref_login.getString("mb_2", "");

        if(!mb_name.isEmpty()) {
            txt_name.setText(mb_name);
            txt_logout.setVisibility(View.VISIBLE);
            side_coupon.setVisibility(View.VISIBLE);
            side_favorite.setVisibility(View.VISIBLE);
            side_schedule.setVisibility(View.VISIBLE);
            side_profile.setVisibility(View.VISIBLE);
            side_login.setVisibility(View.GONE);
            side_register.setVisibility(View.GONE);
            txt_modprofile.setVisibility(View.GONE);
        }
        if(!mb_2.equals("")) {
            Glide.with(this).asBitmap().load("http://shiningtour.itforone.co.kr/data/proFile/" + mb_2).into(img_profile);
        }

        SharedPreferences pref_lang = getSharedPreferences("pref_lang", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref_lang.edit();
        editor.putString("lang", lang_flg);
        editor.commit();

        Fragment frag_01 = new frag_select();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, frag_01).commit();


        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        popup_subject =  jsonResponse.getString("nw_subject");
                        popup_content =  jsonResponse.getString("nw_content");
                        popup_link =  jsonResponse.getString("nw_link");

                        if(!popup_subject.equals(null)) {
                            WebView webView = new WebView(MainActivity.this);
                            webView.loadData(popup_content, "text/html", "utf-8");
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder .setView(webView)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                         /*   if(popup_link.contains("shiningtour.itforone")) {
                                                bundle.putString("seleted_menu", popup_link);
                                                frg.setArguments(bundle);
                                                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                                            }*/
                                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(popup_link));
                                                startActivity(intent);
                                        }
                                    })
                                    .show();
                        }
                        else{}
                      /*  if(!popup_subject.equals(null)){
                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                    .setTitle(popup_subject)
                                    .setMessage(Html.fromHtml(popup_content))
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            bundle.putString("seleted_menu", popup_link);
                                            frg.setArguments(bundle);
                                            getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                                        }
                                    })
                                    .show();
                        }
                        else{}*/
                        //Toast.makeText(getApplicationContext(),popup_subject, Toast.LENGTH_LONG).show();
                         //jsonResponse.getString("nw_end_time");
                    }
                    else{
                        //Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        Getpopupinfo getpopupinfo = new Getpopupinfo(responseListener);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(getpopupinfo);
    }


    // drawer 창 제어
    public void open_drawer(View view) {drawer.openDrawer(GravityCompat.END);}
    public void close_drawer(View view) {drawer.closeDrawer(GravityCompat.END);}

    //화살표버튼 제어
    public void go_intro(View view){finish();}
    public void back_main(View view) {getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_select()).commit();};

    public void intent_insta(View view){
        Uri uri = Uri.parse("http://instagram.com/_u/"+ insta_id);
        String apppackage = "com.instagram.android";
        Intent i= new Intent(Intent.ACTION_VIEW,uri);
        i.setPackage(apppackage);

        try {
            startActivity(i);
        } catch (Exception  e) {
            Toast.makeText(this, "Sorry, Instagram Apps Not Found", Toast.LENGTH_LONG).show();
        }
    }

    public void intent_homepage(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tourclick.co.kr"));
        startActivity(intent);
    }

    public void intent_kakao(View view){
   /*     String apppackage = "com.kakao.talk";
        Uri uri = Uri.parse("kakaoplus://plusfriend/friend/@codibook");

        Intent i= new Intent(Intent.ACTION_VIEW,uri);
        i.setPackage(apppackage);

        try {
            startActivity(i);
        } catch (Exception  e) {
            Toast.makeText(this, "Sorry, KaKaoTalk Apps Not Found", Toast.LENGTH_LONG).show();
        }*/

        if(lang_flg.equals("kor")){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_uQxexjT"));
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pf.kakao.com/_rtxoZT"));
            startActivity(intent);
        }

    }

    public void intent_call(View view)
    {
        String number ="";
        if (lang_flg.equals("kor")) {
            number = "tel:02-739-9916";
        } else {
            number = "tel:673-2610673";
        }
        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(number)));
    }

    public void load_img(View view)
    {
        if (!txt_name.getText().toString().equals("로그인"))
        {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, 100);
            return;
        }
        Toast.makeText(getApplicationContext(), "로그인을 해야 합니다.", Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            try
            {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                Glide.with(this).asBitmap().load(bitmap).into(img_profile);
                txt_modprofile.setVisibility(View.GONE);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
                txt_modprofile.setVisibility(View.VISIBLE);
                ByteArrayOutputStream  output = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
                profile_img  = Base64.encodeToString(output.toByteArray(), Base64.DEFAULT);
                String filename = mb_id + String.valueOf(System.currentTimeMillis());
                SharedPreferences pref = getSharedPreferences("pref_profile",MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("mb_2",filename);
                editor.commit();
               Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                uploadProfile uploadprofile = new uploadProfile(mb_id, profile_img, filename, responseListener);
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(uploadprofile);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public void logout(View paramView)
    {
        SharedPreferences.Editor editor = pref_login.edit();
        editor.clear();
        editor.commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_logout()).commit();
        finish();
        startActivity(getIntent());
    }

    //fragment viewChange & input data
    public void go_1(View view) {
        if(lang_flg.equals("kor")) {
            switch (view.getId()) {
                case R.id.bru_activity:
                    bundle.putString("seleted_menu", getString(R.string.board_tour));
                    frg.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                    break;
                case R.id.bru_foods:
                    bundle.putString("seleted_menu", getString(R.string.board_tour2));
                    frg.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                    break;
                case R.id.bru_hotel:
                    bundle.putString("seleted_menu", getString(R.string.board_tour4));
                    frg.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                    break;
                case R.id.bru_place:
                    bundle.putString("seleted_menu", getString(R.string.board_tour5));
                    frg.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                    break;
                case R.id.bru_shoping:
                    bundle.putString("seleted_menu", getString(R.string.board_tour3));
                    frg.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                    break;
                case R.id.bru_trafic:
                    bundle.putString("seleted_menu", getString(R.string.board_tour7));
                    frg.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                    break;
                case R.id.bru_study:
                    bundle.putString("seleted_menu", getString(R.string.board_tour8));
                    frg.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                    break;
                case R.id.bru_extra:
                    bundle.putString("seleted_menu", getString(R.string.board_tour6));
                    frg.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                    break;
                case R.id.side_login:
                    drawer.closeDrawer(Gravity.END);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_login()).commit();
                    break;
                case R.id.side_register:
                    drawer.closeDrawer(Gravity.END);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_register()).commit();
                    break;
                case R.id.side_profile:
                    if(mb_id.equals("")){
                        Toast.makeText(getApplicationContext(),notice_login,Toast.LENGTH_LONG).show();
                        break;
                    }
                    drawer.closeDrawer(Gravity.END);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_profile()).commit();
                    break;
                case R.id.side_favorite:
                    if(mb_id.equals("")){
                        Toast.makeText(getApplicationContext(),notice_login,Toast.LENGTH_LONG).show();
                        break;
                    }
                    drawer.closeDrawer(Gravity.END);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_favorite()).commit();
                    break;
                case R.id.side_coupon:
                    if(mb_id.equals("")){
                        Toast.makeText(getApplicationContext(),notice_login,Toast.LENGTH_LONG).show();
                        break;
                    }
                    drawer.closeDrawer(Gravity.END);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_coupon()).commit();
                    break;
                case R.id.side_schedule:
                    if(mb_id.equals("")){
                        Toast.makeText(getApplicationContext(),notice_login,Toast.LENGTH_LONG).show();
                        break;
                    }
                    drawer.closeDrawer(Gravity.END);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_schedule()).commit();
                    break;
                case R.id.side_qna:
                    drawer.closeDrawer(Gravity.END);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_qna()).commit();
                    break;
            }
        }
        else {
            switch (view.getId()) {
            case R.id.bru_activity:
                bundle.putString("seleted_menu", getString(R.string.board_tour_eng));
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case R.id.bru_foods:
                bundle.putString("seleted_menu", getString(R.string.board_tour2_eng));
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case R.id.bru_hotel:
                bundle.putString("seleted_menu", getString(R.string.board_tour4_eng));
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case R.id.bru_place:
                bundle.putString("seleted_menu", getString(R.string.board_tour5_eng));
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case R.id.bru_shoping:
                bundle.putString("seleted_menu", getString(R.string.board_tour3_eng));
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case R.id.bru_trafic:
                bundle.putString("seleted_menu", getString(R.string.board_tour7_eng));
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case R.id.bru_study:
                bundle.putString("seleted_menu", getString(R.string.board_tour8_eng));
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case R.id.bru_extra:
                bundle.putString("seleted_menu", getString(R.string.board_tour6_eng));
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case R.id.side_login:
                drawer.closeDrawer(Gravity.END);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_login()).commit();
                break;
            case R.id.side_register:
                drawer.closeDrawer(Gravity.END);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_register()).commit();
                break;
            case R.id.side_profile:
                if(mb_id.equals("")){
                    Toast.makeText(getApplicationContext(),notice_login,Toast.LENGTH_LONG).show();
                    break;
                }
                drawer.closeDrawer(Gravity.END);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_profile()).commit();
                break;
            case R.id.side_favorite:
                if(mb_id.equals("")){
                    Toast.makeText(getApplicationContext(),notice_login,Toast.LENGTH_LONG).show();
                    break;
                }
                drawer.closeDrawer(Gravity.END);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_favorite()).commit();
                break;
            case R.id.side_coupon:
                if(mb_id.equals("")){
                    Toast.makeText(getApplicationContext(),notice_login,Toast.LENGTH_LONG).show();
                    break;
                }
                drawer.closeDrawer(Gravity.END);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_coupon()).commit();
                break;
            case R.id.side_schedule:
                if(mb_id.equals("")){
                    Toast.makeText(getApplicationContext(),notice_login,Toast.LENGTH_LONG).show();
                    break;
                }
                drawer.closeDrawer(Gravity.END);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_schedule()).commit();
                break;
            case R.id.side_qna:
                drawer.closeDrawer(Gravity.END);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_qna()).commit();
                break;
        }
        }
    }
    public void go_2(View view) {
        /*switch (view.getId()) {
            case  R.id.nav_home :
                break;

            case  R.id.nav_favorite :  bundle.putString("seleted_menu", "http://shiningtour.itforone.co.kr/bbs/mybookmark.php");
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
                break;
            case  R.id.nav_faq :     bundle.putString("seleted_menu", "http://shiningtour.itforone.co.kr/bbs/board.php?bo_table=faq");
                frg.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
        }
*/
    }

    @Override
    public void onBackPressed() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {

            if (fragment.isVisible()) {
                if ((fragment instanceof frag_select) && (!drawer.isDrawerOpen(Gravity.END)))
                {
                    startActivity(new Intent(this, introActivity.class));
                }
                else if (drawer.isDrawerOpen(Gravity.END))
                {
                    drawer.closeDrawer(Gravity.END);
                }
                else
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, new frag_select()).commit();
                    drawer.closeDrawer(Gravity.END);
                }
            }
        }
    }
    //옵션메뉴 제어(사용하지않음)
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    //drawer 메뉴 버튼 제어
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
//        if (id == R.id.nav_home) {
//        } else if (id == R.id.nav_favorite) { bundle.putString("seleted_menu", "http://shiningtour.itforone.co.kr/bbs/mybookmark.php");
//            frg.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_news) {
//
//        } else if (id == R.id.nav_faq) {
//            bundle.putString("seleted_menu", "http://shiningtour.itforone.co.kr/bbs/board.php?bo_table=faq");
//            frg.setArguments(bundle);
//            getSupportFragmentManager().beginTransaction().replace(R.id.content, frg).commit();
//        }
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }
}
