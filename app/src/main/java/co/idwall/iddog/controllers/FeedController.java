package co.idwall.iddog.controllers;

import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;

import co.idwall.iddog.R;
import co.idwall.iddog.model.FeedCategoria;
import co.idwall.iddog.services.RetrofitInicializador;
import co.idwall.iddog.ui.fragment.FeedFragment;
import co.idwall.iddog.util.ActivityUtil;
import co.idwall.iddog.util.DialogUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static co.idwall.iddog.ui.Constantes.ERROR;
import static co.idwall.iddog.ui.Constantes.MESSAGE;

public class FeedController {

    private final Context context;
    private FeedFragment feedFragment;

    public FeedController(Context context, FeedFragment feedFragment) {
        this.context = context;
        this.feedFragment = feedFragment;
    }

    public void buscarFeed(String token, String categoria){
        Call<FeedCategoria> call = new RetrofitInicializador(context).getDogSerice().feed(token, categoria);
        call.enqueue(new Callback<FeedCategoria>() {
            @Override
            public void onResponse(Call<FeedCategoria> call, Response<FeedCategoria> response) {
                trataRespostaApiDog(response);
            }

            @Override
            public void onFailure(Call<FeedCategoria> call, Throwable t) {
                DialogUtil.exibirMensagemErro(context,context.getResources().getString(R.string.controller_erro_transmissao));
                feedFragment.configuraRecycleView(new ArrayList<String>());
            }
        });
    }

    private void trataRespostaApiDog(Response<FeedCategoria> response) {
        FeedCategoria feedCategoria = response.body();
        if(feedCategoria != null){
            feedFragment.configuraRecycleView(feedCategoria.getListaUrlsDog());
        }else{
            trataRespostaErro(response);
            feedFragment.configuraRecycleView(new ArrayList<String>());
        }
    }

    private void trataRespostaErro(Response<FeedCategoria> response) {
        try {
            JSONObject json = new JSONObject(response.errorBody().string());
            JSONObject jsonErro = json.getJSONObject(ERROR);
            ActivityUtil.negaAcesso(context, jsonErro.getString(MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
            DialogUtil.exibirMensagemErro(context, context.getResources().getString(R.string.controller_erro_transmissao));
        }
        feedFragment.configuraRecycleView(new ArrayList<String>());
    }

}
