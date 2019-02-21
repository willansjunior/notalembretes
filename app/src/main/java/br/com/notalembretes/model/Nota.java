package br.com.notalembretes.model;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by willans on 20/02/19.
 */
public class Nota implements Serializable {

    private String titulo;
    private String descricao;

    public Nota(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
