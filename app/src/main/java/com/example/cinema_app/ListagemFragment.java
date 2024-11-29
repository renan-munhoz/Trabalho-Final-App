package com.example.cinema_app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.backend.ApiService;
import com.example.backend.Filme;
import com.example.backend.ResponseFilme;
import com.example.backend.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ListagemFragment extends Fragment {

    private ListView listViewFilmes;
    private ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listagem, container, false);
        listViewFilmes = view.findViewById(R.id.listViewFilmes);

        apiService = RetrofitClient.getApiService();

        carregarFilmes();
        return view;
    }

    private void carregarFilmes() {
        Call<List<Filme>> call = apiService.getData();
        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Filme> filmes = response.body();
                    exibirFilmes(filmes);
                } else {
                    mostrarErro("Erro ao carregar filmes: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {
                mostrarErro("Falha na conexão: " + t.getMessage());
            }
        });
    }



    private void exibirFilmes(List<Filme> filmes) {
        for (Filme filme : filmes) {
            Log.d("ListagemFragment", "ID: " + filme.getId() + ", Título: " +
                    (filme.getData() != null ? filme.getData().getTitulo() : "null"));
        }
        ArrayAdapter<Filme> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, filmes);
        listViewFilmes.setAdapter(adapter);

        listViewFilmes.setOnItemClickListener((parent, view, position, id) -> {
            Filme filmeClicado = filmes.get(position);
            exibirDetalhes(filmeClicado);
        });
    }


    private void mostrarErro(String mensagem) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(), mensagem, Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void exibirDetalhes(Filme filme) {
        DetalhesFragment detalhesFragment = new DetalhesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("filme", filme);
        detalhesFragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detalhesFragment)
                .addToBackStack(null)
                .commit();
    }

}
