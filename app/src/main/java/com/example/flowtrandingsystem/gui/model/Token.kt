package com.example.flowtrandingsystem.gui.model

data class Token (
    var user: UserToken = UserToken(0, Branch(), "", "", "", Role()),
    var token: String = ""
)