package com.cordero.it342.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var session: SessionManager
    private val authApi = ApiClient.retrofit.create(AuthApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        session = SessionManager(this)

        val emailField = findViewById<EditText>(R.id.inputEmail)
        val passwordField = findViewById<EditText>(R.id.inputPassword)
        val confirmField = findViewById<EditText>(R.id.inputConfirm)
        val firstNameField = findViewById<EditText>(R.id.inputFirstName)
        val lastNameField = findViewById<EditText>(R.id.inputLastName)
        val registerBtn = findViewById<Button>(R.id.btnRegister)
        val linkLogin = findViewById<TextView>(R.id.linkLogin)

        registerBtn.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString()
            val confirm = confirmField.text.toString()
            val firstName = firstNameField.text.toString().trim()
            val lastName = lastNameField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty() || confirm.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 8) {
                Toast.makeText(this, "Password must be at least 8 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password != confirm) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {
                    val res = authApi.register(RegisterRequest(email, password, confirm, firstName, lastName))
                    session.saveToken(res.token)
                    startActivity(Intent(this@RegisterActivity, ProfileActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@RegisterActivity, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        linkLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
