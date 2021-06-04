package com.example.flowtrandingsystem.gui.model

data class User(
        var cnpj_ou_cpf: String = "",
        var password: String = "",
        var user_rg: String = "",
        var user_branch_id: Int = 0,
        var permission_name: String = ""
)
