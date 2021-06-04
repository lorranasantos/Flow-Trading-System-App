package com.example.flowtrandingsystem.gui.model

data class Token (
    var user: UserToken = UserToken(Branch()),
    var token : String = ""
)