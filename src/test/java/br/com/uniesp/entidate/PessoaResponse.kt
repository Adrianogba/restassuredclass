package br.com.uniesp.entidate

class PessoaResponse {
    var nome: String? = null
    var job: String? = null
    var id: String? = null
    var createdAt: String? = null

    constructor(nome: String?, job: String?, id: String?, createdAt: String?) : super() {
        this.nome = nome
        this.job = job
        this.id = id
        this.createdAt = createdAt
    }

    constructor() : super() {        // TODO Auto-generated constructor stub
    }
}