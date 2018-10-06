package co.idwall.iddog.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.idwall.iddog.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                vaiParaLogin();
            }
        },2000);
    }

    private void vaiParaLogin() {
        Intent vaiParaLogin = new Intent(this, LoginActivity.class);
        startActivity(vaiParaLogin);
        finish();
    }
}
