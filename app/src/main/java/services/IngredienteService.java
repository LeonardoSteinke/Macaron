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

    @GET("ingrediente/{id}")
    Call<Ingrediente> select(@Path("id") int id);

    @GET("ingrediente")
    Call<List<Ingrediente>> select();

}
