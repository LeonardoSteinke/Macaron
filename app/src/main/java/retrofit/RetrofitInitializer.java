package retrofit;

import model.Ingrediente;
import retrofit2.converter.gson.GsonConverterFactory;
import services.IngredienteReceitaService;
import services.ReceitaService;
import services.SessionService;
import services.UsuarioService;

import retrofit2.Retrofit;

public class RetrofitInitializer {

    private final Retrofit retrofit;

    private String baseUrl = "https://localhost:3333";
    private String baseUrl2 = "https://macaron-ddm.herokuapp.com";

    public RetrofitInitializer() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UsuarioService setUserService() {
        return retrofit.create(UsuarioService.class);
    }

    public SessionService setSession() {
        return retrofit.create(SessionService.class);
    }

    public ReceitaService setReceitaService() {
        return retrofit.create(ReceitaService.class);
    }

    public IngredienteReceitaService setIngredienteReceitaService() {
        return retrofit.create(IngredienteReceitaService.class);
    }


}
