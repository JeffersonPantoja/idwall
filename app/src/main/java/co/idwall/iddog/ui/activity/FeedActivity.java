package co.idwall.iddog.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.idwall.iddog.R;
import co.idwall.iddog.ui.adapter.FeedPaginaAdapter;
import co.idwall.iddog.util.ActivityUtil;
import co.idwall.iddog.util.PreferencesUtil;

import static co.idwall.iddog.Constantes.TOKEN;

public class FeedActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        validaAcesso();
        configuraTabs();
    }

    private void validaAcesso() {
        Intent intent = getIntent();
        if(temToken(intent)){
            String token = intent.getStringExtra(TOKEN);
            PreferencesUtil.salvaToken(this, token);
        }else {
            ActivityUtil.vaiParaOutraActivity(this, LoginActivity.class);
        }
    }

    private boolean temToken(Intent intent) {
        return intent.hasExtra(TOKEN);
    }

    private void configuraTabs() {
        ViewPager viewPager = findViewById(R.id.feed_viewpage);
        viewPager.setAdapter(new FeedPaginaAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = findViewById(R.id.feed_tab);
        tabLayout.setupWithViewPager(viewPager);
    }

}
