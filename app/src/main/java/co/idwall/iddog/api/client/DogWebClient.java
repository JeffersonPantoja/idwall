package co.idwall.iddog.api.client;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Map;

import co.idwall.iddog.api.RetrofitInicializador;
import co.idwall.iddog.api.services.DogService;
import co.idwall.iddog.dto.ErroResponse;
import co.idwall.iddog.dto.Signup;
import co.idwall.iddog.model.FeedCategoria;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

import static co.idwall.iddog.Constantes.ERRO_DE_TRANSMISSÃO;

public class DogWebClient {


    private final DogService service;

    public DogWebClient() {
        service = new RetrofitInicializador().getDogSerice();
    }

    public void buscarFeed(String token, String categoria, final FeedProcessado listener){
        Call<FeedCategoria> call = new RetrofitInicializador().getDogSerice().feed(token, categoria);
        call.enqueue(new Callback<FeedCategoria>() {
            @Override
            public void onResponse(Call<FeedCategoria> call, Response<FeedCategoria> response) {
                if(response.isSuccessful()){
                    listener.sucesso(response.body().getListaUrlsDog());
                }else{
                    try {
                        ErroResponse error = pegaErro(response.errorBody());
                        listener.falha(error.getErro().getMensagem());
                    } catch (IOException e) {
                        e.printStackTrace();
                        listener.falha(null);
                    }
                }
            }

            @Override
            public void onFailure(Call<FeedCategoria> call, Throwable t) {
                listener.falha(null);
            }
        });
    }

    public void loginEntrar(Map<String, String> email, final LoginProcessado listener){

        Call<Signup> call = new RetrofitInicializador().getDogSerice().signup(email);
        call.enqueue(new Callback<Signup>() {
            @Override
            public void onResponse(Call<Signup> call, Response<Signup> response) {
                if(response.isSuccessful()){
                    String token = response.body().getUsuario().getToken();
                    listener.sucesso(token);
                }else{
                    try {
                        ErroResponse error = pegaErro(response.errorBody());
                        listener.falha(error.getErro().getMensagem());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Signup> call, Throwable t) {
                listener.falha(ERRO_DE_TRANSMISSÃO);
            }
        });
    }

    private ErroResponse pegaErro(ResponseBody responseBody) throws IOException {
        Converter<ResponseBody, ErroResponse> errorConverter;
        errorConverter = new RetrofitInicializador().retrofit
                .responseBodyConverter(ErroResponse.class, new Annotation[0]);

        return errorConverter.convert(responseBody);
    }
}
