package com.example.tp_mob_tiagocardona

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tp_mob_tiagocardona.databinding.ActivityCursoDetalhesBinding

class CursoDetalhesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursoDetalhesBinding

    private val editCursoLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            setResult(RESULT_OK)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursoDetalhesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome") ?: ""
        val local = intent.getStringExtra("local") ?: ""
        val dataInicio = intent.getStringExtra("dataInicio") ?: ""
        val dataFim = intent.getStringExtra("dataFim") ?: ""
        val preco = intent.getDoubleExtra("preco", 0.0)
        val duracao = intent.getStringExtra("duracao") ?: ""
        val edicao = intent.getStringExtra("edicao") ?: ""
        val imagem = intent.getIntExtra("imagem", R.drawable.ic_launcher_foreground)
        val cursoId = intent.getIntExtra("id", -1)

        binding.txtNome.text = nome
        binding.txtLocal.text = local
        binding.txtDataInicio.text = dataInicio
        binding.txtDataFim.text = dataFim
        binding.txtPreco.text = "€ $preco"
        binding.txtDuracao.text = duracao
        binding.txtEdicao.text = edicao
        try {
            binding.imgCurso.setImageResource(imagem)
        } catch (e: Exception) {
            binding.imgCurso.setImageResource(R.drawable.a1)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.btnEdit.setOnClickListener {
            val editIntent = Intent(this, FormCursoActivity::class.java)
            editIntent.putExtra("id", cursoId)
            editIntent.putExtra("nome", binding.txtNome.text.toString())
            editIntent.putExtra("local", binding.txtLocal.text.toString())
            editIntent.putExtra("dataInicio", binding.txtDataInicio.text.toString())
            editIntent.putExtra("dataFim", binding.txtDataFim.text.toString())
            editIntent.putExtra("preco", preco)
            editIntent.putExtra("duracao", binding.txtDuracao.text.toString())
            editIntent.putExtra("edicao", binding.txtEdicao.text.toString())
            editIntent.putExtra("imagemResId", imagem)
            editCursoLauncher.launch(editIntent)
        }
    }
}
