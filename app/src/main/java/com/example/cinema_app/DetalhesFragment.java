package com.example.cinema_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.backend.Filme;
import com.example.backend.RetrofitClient;

public class DetalhesFragment extends Fragment {

    private TextView textTitulo, textAno, textDiretor, textGenero;

    public static DetalhesFragment newInstance(Filme filme) {
        DetalhesFragment fragment = new DetalhesFragment();
        Bundle args = new Bundle();
        args.putSerializable("filme", filme);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhes, container, false);

        textTitulo = view.findViewById(R.id.textTitulo);
        textAno = view.findViewById(R.id.textAno);
        textDiretor = view.findViewById(R.id.textDiretor);
        textGenero = view.findViewById(R.id.textGenero);

        if (getArguments() != null) {
            Filme filme = (Filme) getArguments().getSerializable("filme");
            if (filme != null) {
                textTitulo.setText(filme.getTitulo());
                textAno.setText(filme.getAnoEstreia());
                textDiretor.setText(filme.getDiretor());
                textGenero.setText(filme.getGenero());
            }
        }

        return view;
    }
}

