package co.idwall.iddog.services;

import java.util.Map;

import co.idwall.iddog.dto.Signup;
import co.idwall.iddog.model.Usuario;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface DogService {

//    @GET("search/repositories?q=language:Java&sort=stars")
//    Call<RepositorioSinc> listaRepositorios(@Query("page") int pagina);
//
//    @GET("repos/{usuario}/{repositorio}/pulls")
//    Call<List<PullRequest>>  listaPullRequest(@Path("usuario") String usuario, @Path("repositorio") String repositorio);


    @Headers("Content-Type: application/json")
    @POST("Signup")
    Call<Signup> signup(@QueryMap Map<String, String> email);
}
