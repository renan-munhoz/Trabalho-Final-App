package com.example.cinema_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.backend.Filme;
import com.example.backend.ResponseFilme;
import com.example.backend.RetrofitClient;

public class DetalhesFragment extends Fragment {
    private TextView textViewTitulo, textViewAno, textViewDiretor, textViewGenero;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes, container, false);

        textViewTitulo = view.findViewById(R.id.textViewTitulo);
        textViewAno = view.findViewById(R.id.textViewAno);
        textViewDiretor = view.findViewById(R.id.textViewDiretor);
        textViewGenero = view.findViewById(R.id.textViewGenero);

        // Recupera o filme passado como argumento
        Filme filme = (Filme) getArguments().getSerializable("filme");
        if (filme != null) {
            exibirDetalhesFilme(filme);
        }

        return view;
    }

    private void exibirDetalhesFilme(Filme filme) {
        textViewTitulo.setText(filme.getData().getTitulo());
        textViewAno.setText("Ano de Estreia: " + filme.getData().getAnoEstreia());
        textViewDiretor.setText("Diretor: " + filme.getData().getDiretor());
        textViewGenero.setText("Gênero: " + filme.getData().getGenero());
    }
}


