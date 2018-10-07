package co.idwall.iddog.services;

import java.util.Map;

import co.idwall.iddog.dto.Signup;
import co.idwall.iddog.model.FeedCategoria;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DogService {

    @Headers("Content-Type: application/json")
    @POST("Signup")
    Call<Signup> signup(@QueryMap Map<String, String> email);

    @Headers("Content-Type: application/json")
    @GET("feed")
    Call<FeedCategoria> feed(@Header("Authorization") String token, @Query("category") String categoria);
}
