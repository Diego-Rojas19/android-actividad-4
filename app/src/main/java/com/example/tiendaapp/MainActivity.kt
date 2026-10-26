package com.example.tiendaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.tiendaapp.ui.screen.CartScreen
import com.example.tiendaapp.ui.screen.ForgotPasswordScreen
import com.example.tiendaapp.ui.screen.HomeScreen
import com.example.tiendaapp.ui.screen.LoginScreen
import com.example.tiendaapp.ui.screen.Product
import com.example.tiendaapp.ui.screen.RegisterScreen
import com.example.tiendaapp.ui.theme.TiendaAppTheme

enum class Screen {
    LOGIN, REGISTER, FORGOT_PASSWORD, HOME, CART
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TiendaAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TiendaAppNavigation()
                    }
                }
            }
        }
    }
}

@Composable
fun TiendaAppNavigation() {
    var currentScreen by rememberSaveable { mutableStateOf(Screen.LOGIN) }
    val cartItems = remember { mutableStateListOf<Product>() }

    when (currentScreen) {
        Screen.LOGIN -> {
            LoginScreen(
                onLoginSuccess = {
                    currentScreen = Screen.HOME
                },
                onNavigateToRegister = {
                    currentScreen = Screen.REGISTER
                },
                onNavigateToForgotPassword = {
                    currentScreen = Screen.FORGOT_PASSWORD
                }
            )
        }
        Screen.REGISTER -> {
            RegisterScreen(
                onBackToLogin = {
                    currentScreen = Screen.LOGIN
                },
                onRegisterSuccess = {
                    currentScreen = Screen.HOME
                }
            )
        }
        Screen.FORGOT_PASSWORD -> {
            ForgotPasswordScreen(
                onBackToLogin = {
                    currentScreen = Screen.LOGIN
                }
            )
        }
        Screen.HOME -> {
            HomeScreen(
                cartCount = cartItems.size,
                onAddToCart = { product ->
                    cartItems.add(product)
                },
                onOpenCart = {
                    currentScreen = Screen.CART
                },
                onLogout = {
                    cartItems.clear()
                    currentScreen = Screen.LOGIN
                }
            )
        }
        Screen.CART -> {
            CartScreen(
                cartItems = cartItems,
                onBack = {
                    currentScreen = Screen.HOME
                },
                onRemoveItem = { product ->
                    cartItems.remove(product)
                },
                onClearCart = {
                    cartItems.clear()
                }
            )
        }
    }
}
