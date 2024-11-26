package com.example.backend;

import java.io.Serializable;

public class Filme implements Serializable{

    private int id;
    private String titulo;
    private String anoEstreia;
    private String diretor;
    private String genero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnoEstreia() {
        return anoEstreia;
    }

    public void setAnoEstreia(String anoEstreia) {
        this.anoEstreia = anoEstreia;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
