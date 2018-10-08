package co.idwall.iddog.controllers;

import android.content.Context;
import android.widget.Toast;

import co.idwall.iddog.model.FeedCategoria;
import co.idwall.iddog.services.RetrofitInicializador;
import co.idwall.iddog.ui.fragment.FeedFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedController {

    private final Context context;
    private FeedFragment feed;

    public FeedController(Context context, FeedFragment feed) {
        this.context = context;
        this.feed = feed;
    }

    public void buscarFeed(String token, String categoria){


        Call<FeedCategoria> call = new RetrofitInicializador(context).getDogSerice().feed(token, categoria);

        call.enqueue(new Callback<FeedCategoria>() {
            @Override
            public void onResponse(Call<FeedCategoria> call, Response<FeedCategoria> response) {
                FeedCategoria feedCategoria = response.body();
                feed.configuraRecycleView(feedCategoria);
            }

            @Override
            public void onFailure(Call<FeedCategoria> call, Throwable t) {
                Toast.makeText(context, "falha", Toast.LENGTH_LONG).show();
            }
        });
    }


}
