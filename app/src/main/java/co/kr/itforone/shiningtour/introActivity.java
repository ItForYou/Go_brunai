package co.kr.itforone.shiningtour;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

public class introActivity extends AppCompatActivity {
    private long backPrssedTime = 0;
    private ActivityManager am = ActivityManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        am.addActivity(this);
        ButterKnife.bind(this);

    }

    //lang_data mainActivity 전송
    public void go_main(View view){
        Intent i = new Intent(introActivity.this, MainActivity.class);
        switch (view.getId()) {

            case  R.id.intro_kor : i.putExtra("lang_flg", "kor");introActivity.this.startActivity(i); break;
            case  R.id.intro_eng : i.putExtra("lang_flg", "eng");introActivity.this.startActivity(i); break;
        }
    }



    @Override
    public void onBackPressed() {

        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPrssedTime;

        if (0 <= intervalTime && 2000 >= intervalTime) {
            am.finishAllActivity();
        }
        else
        {
            backPrssedTime = tempTime;
            Toast.makeText(getApplicationContext(), "한번 더 뒤로가기 누를시 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}

