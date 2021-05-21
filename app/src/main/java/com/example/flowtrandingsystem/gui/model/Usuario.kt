package com.example.flowtrandingsystem.gui.model

class Usuario() {
    var cnpj_ou_cpf: String = ""
    var user_password: String = ""

    override fun toString(): String {
        return "Usuario(cnpj_ou_cpf='$cnpj_ou_cpf', user_password=$user_password)"
    }
}