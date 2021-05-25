package com.example.flowtrandingsystem.gui.model

import com.google.gson.annotations.SerializedName

data class Usuario(

        @SerializedName("cnpj_ou_cpf")
        var cnpj_ou_cpf: String = "",

        @SerializedName("password")
        var password: String = ""

)
