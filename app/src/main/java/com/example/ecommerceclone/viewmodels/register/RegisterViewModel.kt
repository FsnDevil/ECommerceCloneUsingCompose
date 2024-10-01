package com.example.ecommerceclone.viewmodels.register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ecommerceclone.data.models.RegistrationUiData
import com.example.ecommerceclone.ui.utils.Validator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {

    private val TAG: String="RegisterViewModel"
    private var auth: FirebaseAuth = Firebase.auth

    var regDataState = mutableStateOf(RegistrationUiData())

    var isInputValid = mutableStateOf(false)

    var isRegistrationSuccess= mutableStateOf(false)

    fun registrationUiEvent(registrationUiEvent: RegistrationUiEvent) {
        when (registrationUiEvent) {
            is RegistrationUiEvent.FirstNameChanged -> {
                regDataState.value.firstName=registrationUiEvent.firstName
            }

            is RegistrationUiEvent.LastNameChanged -> {
                regDataState.value.lastName=registrationUiEvent.lastName
            }

            is RegistrationUiEvent.EmailChanged -> {
                regDataState.value.email=registrationUiEvent.email
            }

            is RegistrationUiEvent.PasswordChanged -> {
                regDataState.value.password=registrationUiEvent.password
            }

            RegistrationUiEvent.RegisterButtonClicked -> {
                createUserWithEmailAndPassword(regDataState.value.email,regDataState.value.password)
            }
        }

        validateDataWithRules()
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = regDataState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = regDataState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = regDataState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = regDataState.value.password
        )

        regDataState.value.firstNameError=fNameResult.status
        regDataState.value.lastNameError=lNameResult.status
        regDataState.value.emailError=emailResult.status
        regDataState.value.passwordError=passwordResult.status

        isInputValid.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status

    }

    private fun createUserWithEmailAndPassword(email:String,password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d(TAG,"User Created..")
                    isRegistrationSuccess.value=true
                }
            }
            .addOnFailureListener{
                Log.d(TAG,"Something Went Wrong..")
                Log.d(TAG,it.localizedMessage)
                Log.d(TAG,it.message.toString())
                isRegistrationSuccess.value=false
            }
    }
}

sealed class RegistrationUiEvent {
    data class FirstNameChanged(val firstName: String) : RegistrationUiEvent()
    data class LastNameChanged(val lastName: String) : RegistrationUiEvent()
    data class EmailChanged(val email: String) : RegistrationUiEvent()
    data class PasswordChanged(val password: String) : RegistrationUiEvent()
    data object RegisterButtonClicked : RegistrationUiEvent()
}