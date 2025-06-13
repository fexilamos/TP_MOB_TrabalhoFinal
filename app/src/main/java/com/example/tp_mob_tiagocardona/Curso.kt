package com.example.tp_mob_tiagocardona

data class Curso(
    var id: Int = 0,
    var nome: String,
    var local: String,
    var dataInicio: String,
    var dataFim: String,
    var preco: Double,
    var duracao: String,
    var edicao: String,
    var imagemResId: Int
)