package com.example.tiendaapp

import com.example.tiendaapp.data.AuthResult
import com.example.tiendaapp.data.User
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.security.MessageDigest

data class TestUser(
    val fullName: String,
    val email: String,
    val passwordHash: String
)

class TestAuthService {
    private val storage = mutableMapOf<String, String>()

    init {
        // Pre-registered accounts
        registerUser("Aprendiz SENA", "admin@sena.edu.co", "sena1234")
        registerUser("Usuario Ejemplo", "usuario@correo.com", "123456")
    }

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray(Charsets.UTF_8))
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    fun isEmailRegistered(email: String): Boolean {
        val key = email.lowercase().trim()
        return storage.containsKey("user_name_$key")
    }

    fun registerUser(fullName: String, email: String, password: String): Boolean {
        val key = email.lowercase().trim()
        if (isEmailRegistered(key)) return false
        val passwordHash = hashPassword(password)
        storage["user_name_$key"] = fullName
        storage["user_email_$key"] = key
        storage["user_hash_$key"] = passwordHash
        return true
    }

    fun validateCredentials(email: String, password: String): AuthResult {
        val key = email.lowercase().trim()
        if (!isEmailRegistered(key)) return AuthResult.EmailNotFound
        val storedHash = storage["user_hash_$key"] ?: ""
        val inputHash = hashPassword(password)
        return if (storedHash == inputHash) {
            val name = storage["user_name_$key"] ?: "Usuario"
            AuthResult.Success(User(name, key, storedHash))
        } else {
            AuthResult.IncorrectPassword
        }
    }

    fun resetPassword(email: String, newPassword: String): Boolean {
        val key = email.lowercase().trim()
        if (!isEmailRegistered(key)) return false
        storage["user_hash_$key"] = hashPassword(newPassword)
        return true
    }
}

class AuthLogicTest {

    private lateinit var authService: TestAuthService

    @Before
    fun setUp() {
        authService = TestAuthService()
    }

    @Test
    fun testDefaultAccountsExist() {
        assertTrue(authService.isEmailRegistered("admin@sena.edu.co"))
        assertTrue(authService.isEmailRegistered("usuario@correo.com"))
    }

    @Test
    fun testLoginSuccess() {
        val result = authService.validateCredentials("admin@sena.edu.co", "sena1234")
        assertTrue(result is AuthResult.Success)
        assertEquals("Aprendiz SENA", (result as AuthResult.Success).user.fullName)
    }

    @Test
    fun testLoginIncorrectPassword() {
        val result = authService.validateCredentials("admin@sena.edu.co", "wrong_password")
        assertEquals(AuthResult.IncorrectPassword, result)
    }

    @Test
    fun testLoginEmailNotFound() {
        val result = authService.validateCredentials("no_existe@sena.edu.co", "sena1234")
        assertEquals(AuthResult.EmailNotFound, result)
    }

    @Test
    fun testRegisterNewUserSuccess() {
        val registered = authService.registerUser("Maria Gomez", "maria@sena.edu.co", "maria2025")
        assertTrue(registered)
        assertTrue(authService.isEmailRegistered("maria@sena.edu.co"))

        // Login with new user
        val loginResult = authService.validateCredentials("maria@sena.edu.co", "maria2025")
        assertTrue(loginResult is AuthResult.Success)
        assertEquals("Maria Gomez", (loginResult as AuthResult.Success).user.fullName)
    }

    @Test
    fun testRegisterDuplicateEmailFails() {
        val registered = authService.registerUser("Otro Admin", "admin@sena.edu.co", "sena1234")
        assertFalse(registered)
    }

    @Test
    fun testResetPasswordSuccess() {
        val reset = authService.resetPassword("admin@sena.edu.co", "new_pass_999")
        assertTrue(reset)

        // Old password should fail
        val oldResult = authService.validateCredentials("admin@sena.edu.co", "sena1234")
        assertEquals(AuthResult.IncorrectPassword, oldResult)

        // New password should succeed
        val newResult = authService.validateCredentials("admin@sena.edu.co", "new_pass_999")
        assertTrue(newResult is AuthResult.Success)
    }

    @Test
    fun testResetPasswordUnregisteredEmailFails() {
        val reset = authService.resetPassword("fantasma@sena.edu.co", "pass123")
        assertFalse(reset)
    }
}
