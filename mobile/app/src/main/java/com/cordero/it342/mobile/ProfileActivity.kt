package com.cordero.it342.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private lateinit var session: SessionManager
    private val userApi = ApiClient.retrofit.create(UserApi::class.java)
    private val authApi = ApiClient.retrofit.create(AuthApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        session = SessionManager(this)
        val token = session.getToken()
        if (token == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        val nameText = findViewById<TextView>(R.id.textName)
        val emailText = findViewById<TextView>(R.id.textEmail)
        val logoutBtn = findViewById<Button>(R.id.btnLogout)

        lifecycleScope.launch {
            try {
                val me = userApi.me("Bearer $token")
                nameText.text = "${me.firstName} ${me.lastName}"
                emailText.text = me.email
            } catch (e: Exception) {
                Toast.makeText(this@ProfileActivity, "Failed to load profile", Toast.LENGTH_SHORT).show()
            }
        }

        logoutBtn.setOnClickListener {
            lifecycleScope.launch {
                try {
                    authApi.logout("Bearer $token")
                } catch (_: Exception) {
                } finally {
                    session.clear()
                    startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}
