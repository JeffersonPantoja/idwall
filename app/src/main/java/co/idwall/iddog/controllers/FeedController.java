package co.idwall.iddog.controllers;

import android.content.Context;
import android.widget.Toast;

import co.idwall.iddog.model.FeedCategoria;
import co.idwall.iddog.services.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedController {

    private final Context context;

    public FeedController(Context context) {
        this.context = context;
    }

    public void buscarFeed(String token, String categoria){


        Call<FeedCategoria> call = new RetrofitInicializador(context).getDogSerice().feed(token, categoria);

        call.enqueue(new Callback<FeedCategoria>() {
            @Override
            public void onResponse(Call<FeedCategoria> call, Response<FeedCategoria> response) {
                FeedCategoria feedCategoria = response.body();

            }

            @Override
            public void onFailure(Call<FeedCategoria> call, Throwable t) {
                Toast.makeText(context, "falha", Toast.LENGTH_LONG).show();
            }
        });
    }


}
