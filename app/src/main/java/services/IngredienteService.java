package services;

import java.util.List;

import model.Ingrediente;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IngredienteService {

    @GET("receita/ingredientes/{id}")
    Call<List<Ingrediente>> selectReceitaIngredientes(@Path("id") int id);

    @GET("usuario/ingredientes/{id}")
    Call<List<Ingrediente>> selectUsuarioIngredientes(@Path("id") int id);

    @GET("receita/ingredientes")
    Call<List<Ingrediente>> selectReceita();

}
