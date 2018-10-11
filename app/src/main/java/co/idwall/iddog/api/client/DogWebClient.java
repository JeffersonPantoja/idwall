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

    private final RetrofitInicializador retrofitInicializador;

    public DogWebClient() {
        retrofitInicializador = new RetrofitInicializador();
    }

    public void loginEntrar(Map<String, String> email, final LoginProcessado listener){

        Call<Signup> call = retrofitInicializador.getDogSerice().signup(email);
        call.enqueue(new Callback<Signup>() {
            @Override
            public void onResponse(Call<Signup> call, Response<Signup> response) {

                switch (response.code()) {
                    case 200:
                        String token = response.body().getUsuario().getToken();
                        listener.sucesso(token);
                        break;
                    case 400:
                        try {
                            ErroResponse error = pegaErro(response.errorBody());
                            listener.falha(error.getErro().getMensagem());
                        } catch (IOException e) {
                            e.printStackTrace();
                            listener.falha(ERRO_DE_TRANSMISSÃO);
                        }
                        break;
                    default:
                        listener.falha(ERRO_DE_TRANSMISSÃO);
                }
            }

            @Override
            public void onFailure(Call<Signup> call, Throwable t) {
                listener.falha(ERRO_DE_TRANSMISSÃO);
            }
        });
    }

    public void buscaFeed(String token, String categoria, final FeedProcessado listener){
        Call<FeedCategoria> call = retrofitInicializador.getDogSerice().feed(token, categoria);
        call.enqueue(new Callback<FeedCategoria>() {
            @Override
            public void onResponse(Call<FeedCategoria> call, Response<FeedCategoria> response) {

                switch (response.code()){
                    case 200:
                        listener.sucesso(response.body().getListaUrlsDog());
                        break;
                    case 401:
                        try {
                            ErroResponse error = pegaErro(response.errorBody());
                            listener.falha(error.getErro().getMensagem());
                        } catch (IOException e) {
                            e.printStackTrace();
                            listener.falha(null);
                        }
                        break;
                    default:
                        listener.falha(null);
                }
            }

            @Override
            public void onFailure(Call<FeedCategoria> call, Throwable t) {
                listener.falha(null);
            }
        });
    }

    private ErroResponse pegaErro(ResponseBody responseBody) throws IOException {
        Converter<ResponseBody, ErroResponse> errorConverter;
        errorConverter = retrofitInicializador.getRetrofit()
                .responseBodyConverter(ErroResponse.class, new Annotation[0]);

        return errorConverter.convert(responseBody);
    }
}
