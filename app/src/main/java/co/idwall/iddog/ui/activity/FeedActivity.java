package co.idwall.iddog.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.idwall.iddog.R;
import co.idwall.iddog.util.PreferencesUtil;

import static co.idwall.iddog.ui.activity.ConstantesActivity.TOKEN;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        validaToken();


    }

    private void validaToken() {
        Intent intent = getIntent();
        if(intent.hasExtra(TOKEN)){
            String token = intent.getStringExtra(TOKEN);
            PreferencesUtil.salvaToken(this, token);
        }else {
            vaiParaLogin();
        }
    }

    private void vaiParaLogin() {
        Intent vaiParaLogin = new Intent(this, LoginActivity.class);
        startActivity(vaiParaLogin);
        finish();
    }
}
