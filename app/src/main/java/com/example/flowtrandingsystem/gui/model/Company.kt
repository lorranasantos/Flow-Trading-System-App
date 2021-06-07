package com.example.flowtrandingsystem.gui.model

data class Company (

    var id: Int = 0,
    var fantasy_name: String = "",
    var social_reason: String = "",
    var commercial_email: String = "",
    var plan_id: String = "",
    var nature_of_the_business: String = "",
    var Branch: Branch = Branch()

)
