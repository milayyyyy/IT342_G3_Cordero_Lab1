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

class LoginActivity : AppCompatActivity() {
    private lateinit var session: SessionManager
    private val authApi = ApiClient.retrofit.create(AuthApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        session = SessionManager(this)
        if (session.getToken() != null) {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
            return
        }

        val emailField = findViewById<EditText>(R.id.inputEmail)
        val passwordField = findViewById<EditText>(R.id.inputPassword)
        val loginBtn = findViewById<Button>(R.id.btnLogin)
        val linkRegister = findViewById<TextView>(R.id.linkRegister)

        loginBtn.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                try {
                    val res = authApi.login(LoginRequest(email, password))
                    session.saveToken(res.token)
                    startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        linkRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
