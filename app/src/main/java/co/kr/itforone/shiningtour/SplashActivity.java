package co.kr.itforone.shiningtour;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private long backPrssedTime = 0;
    @BindView(R.id.splash)
    ImageView img_splash;
    private ActivityManager am = ActivityManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Random random = new Random();
        int spls_flg = random.nextInt(4) + 1;
        switch (spls_flg) {
            case 1: img_splash.setImageResource(R.drawable.splash1);break;
            case 2: img_splash.setImageResource(R.drawable.splash2);break;
            case 3: img_splash.setImageResource(R.drawable.splash3);break;
            case 4: img_splash.setImageResource(R.drawable.splash4);break;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent introintent = new Intent(SplashActivity.this, introActivity.class);
                SplashActivity.this.startActivity(introintent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
