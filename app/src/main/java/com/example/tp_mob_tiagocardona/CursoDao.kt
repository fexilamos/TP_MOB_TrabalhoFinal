package com.example.tp_mob_tiagocardona

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class CursoDao(context: Context) {

    private val dbHelper = CursoDbHelper(context)

    // Inserir curso
    fun inserir(curso: Curso): Long {
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(CursoDbHelper.COLUMN_NOME, curso.nome)
            put(CursoDbHelper.COLUMN_LOCAL, curso.local)
            put(CursoDbHelper.COLUMN_DATA_INICIO, curso.dataInicio)
            put(CursoDbHelper.COLUMN_DATA_FIM, curso.dataFim)
            put(CursoDbHelper.COLUMN_PRECO, curso.preco)
            put(CursoDbHelper.COLUMN_DURACAO, curso.duracao)
            put(CursoDbHelper.COLUMN_EDICAO, curso.edicao)
            put(CursoDbHelper.COLUMN_IMAGEM, curso.imagemResId)
        }

        val id = db.insert(CursoDbHelper.TABLE_NAME, null, values)
        db.close()
        return id
    }

    // Atualizar curso
    fun atualizar(curso: Curso): Int {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(CursoDbHelper.COLUMN_NOME, curso.nome)
            put(CursoDbHelper.COLUMN_LOCAL, curso.local)
            put(CursoDbHelper.COLUMN_DATA_INICIO, curso.dataInicio)
            put(CursoDbHelper.COLUMN_DATA_FIM, curso.dataFim)
            put(CursoDbHelper.COLUMN_PRECO, curso.preco)
            put(CursoDbHelper.COLUMN_DURACAO, curso.duracao)
            put(CursoDbHelper.COLUMN_EDICAO, curso.edicao)
            put(CursoDbHelper.COLUMN_IMAGEM, curso.imagemResId)
        }

        val rows = db.update(
            CursoDbHelper.TABLE_NAME,
            values,
            "${CursoDbHelper.COLUMN_ID} = ?",
            arrayOf(curso.id.toString())
        )
        db.close()
        return rows
    }

    // Eliminar curso
    fun eliminar(id: Int): Int {
        val db = dbHelper.writableDatabase
        val rows = db.delete(
            CursoDbHelper.TABLE_NAME,
            "${CursoDbHelper.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )
        db.close()
        return rows
    }

    // Listar todos os cursos
    fun listar(): List<Curso> {
        val lista = mutableListOf<Curso>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            CursoDbHelper.TABLE_NAME,
            null, null, null, null, null, null
        )

        with(cursor) {
            while (moveToNext()) {
                val curso = Curso(
                    id = getInt(getColumnIndexOrThrow(CursoDbHelper.COLUMN_ID)),
                    nome = getString(getColumnIndexOrThrow(CursoDbHelper.COLUMN_NOME)),
                    local = getString(getColumnIndexOrThrow(CursoDbHelper.COLUMN_LOCAL)),
                    dataInicio = getString(getColumnIndexOrThrow(CursoDbHelper.COLUMN_DATA_INICIO)),
                    dataFim = getString(getColumnIndexOrThrow(CursoDbHelper.COLUMN_DATA_FIM)),
                    preco = getDouble(getColumnIndexOrThrow(CursoDbHelper.COLUMN_PRECO)),
                    duracao = getString(getColumnIndexOrThrow(CursoDbHelper.COLUMN_DURACAO)),
                    edicao = getString(getColumnIndexOrThrow(CursoDbHelper.COLUMN_EDICAO)),
                    imagemResId = getInt(getColumnIndexOrThrow(CursoDbHelper.COLUMN_IMAGEM))
                )
                lista.add(curso)
            }
        }

        cursor.close()
        db.close()
        return lista
    }
    fun inicializarCursosPadrao() {
        if (listar().isEmpty()) {
            inserir(
                Curso(
                    nome = "Linux Server and Networking Administration",
                    local = "Online",
                    dataInicio = "2025-06-16",
                    dataFim = "2025-12-16",
                    preco = 1200.0,
                    duracao = "6 meses",
                    edicao = "Edição 1",
                    imagemResId = R.drawable.a1
                )
            )
            inserir(
                Curso(
                    nome = "AWS RE/START - Cloud Computing and Network Administration",
                    local = "Porto",
                    dataInicio = "2025-07-01",
                    dataFim = "2025-12-01",
                    preco = 1000.0,
                    duracao = "5 meses",
                    edicao = "Edição 3",
                    imagemResId = R.drawable.a2
                )
            )
            inserir(
                Curso(
                    nome = "Introdução à Ciência de Dados com Python",
                    local = "Online",
                    dataInicio = "2025-09-10",
                    dataFim = "2026-02-10",
                    preco = 900.0,
                    duracao = "5 meses",
                    edicao = "Edição 2",
                    imagemResId = R.drawable.a3
                )
            )
            inserir(
                Curso(
                    nome = "Modelação e Animação 3D no Blender",
                    local = "Lisboa",
                    dataInicio = "2025-08-20",
                    dataFim = "2026-01-20",
                    preco = 1500.0,
                    duracao = "5 meses",
                    edicao = "Edição 1",
                    imagemResId = R.drawable.a4
                )
            )
            inserir(
                Curso(
                    nome = "Desenvolvimento de Aplicações Móveis com Flutter",
                    local = "Porto",
                    dataInicio = "2025-07-05",
                    dataFim = "2025-12-05",
                    preco = 1200.0,
                    duracao = "5 meses",
                    edicao = "Edição 3",
                    imagemResId = R.drawable.a5
                )
            )
        }
    }
}