package services;

import java.util.List;

import model.Receita;
import model.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import retrofit2.http.POST;
import retrofit2.http.Body;

public interface UsuarioService {

    @GET("usuario/{id}")
    Call<List<Usuario>> select(@Path("id") int login);

    @GET("usuario/receita/{id}")
    Call<List<Receita>> selectUserRecipes(@Path("id") int id);

    @POST("usuario")
    Call<Usuario> SignUp(@Body Usuario usuario);

    @POST("usuario")
    Call<Usuario> SignUp(@Body String string);



}
