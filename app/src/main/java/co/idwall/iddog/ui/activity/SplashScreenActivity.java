package co.idwall.iddog.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import co.idwall.iddog.R;
import co.idwall.iddog.util.ActivityUtil;
import co.idwall.iddog.util.PreferencesUtil;

import static co.idwall.iddog.Constantes.JA_ABRIU_APP;
import static co.idwall.iddog.Constantes.PREFERENCIAS_DO_USUARIO;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences preferences = getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);

        if (preferences.contains(JA_ABRIU_APP))
            ActivityUtil.vaiParaOutraActivity(this, LoginActivity.class);
        else
            exibeSplashScreen();

    }

    private void exibeSplashScreen() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PreferencesUtil.salvaPreferenciaJaAbriu(SplashScreenActivity.this, true);
                ActivityUtil.vaiParaOutraActivity(SplashScreenActivity.this, LoginActivity.class);
            }
        },2000);
    }


}
