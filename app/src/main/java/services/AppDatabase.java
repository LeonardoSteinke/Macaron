package services;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import model.Ingrediente;
import model.Receita;
import model.Usuario;
import model.UsuarioDao;

@Database(entities = {Usuario.class, Receita.class, Ingrediente.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
}
