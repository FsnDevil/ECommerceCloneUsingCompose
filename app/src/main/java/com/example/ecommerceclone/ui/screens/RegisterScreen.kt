package com.example.ecommerceclone.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceclone.R
import com.example.ecommerceclone.ui.components.ButtonComponent
import com.example.ecommerceclone.ui.components.CheckboxComponent
import com.example.ecommerceclone.ui.components.ClickableLoginTextComponent
import com.example.ecommerceclone.ui.components.DividerTextComponent
import com.example.ecommerceclone.ui.components.HeadingTextComponent
import com.example.ecommerceclone.ui.components.NormalTextComponent
import com.example.ecommerceclone.ui.components.PasswordTextFieldComponent
import com.example.ecommerceclone.ui.components.TextFieldComponent
import com.example.ecommerceclone.ui.utils.Screens
import com.example.ecommerceclone.viewmodels.register.RegisterViewModel
import com.example.ecommerceclone.viewmodels.register.RegistrationUiEvent
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = viewModel(),
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                Column {
                    NormalTextComponent(value = stringResource(id = R.string.hello))
                    HeadingTextComponent(value = stringResource(id = R.string.create_account))

                    Spacer(modifier = Modifier.height(20.dp))


                    TextFieldComponent(
                        labelValue = "First name",
                        painterResource = painterResource(id = R.drawable.profile),
                        onValueChange = {
                            registerViewModel.registrationUiEvent(
                                RegistrationUiEvent.FirstNameChanged(
                                    it
                                )
                            )
                        },
                        errorStatus = registerViewModel.regDataState.value.firstNameError
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    TextFieldComponent(
                        labelValue = "Last name",
                        painterResource = painterResource(id = R.drawable.profile),
                        onValueChange = {
                            registerViewModel.registrationUiEvent(
                                RegistrationUiEvent.LastNameChanged(
                                    it
                                )
                            )
                        },
                        errorStatus = registerViewModel.regDataState.value.lastNameError
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    TextFieldComponent(
                        labelValue = "Email",
                        painterResource = painterResource(id = R.drawable.message),
                        onValueChange = {
                            registerViewModel.registrationUiEvent(
                                RegistrationUiEvent.EmailChanged(
                                    it
                                )
                            )
                        },
                        errorStatus = registerViewModel.regDataState.value.emailError
                    )


                    Spacer(modifier = Modifier.padding(10.dp))

                    PasswordTextFieldComponent(
                        labelValue = "Password",
                        painterResource = painterResource(id = R.drawable.lock),
                        onValueChange = {
                            registerViewModel.registrationUiEvent(
                                RegistrationUiEvent.PasswordChanged(
                                    it
                                )
                            )
                        },
                        errorStatus = registerViewModel.regDataState.value.passwordError
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                        onTextSelected = {

                        },
                        onCheckedChange = {

                        }
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    ButtonComponent(
                        value = stringResource(id = R.string.register),
                        onButtonClicked = {
                            registerViewModel.registrationUiEvent(RegistrationUiEvent.RegisterButtonClicked)
                        },
                        isEnabled = registerViewModel.isInputValid.value
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DividerTextComponent()

                    ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                        navController.navigate(Screens.LOGIN.toString()) {
                            popUpTo(Screens.REGISTER.toString()) {
                                inclusive = true
                            }
                        }
                    })
                }
            }
        }
    }


    LaunchedEffect(registerViewModel.isRegistrationSuccess.value) {
        if (registerViewModel.isRegistrationSuccess.value) {
            navController.navigate(Screens.HOME.toString()) {
                popUpTo(Screens.REGISTER.toString()) {
                    inclusive = true
                }
            }
        }
    }
}