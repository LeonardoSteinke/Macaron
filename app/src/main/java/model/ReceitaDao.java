package model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReceitaDao {
    @Query("SELECT * FROM Receita")
    List<Usuario> getAll();

    @Query("SELECT * FROM Receita WHERE id IN (:userIds)")
    List<Receita> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Receita WHERE nome LIKE :nome LIMIT 1")
    Receita findByName(String nome);

    @Insert
    void insertAll(Receita... receitas);

    @Delete
    void delete(Receita receita);

}
