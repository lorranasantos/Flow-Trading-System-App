package com.example.flowtrandingsystem.gui.model

import com.google.gson.annotations.SerializedName

data class UsuarioInfo (

    @SerializedName("cnpj_ou_cpf")
    var cnpj_ou_cpf: String = "",

    @SerializedName("user_rg")
    var user_rg: String = "",

    @SerializedName("user_branch_id")
    var user_branch_id: Int = 0,

    @SerializedName("permission_name")
    var permission_name: String = ""
)