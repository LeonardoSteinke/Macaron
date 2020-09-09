package services;

import java.util.List;

import model.Ingrediente;
import model.Receita;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IngredienteReceitaService {

    @GET("receita/{id}")
    Call<Ingrediente> select(@Path("id") int id);

    @GET("receita")
    Call<List<Ingrediente>> select();

    @POST("/receita/ingredientes")
    Call<Ingrediente> cadastrarIngrediente(@Body Ingrediente ingrediente);

    @PUT("/receita/{id}")
    Call<Ingrediente> atualizarIngrediente(@Path("id") int id);

}
