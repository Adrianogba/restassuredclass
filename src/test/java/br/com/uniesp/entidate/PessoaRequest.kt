package br.com.uniesp.entidate

class PessoaRequest {
    var nome: String? = null
    var job: String? = null

    constructor(nome: String?, job: String?) : super() {
        this.nome = nome
        this.job = job
    }

    constructor() : super() {        // TODO Auto-generated constructor stub
    }
}