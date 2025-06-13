package com.example.tp_mob_tiagocardona

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp_mob_tiagocardona.databinding.ActivityFormCursoBinding
import java.util.Calendar

class FormCursoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormCursoBinding
    private lateinit var cursoDao: CursoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCursoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cursoDao = CursoDao(this)

        // Check if editing an existing Curso
        val cursoId = intent.getIntExtra("id", -1)
        val isEditing = cursoId != -1
        if (isEditing) {
            binding.edtNome.setText(intent.getStringExtra("nome") ?: "")
            binding.edtLocal.setText(intent.getStringExtra("local") ?: "")
            binding.edtDataInicio.setText(intent.getStringExtra("dataInicio") ?: "")
            binding.edtDataFim.setText(intent.getStringExtra("dataFim") ?: "")
            binding.edtPreco.setText(intent.getDoubleExtra("preco", 0.0).toString())
            binding.edtDuracao.setText(intent.getStringExtra("duracao") ?: "")
            binding.edtEdicao.setText(intent.getStringExtra("edicao") ?: "")
            // You can also handle imagemResId if you add an image picker
        }

        // Date picker for Data de Início
        binding.edtDataInicio.setOnClickListener {
            val cal = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val m = (month + 1).toString().padStart(2, '0')
                val d = dayOfMonth.toString().padStart(2, '0')
                binding.edtDataInicio.setText("$year-$m-$d")
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
        // Date picker for Data de Fim
        binding.edtDataFim.setOnClickListener {
            val cal = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val m = (month + 1).toString().padStart(2, '0')
                val d = dayOfMonth.toString().padStart(2, '0')
                binding.edtDataFim.setText("$year-$m-$d")
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        binding.btnGuardar.setOnClickListener {
            val curso = Curso(
                id = if (isEditing) cursoId else 0,
                nome = binding.edtNome.text.toString(),
                local = binding.edtLocal.text.toString(),
                dataInicio = binding.edtDataInicio.text.toString(),
                dataFim = binding.edtDataFim.text.toString(),
                preco = binding.edtPreco.text.toString().toDoubleOrNull() ?: 0.0,
                duracao = binding.edtDuracao.text.toString(),
                edicao = binding.edtEdicao.text.toString(),
                imagemResId = R.drawable.a1 // Imagem fixa por agora
            )

            if (isEditing) {
                cursoDao.atualizar(curso)
                Toast.makeText(this, "Curso atualizado com sucesso!", Toast.LENGTH_SHORT).show()
            } else {
                cursoDao.inserir(curso)
                Toast.makeText(this, "Curso guardado com sucesso!", Toast.LENGTH_SHORT).show()
            }
            setResult(RESULT_OK)
            finish()
        }
    }
}
