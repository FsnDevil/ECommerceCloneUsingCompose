package com.example.ecommerceclone.data.models

data class LoginUiData(
    var email:String="",
    var password:String="",
    var emailError:Boolean=false,
    var passwordError:Boolean=false
)