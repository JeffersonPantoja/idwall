package co.idwall.iddog.ui.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;

import co.idwall.iddog.R;
import co.idwall.iddog.controllers.LoginController;

public class LoginActivity extends AppCompatActivity {

    private Button botaoEntrar;
    private ProgressBar loading;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                new LoginController().loginEntrar(LoginActivity.this, recuperaEMapeaEmail());
            }
        };
    }

    private void ativarLoading() {
        botaoEntrar.setVisibility(View.GONE);
        loading = findViewById(R.id.login_progresbar);
        loading.setVisibility(View.VISIBLE);
    }

    private Map<String, String> recuperaEMapeaEmail() {
        EditText email = findViewById(R.id.login_email);
        Map<String, String> emailMap = new HashMap<>();
        emailMap.put("email",email.getText().toString());
        return emailMap;
    }

    public void paraLoading(){
        botaoEntrar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
    }

    public void exibirMensagemErro(String mensagemErro) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensagemErro)
                .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        dialog = builder.create();
        dialog.show();
    }
}
