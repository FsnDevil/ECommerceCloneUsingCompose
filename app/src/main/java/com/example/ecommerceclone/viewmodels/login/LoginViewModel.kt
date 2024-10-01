package com.example.ecommerceclone.viewmodels.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.ecommerceclone.data.models.LoginUiData
import com.example.ecommerceclone.ui.utils.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel() : ViewModel() {
    var loginUiData = mutableStateOf(LoginUiData())
    val auth: FirebaseAuth = Firebase.auth
    var isLoginInputValid = mutableStateOf(false)

    var isLoginSuccess = mutableStateOf(false)

    var isLoginFail = mutableStateOf("")

    fun loginUiEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChanged -> {
                loginUiData.value.email = event.email
            }

            is LoginUiEvent.PasswordChanged -> {
                loginUiData.value.password = event.password
            }

            LoginUiEvent.LoginButtonClicked -> {
                loginUserWithEmailAndPassword(loginUiData.value.email, loginUiData.value.password)
            }
        }

        validateDataWithRules()
    }

    private fun loginUserWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                if (it.user!=null){
                    isLoginSuccess.value=true
                }
            }
            .addOnFailureListener{
                isLoginFail.value= it.localizedMessage
            }
    }

    private fun validateDataWithRules() {
        val emailResult = Validator.validateEmail(loginUiData.value.email)
        val passwordResult = Validator.validatePassword(loginUiData.value.password)

        loginUiData.value.emailError = emailResult.status
        loginUiData.value.passwordError = passwordResult.status

        isLoginInputValid.value = emailResult.status && passwordResult.status
    }
}

sealed class LoginUiEvent {
    data class EmailChanged(val email: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    data object LoginButtonClicked : LoginUiEvent()
}