package com.example.backend;

import com.google.gson.annotations.SerializedName;

public class AuxFilme {
    @SerializedName("anoEstreia")
    private String anoEstreia;
    @SerializedName("diretor")
    private String diretor;
    @SerializedName("genero")
    private String genero;
    @SerializedName("titulo")
    private String titulo;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
