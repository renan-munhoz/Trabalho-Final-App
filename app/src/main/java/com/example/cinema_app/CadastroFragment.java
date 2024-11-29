package com.example.cinema_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.backend.AuxFilme;
import com.example.backend.Filme;
import com.example.backend.ResponseFilme;
import com.example.backend.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.backend.ApiService;

public class CadastroFragment extends Fragment {

    private ApiService apiService;
    private EditText editTitulo, editAno, editDiretor, editGenero;
    private Button btnSalvar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = RetrofitClient.getApiService();
    }

    @Nullable
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

        // Inicializa o serviço API
        //realizarLogin();

        btnSalvar.setOnClickListener(v -> salvarFilme());


        return view;
    }

    private void salvarFilme() {
        String anoEstreia = editAno.getText().toString();
        String diretor = editDiretor.getText().toString();
        String genero = editGenero.getText().toString();
        String titulo = editTitulo.getText().toString();

        AuxFilme filmeRequest = new AuxFilme();
        filmeRequest.setDiretor(diretor);
        filmeRequest.setAnoEstreia(anoEstreia);
        filmeRequest.setGenero(genero);
        filmeRequest.setTitulo(titulo);

        Call<ResponseFilme> call = apiService.postData(filmeRequest);
        call.enqueue(new Callback<ResponseFilme>() {
            @Override
            public void onResponse(Call<ResponseFilme> call, Response<ResponseFilme> response) {
                if (response.isSuccessful()) {
                    ResponseFilme resultado = response.body();
                    mostrarMensagemSucesso("Filme salvo com sucesso!");
                    limparCampos();
                } else {
                    mostrarMensagemErro("Erro ao salvar o filme. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseFilme> call, Throwable t) {
                mostrarMensagemErro("Falha na conexão: " + t.getMessage());
            }
        });
    }



    private void mostrarMensagemSucesso(String mensagem) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(), mensagem, Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void mostrarMensagemErro(String mensagem) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(), mensagem, Toast.LENGTH_LONG).show();
            });
        }
    }

    private void limparCampos() {
        editTitulo.setText("");
        editAno.setText("");
        editDiretor.setText("");
        editGenero.setText("");
    }
}
