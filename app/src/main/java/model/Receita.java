package model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Receita {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "nome")
    private String nome;
    @ColumnInfo(name = "categoria")
    private String categoria;
    @ColumnInfo(name = "modo_preparo")
    private String modo_preparo;
    @ColumnInfo(name = "tempo_preparo")
    private int tempo_preparo;
    @ColumnInfo(name = "dificuldade")
    private int dificuldade;
    @ColumnInfo(name = "porcoes")
    private int porcoes;
    @ColumnInfo(name = "avaliacao")
    private int avaliacao;
    @ColumnInfo(name = "tipo")
    private int tipo;
    @ColumnInfo(name = "id_usuario")
    private int id_usuario;

    public Receita() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModo_preparo() {
        return modo_preparo;
    }

    public void setModo_preparo(String modo_preparo) {
        this.modo_preparo = modo_preparo;
    }

    public int getTempo_preparo() {
        return tempo_preparo;
    }

    public void setTempo_preparo(int tempo_preparo) {
        this.tempo_preparo = tempo_preparo;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getPorcoes() {
        return porcoes;
    }

    public void setPorcoes(int porcoes) {
        this.porcoes = porcoes;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
