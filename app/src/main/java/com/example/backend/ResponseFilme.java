package com.example.backend;
import com.google.gson.annotations.SerializedName;

public class ResponseFilme {
    @SerializedName("id")
    private int id;

    @SerializedName("message")
    private String message;

    // Outros campos que a resposta possa conter

    // Getters e setters
}
