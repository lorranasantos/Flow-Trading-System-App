package com.example.flowtrandingsystem.gui.model

data class User(

        var id: Int = 0,
        var user_name: String = "",
        var cpf: String = "",
        var rg: String = "",
        var Branch: Branch = Branch(),
        var Role: Role = Role(),
        var Permissions: Permissions = Permissions()

)
