package com.example.backend;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Filme implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("data")
    private FilmeData data;

    public static class FilmeData implements Serializable {
        @SerializedName("anoEstreia")
        private String anoEstreia;
        @SerializedName("diretor")
        private String diretor;
        @SerializedName("genero")
        private String genero;
        @SerializedName("titulo")
        private String titulo;

        // Getters e setters para FilmeData

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

    // Getters e setters para Filme


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FilmeData getData() {
        return data;
    }

    public void setData(FilmeData data) {
        this.data = data;
    }

    // Construtor que inicializa data
    public Filme() {
        this.data = new FilmeData();
    }

    @Override
    public String toString() {
        return data != null ? data.titulo : "Sem título";
    }
}
