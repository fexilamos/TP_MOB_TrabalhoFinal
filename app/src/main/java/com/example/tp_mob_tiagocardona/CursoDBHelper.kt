package com.example.tp_mob_tiagocardona

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CursoDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "cursos.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "cursos"
        const val COLUMN_ID = "id"
        const val COLUMN_NOME = "nome"
        const val COLUMN_LOCAL = "local"
        const val COLUMN_DATA_INICIO = "data_inicio"
        const val COLUMN_DATA_FIM = "data_fim"
        const val COLUMN_PRECO = "preco"
        const val COLUMN_DURACAO = "duracao"
        const val COLUMN_EDICAO = "edicao"
        const val COLUMN_IMAGEM = "imagem"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOME TEXT NOT NULL,
                $COLUMN_LOCAL TEXT NOT NULL,
                $COLUMN_DATA_INICIO TEXT NOT NULL,
                $COLUMN_DATA_FIM TEXT NOT NULL,
                $COLUMN_PRECO REAL NOT NULL,
                $COLUMN_DURACAO TEXT NOT NULL,
                $COLUMN_EDICAO TEXT NOT NULL,
                $COLUMN_IMAGEM INTEGER NOT NULL
            )
        """.trimIndent()
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}