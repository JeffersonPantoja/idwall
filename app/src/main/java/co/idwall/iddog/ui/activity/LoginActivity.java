package co.idwall.iddog.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import co.idwall.iddog.R;
import co.idwall.iddog.controllers.LoginController;

import static co.idwall.iddog.ui.Constantes.PREFERENCIAS_DO_USUARIO;
import static co.idwall.iddog.ui.Constantes.TOKEN;

public class LoginActivity extends AppCompatActivity {
    private Button botaoEntrar;
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences(PREFERENCIAS_DO_USUARIO, MODE_PRIVATE);
        if(preferences.contains(TOKEN)){
            vaiParaFeed(preferences.getString(TOKEN,null));
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
                new LoginController(LoginActivity.this).loginEntrar(recuperaEMapeaEmail());
            }
        };
    }

    private Map<String, String> recuperaEMapeaEmail() {
        EditText email = findViewById(R.id.login_email);
        Map<String, String> emailMap = new HashMap<>();
        emailMap.put("email",email.getText().toString());
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

    public void exibirMensagemErro(String mensagemErro) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensagemErro)
                .setPositiveButton(R.string.login_entendido, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void vaiParaFeed(String token) {
        Intent vaiParaFeed = new Intent(this, FeedActivity.class);
        vaiParaFeed.putExtra(TOKEN, token);
        startActivity(vaiParaFeed);
        finish();
    }
}
