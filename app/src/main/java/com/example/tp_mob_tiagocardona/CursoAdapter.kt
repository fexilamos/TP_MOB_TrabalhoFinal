package com.example.tp_mob_tiagocardona

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_mob_tiagocardona.databinding.ItemCursoBinding

class CursoAdapter(
    private var cursos: List<Curso>,
    private val onItemClick: (Curso) -> Unit
) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    inner class CursoViewHolder(val binding: ItemCursoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val binding = ItemCursoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CursoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = cursos[position]
        with(holder.binding) {
            txtNome.text = curso.nome
            txtLocalData.text = "${curso.local} – ${curso.dataInicio}"
            try {
                imgCurso.setImageResource(curso.imagemResId)
            } catch (e: Exception) {
                // fallback to a default drawable if resource is invalid
                imgCurso.setImageResource(R.drawable.a1)
            }

            root.setOnClickListener {
                onItemClick(curso)
            }
        }
    }

    override fun getItemCount(): Int = cursos.size

    fun atualizarLista(novaLista: List<Curso>) {
        cursos = novaLista
        notifyDataSetChanged()
    }
}