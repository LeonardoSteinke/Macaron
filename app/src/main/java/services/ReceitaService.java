package services;

import java.util.List;

import model.Receita;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReceitaService {

    @GET("receita/{id}")
    Call<Receita> select(@Path("id") int id);

    @GET("receita")
    Call<List<Receita>> select();

    @POST("/receita")
    Call<Receita> cadastrarReceita(@Body Receita receita);

    @PUT("/receita/{id}")
    Call<Receita> atualizarReceita(@Path("id") int id);

}
