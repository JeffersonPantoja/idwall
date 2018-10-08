package co.idwall.iddog.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.idwall.iddog.R;
import co.idwall.iddog.ui.util.PreferencesUtil;

import static co.idwall.iddog.ui.Constantes.JA_ABRIU_APP;
import static co.idwall.iddog.ui.Constantes.PREFERENCIAS_DO_USUARIO;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences preferences = getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);

        if (preferences.contains(JA_ABRIU_APP))
            vaiParaLogin();
        else
            exibeSplashScreen();

    }

    private void exibeSplashScreen() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PreferencesUtil.salvaPreferenciaJaAbriu(SplashScreenActivity.this, true);
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
