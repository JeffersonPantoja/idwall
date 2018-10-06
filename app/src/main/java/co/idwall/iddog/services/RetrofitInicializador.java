package co.idwall.iddog.services;

import android.content.Context;

import co.idwall.iddog.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {

    private final Retrofit retrofit;

    public RetrofitInicializador(Context context){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.url_api_dog))
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public DogService getDogSerice() {
        return retrofit.create(DogService.class);
    }

}
