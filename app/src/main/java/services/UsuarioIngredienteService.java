package services;

import java.util.List;

import model.Ingrediente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioIngredienteService {

    @GET("usuario/{id}")
    Call<Ingrediente> select(@Path("id") int id);

    @GET("usuario")
    Call<List<Ingrediente>> select();

    @POST("/usuario/ingredientes")
    Call<Ingrediente> cadastrarIngrediente(@Body Ingrediente ingrediente);

    @PUT("/usuario/{id}")
    Call<Ingrediente> atualizarIngrediente(@Path("id") int id);

}
