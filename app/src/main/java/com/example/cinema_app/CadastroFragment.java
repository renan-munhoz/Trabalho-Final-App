package com.example.cinema_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.backend.Filme;
import com.example.backend.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroFragment extends Fragment {

    private EditText editTitulo, editAno, editDiretor, editGenero;
    private Button btnSalvar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        // Inicializa os componentes do layout
        editTitulo = view.findViewById(R.id.editTitulo);
        editAno = view.findViewById(R.id.editAno);
        editDiretor = view.findViewById(R.id.editDiretor);
        editGenero = view.findViewById(R.id.editGenero);
        btnSalvar = view.findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> salvarFilme());

        return view;
    }

    private void salvarFilme() {
        Filme filme = new Filme();
        filme.setTitulo(editTitulo.getText().toString());
        filme.setAnoEstreia(editAno.getText().toString());
        filme.setDiretor(editDiretor.getText().toString());
        filme.setGenero(editGenero.getText().toString());

        RetrofitClient.createService().postData(filme).enqueue(new Callback<Filme>() {
            @Override
            public void onResponse(Call<Filme> call, Response<Filme> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Filme cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos(); // Limpa os campos após o cadastro
                } else {
                    Toast.makeText(getContext(), "Erro ao cadastrar filme!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Filme> call, Throwable t) {
                Toast.makeText(getContext(), "Erro de conexão!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limparCampos() {
        editTitulo.setText("");
        editAno.setText("");
        editDiretor.setText("");
        editGenero.setText("");
    }
}
