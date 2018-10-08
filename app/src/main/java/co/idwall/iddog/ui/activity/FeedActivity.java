package co.idwall.iddog.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.idwall.iddog.R;
import co.idwall.iddog.ui.adapter.FeedPaginaAdapter;
import co.idwall.iddog.ui.util.DialogUtil;
import co.idwall.iddog.ui.util.PreferencesUtil;

import static co.idwall.iddog.ui.Constantes.TOKEN;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        validaToken();
        configuraTabs();
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

    private void configuraTabs() {
        ViewPager viewPager = findViewById(R.id.feed_viewpage);
        viewPager.setAdapter(new FeedPaginaAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.feed_tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void vaiParaLogin() {
        Intent vaiParaLogin = new Intent(this, LoginActivity.class);
        startActivity(vaiParaLogin);
        finish();
    }

    public void exibirMensagemErro(String mensagemErro) {
        DialogUtil.exibirMensagemErro(this, mensagemErro);
    }
}
