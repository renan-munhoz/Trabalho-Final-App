package com.example.cinema_app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import com.example.backend.Filme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar a Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Carregar a tela de Cadastro por padrão
        if (savedInstanceState == null) {
            replaceFragment(CadastroFragment())
        }
    }

    // Função para substituir fragmentos
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    // Método para abrir o DetalhesFragment com um filme específico
    fun openDetalhesFragment(filme: Filme) {
        val detalhesFragment = DetalhesFragment()
        val bundle = Bundle()
        detalhesFragment.arguments = bundle
        replaceFragment(detalhesFragment)
    }

    // Inflar o menu na Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Manipular cliques nos itens do menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cadastro -> {
                replaceFragment(CadastroFragment())
                return true
            }
            R.id.menu_lista -> {
                replaceFragment(ListagemFragment())
                return true
            }
            
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
