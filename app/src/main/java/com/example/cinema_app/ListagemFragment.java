package com.example.cinema_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.backend.Filme;
import com.example.backend.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class ListagemFragment extends Fragment {

    private ListView listViewFilmes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla o layout do fragmento
        View view = inflater.inflate(R.layout.fragment_listagem, container, false);

        // Inicializa o ListView
        listViewFilmes = view.findViewById(R.id.listViewFilmes);

        // Carregar a lista de filmes da API
        carregarFilmes();

        return view;
    }

    private void carregarFilmes() {
        // Faz a chamada à API usando Retrofit
        Call<List<Filme>> call = RetrofitClient.createService().getAllFilmes(); // Verifique o nome do método na interface
        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Configura o adaptador com a lista de filmes recebida
                    FilmeAdapter adapter = new FilmeAdapter(getContext(), response.body());
                    listViewFilmes.setAdapter(adapter);

                    // Configura o evento de clique no ListView
                    listViewFilmes.setOnItemClickListener((parent, view, position, id) -> {
                        Filme filmeSelecionado = (Filme) parent.getItemAtPosition(position);
                        exibirDetalhes(filmeSelecionado);
                    });
                } else {
                    Toast.makeText(getContext(), "Erro ao carregar filmes", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {
                Toast.makeText(getContext(), "Erro de conexão: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void exibirDetalhes(Filme filme) {
        // Cria uma nova instância do DetalhesFragment
        DetalhesFragment detalhesFragment = new DetalhesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("filme", filme);  // Passa o objeto Filme para o fragmento de detalhes
        detalhesFragment.setArguments(bundle);

        // Navega para o DetalhesFragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detalhesFragment)  // Certifique-se de que o container está correto
                .addToBackStack(null)
                .commit();
    }
}
