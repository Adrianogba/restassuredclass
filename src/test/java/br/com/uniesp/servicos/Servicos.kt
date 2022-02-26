package br.com.uniesp.servicos

enum class Servicos(val valor: String) {
    getUsers_ID("/api/users/{id}"), getUsers_PAGE("/api/users?page={id}");

}