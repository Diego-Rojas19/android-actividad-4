package com.example.tiendaapp.data

import android.content.Context
import java.security.MessageDigest

data class User(
    val fullName: String,
    val email: String,
    val passwordHash: String
)

sealed class AuthResult {
    data class Success(val user: User) : AuthResult()
    object EmailNotFound : AuthResult()
    object IncorrectPassword : AuthResult()
}

class UserRepository(context: Context) {
    private val prefs = context.getSharedPreferences("tienda_app_users", Context.MODE_PRIVATE)

    init {
        // Pre-registrar cuentas de prueba iniciales si no existen
        if (!isEmailRegistered("admin@sena.edu.co") && !isEmailRegistered("usuario@correo.com")) {
            registerUser("Aprendiz SENA", "admin@sena.edu.co", "sena1234")
            registerUser("Usuario Ejemplo", "usuario@correo.com", "123456")
        }
    }

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    fun isEmailRegistered(email: String): Boolean {
        val key = email.lowercase().trim()
        return prefs.contains("user_name_$key")
    }

    fun registerUser(fullName: String, email: String, password: String): Boolean {
        val key = email.lowercase().trim()
        if (prefs.contains("user_name_$key")) {
            return false // El correo ya está registrado
        }
        val passwordHash = hashPassword(password)
        prefs.edit()
            .putString("user_name_$key", fullName)
            .putString("user_email_$key", key)
            .putString("user_hash_$key", passwordHash)
            .apply()
        return true
    }

    fun validateCredentials(email: String, password: String): AuthResult {
        val key = email.lowercase().trim()
        if (!isEmailRegistered(key)) {
            return AuthResult.EmailNotFound
        }
        val storedHash = prefs.getString("user_hash_$key", "") ?: ""
        val inputHash = hashPassword(password)
        return if (storedHash == inputHash) {
            val name = prefs.getString("user_name_$key", "Usuario") ?: "Usuario"
            AuthResult.Success(User(name, key, storedHash))
        } else {
            AuthResult.IncorrectPassword
        }
    }

    fun resetPassword(email: String, newPassword: String): Boolean {
        val key = email.lowercase().trim()
        if (!isEmailRegistered(key)) return false
        val newHash = hashPassword(newPassword)
        prefs.edit().putString("user_hash_$key", newHash).apply()
        return true
    }
}
