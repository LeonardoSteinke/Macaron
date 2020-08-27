package model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM Usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE id IN (:userIds)")
    List<Usuario> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Usuario WHERE nome LIKE :nome LIMIT 1")
    Usuario findByName(String nome);

    @Insert
    void insertAll(Usuario... usuario);

    @Update
    void updateUsuario(Usuario... usuario);

    @Delete
    void delete(Usuario user);

}
