package model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingrediente {

    @PrimaryKey
    private int id;
    private int id_secundario;
    @ColumnInfo(name = "nome")
    private String ingrediente;
    @ColumnInfo(name = "unidadeMedida")
    private String unidade_medida;
    @ColumnInfo(name = "quantidade")
    private float quantidade;

    public Ingrediente() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_secundario() {
        return id_secundario;
    }

    public void setId_secundario(int id_secundario) {
        this.id_secundario = id_secundario;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getUnidade_medida() {
        return unidade_medida;
    }

    public void setUnidade_medida(String unidade_medida) {
        this.unidade_medida = unidade_medida;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
}
