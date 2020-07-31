package services;

import model.Usuario;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SessionService {

    @POST("session")
    Call<Usuario> requestJson(@Body RequestBody object);

    @GET("usuario")
    Call<Usuario> getAllUsers();


}
