package com.example.tp_mob_tiagocardona

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp_mob_tiagocardona.databinding.ActivityMainBinding
import kotlin.collections.addAll
import kotlin.text.clear

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cursoDao: CursoDao
    private lateinit var adapter: CursoAdapter
    private var cursos = mutableListOf<Curso>()

    private val formCursoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            carregarCursos()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cursoDao = CursoDao(this)

        binding.btnAdicionar.setOnClickListener {
            val intent = Intent(this, FormCursoActivity::class.java)
            formCursoLauncher.launch(intent)
        }

        binding.sobreapp.setOnClickListener {
            val intent = Intent(this, SobreActivity::class.java)
            startActivity(intent)
        }



        val spinner = findViewById<Spinner>(R.id.spinnerOrdenacao)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> cursos.sortBy { it.nome }
                    1 -> cursos.sortByDescending { it.nome }
                    2 -> cursos.sortBy { it.dataInicio }
                    3 -> cursos.sortByDescending { it.dataInicio }
                }
                adapter.atualizarLista(cursos) // ⬅️ Atualiza a lista mostrada
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Inicializar o adapter com lista vazia
        adapter = CursoAdapter(cursos) { curso ->
            val intent = Intent(this, CursoDetalhesActivity::class.java).apply {
                putExtra("id", curso.id)
                putExtra("nome", curso.nome)
                putExtra("local", curso.local)
                putExtra("dataInicio", curso.dataInicio)
                putExtra("dataFim", curso.dataFim)
                putExtra("preco", curso.preco)
                putExtra("duracao", curso.duracao)
                putExtra("edicao", curso.edicao)
                putExtra("imagem", curso.imagemResId)
            }
            formCursoLauncher.launch(intent)
        }
        cursoDao.inicializarCursosPadrao()

        binding.recyclerCursos.setHasFixedSize(true)
        binding.recyclerCursos.layoutManager = LinearLayoutManager(this)
        binding.recyclerCursos.adapter = adapter

        carregarCursos()
    }

    private fun carregarCursos() {
        cursos.clear()
        cursos.addAll(cursoDao.listar())
        adapter.atualizarLista(cursos)
    }
}
