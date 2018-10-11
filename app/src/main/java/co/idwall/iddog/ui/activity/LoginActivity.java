package co.idwall.iddog.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import co.idwall.iddog.R;
import co.idwall.iddog.api.client.LoginProcessado;
import co.idwall.iddog.controllers.LoginController;
import co.idwall.iddog.util.ActivityUtil;
import co.idwall.iddog.util.DialogUtil;

import static co.idwall.iddog.Constantes.EMAIL;
import static co.idwall.iddog.Constantes.PREFERENCIAS_DO_USUARIO;
import static co.idwall.iddog.Constantes.TOKEN;

public class LoginActivity extends AppCompatActivity {

    private Button botaoEntrar;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);
        if(preferences.contains(TOKEN)){
            String token = preferences.getString(TOKEN, null);
            ActivityUtil.vaiParaOutraActivityComExtra(this, token, FeedActivity.class);
        }

        configuraBotaoEntrar();
    }

    private void configuraBotaoEntrar() {
        botaoEntrar = findViewById(R.id.login_entrar);
        botaoEntrar.setOnClickListener(entrar());
    }

    @NonNull
    private View.OnClickListener entrar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View botao) {
                ativarLoading();
                new LoginController().loginEntrar(recuperaEMapeaEmail(), processaLogin());
            }
        };
    }

    @NonNull
    private LoginProcessado processaLogin() {
        return new LoginProcessado() {
            @Override
            public void sucesso(String token) {
                paraLoading();
                ActivityUtil.vaiParaOutraActivityComExtra(LoginActivity.this, token, FeedActivity.class);
            }

            @Override
            public void falha(String mensagemErro) {
                paraLoading();
                new DialogUtil(LoginActivity.this).exibirMensagemErro(mensagemErro);
            }
        };
    }

    private Map<String, String> recuperaEMapeaEmail() {
        EditText email = findViewById(R.id.login_email);
        Map<String, String> emailMap = new HashMap<>();
        emailMap.put(EMAIL,email.getText().toString());
        return emailMap;
    }

    private void ativarLoading() {
        botaoEntrar.setVisibility(View.GONE);
        loading = findViewById(R.id.login_progresbar);
        loading.setVisibility(View.VISIBLE);
    }

    public void paraLoading(){
        botaoEntrar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }




}
