package co.idwall.iddog.controllers;

import android.content.Context;

import org.json.JSONObject;

import java.util.Map;

import co.idwall.iddog.R;
import co.idwall.iddog.dto.Signup;
import co.idwall.iddog.services.RetrofitInicializador;
import co.idwall.iddog.ui.activity.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.idwall.iddog.ui.Constantes.ERROR;
import static co.idwall.iddog.ui.Constantes.MESSAGE;

public class LoginController {

    private Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    public void loginEntrar(Map<String, String> email){

        Call<Signup> call = new RetrofitInicializador(context).getDogSerice().signup(email);
        call.enqueue(new Callback<Signup>() {
            @Override
            public void onResponse(Call<Signup> call, Response<Signup> response) {
                trataRespostaApiDog(response);
            }

            @Override
            public void onFailure(Call<Signup> call, Throwable t) {
                LoginActivity loginActivity = (LoginActivity) context;
                loginActivity.paraLoading();

            }
        });
    }

    private void trataRespostaApiDog(Response<Signup> response) {
        Signup signup = response.body();
        LoginActivity loginActivity = (LoginActivity) context;
        loginActivity.paraLoading();
        if(signup != null){
            loginActivity.vaiParaFeed(signup.getUsuario().getToken());
        }else{
            trataRespostaErro(response);
        }
    }

    private void trataRespostaErro(Response<Signup> response) {
        try {
            JSONObject json = new JSONObject(response.errorBody().string());
            JSONObject jsonErro = json.getJSONObject(ERROR);
            LoginActivity loginActivity = (LoginActivity) context;
            loginActivity.exibirMensagemErro(jsonErro.getString(MESSAGE));

        } catch (Exception e) {
            e.printStackTrace();
            LoginActivity loginActivity = (LoginActivity) context;
            loginActivity.exibirMensagemErro(context.getResources().getString(R.string.controller_erro_transmissao));
        }
    }
}
