package com.example.ecommerceclone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceclone.R
import com.example.ecommerceclone.ui.components.ButtonComponent
import com.example.ecommerceclone.ui.components.ClickableLoginTextComponent
import com.example.ecommerceclone.ui.components.DividerTextComponent
import com.example.ecommerceclone.ui.components.FirstLineText
import com.example.ecommerceclone.ui.components.PasswordTextFieldComponent
import com.example.ecommerceclone.ui.components.SecondLineText
import com.example.ecommerceclone.ui.components.ShowErrorMessage
import com.example.ecommerceclone.ui.components.TextFieldComponent
import com.example.ecommerceclone.ui.components.UnderLinedTextComponent
import com.example.ecommerceclone.ui.utils.Screens
import com.example.ecommerceclone.viewmodels.login.LoginUiEvent
import com.example.ecommerceclone.viewmodels.login.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    navController: NavController
) {

    val showError = remember { mutableStateOf(false) }
    val errorMessage = loginViewModel.isLoginFail.value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Column {
                    FirstLineText(text = stringResource(id = R.string.heyThere))
                    SecondLineText(text = stringResource(id = R.string.welcomeBack))


                    Spacer(modifier = Modifier.padding(30.dp))

                    TextFieldComponent(
                        labelValue = "Email",
                        painterResource = painterResource(id = R.drawable.message),
                        onValueChange = {
                            loginViewModel.loginUiEvent(LoginUiEvent.EmailChanged(it))
                        }, errorStatus = loginViewModel.loginUiData.value.emailError
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    PasswordTextFieldComponent(
                        labelValue = "Password",
                        painterResource = painterResource(id = R.drawable.lock),
                        onValueChange = {
                            loginViewModel.loginUiEvent(LoginUiEvent.PasswordChanged(it))
                        }, errorStatus = loginViewModel.loginUiData.value.passwordError
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                    Spacer(modifier = Modifier.height(40.dp))

                    ButtonComponent(
                        value = stringResource(id = R.string.login),
                        onButtonClicked = {
                            loginViewModel.loginUiEvent(LoginUiEvent.LoginButtonClicked)
                        },
                        isEnabled = loginViewModel.isLoginInputValid.value
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DividerTextComponent()

                    Spacer(modifier = Modifier.height(20.dp))

                    ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                        navController.navigate(Screens.REGISTER.toString())
                    })
                }
            }

        }
    }

    LaunchedEffect(key1 = loginViewModel.isLoginSuccess.value) {
        if (loginViewModel.isLoginSuccess.value) {
            navController.navigate(Screens.HOME.toString()) {
                popUpTo(Screens.LOGIN.toString()) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(key1 = loginViewModel.isLoginFail.value) {
        if (errorMessage.isNotEmpty()) {
            showError.value = true
        }
    }

    if (showError.value) {
        ShowErrorMessage(
            errorMessage = errorMessage,
            onDismiss = { showError.value = false }
        )
    }
}