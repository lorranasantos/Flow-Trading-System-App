package com.example.flowtrandingsystem.gui.model

data class UserToken(
        var branch: Branch = Branch(),
        var id: Int = 0,
        var user_cpf: String = "",
        var user_rg: String = ""
)
