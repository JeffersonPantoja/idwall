package co.idwall.iddog.controllers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import co.idwall.iddog.dto.Signup;
import co.idwall.iddog.model.Usuario;
import co.idwall.iddog.services.RetrofitInicializador;
import co.idwall.iddog.ui.activity.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {

    public static final String ERRO_TRANSMISSÃO = "Erro de transmissão";
    public static final String MENSAGEM_ERRO = "message";
    private LoginActivity loginActivity;

    public void loginEntrar(final Context context, Map<String, String> email){
        loginActivity = (LoginActivity) context;
        Call<Signup> call = new RetrofitInicializador(context).getDogSerice().signup(email);

        call.enqueue(new Callback<Signup>() {
            @Override
            public void onResponse(Call<Signup> call, Response<Signup> response) {
                trataRespostaApiDog(response);
            }

            @Override
            public void onFailure(Call<Signup> call, Throwable t) {
                loginActivity.paraLoading();

            }
        });
    }

    private void trataRespostaApiDog(Response<Signup> response) {
        Signup signup = response.body();
        loginActivity.paraLoading();
        if(signup != null){
            trataRespostaAceita(signup.getUsuario());
        }else{
            trataRespostaErro(response);
        }
    }

    private void trataRespostaAceita(Usuario usuario) {

    }

    private void trataRespostaErro(Response<Signup> response) {
        try {
            JSONObject json = new JSONObject(response.errorBody().string());
            JSONObject jsonErro = json.getJSONObject("error");
            loginActivity.exibirMensagemErro(jsonErro.getString("message"));

        } catch (Exception e) {
            e.printStackTrace();
            loginActivity.exibirMensagemErro(ERRO_TRANSMISSÃO);
        }
    }
}
