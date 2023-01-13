package com.example.finalproject.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.finalproject.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        //Variables
        val auth = Firebase.auth

        val etEmailResPass = findViewById<EditText>(R.id.etEmailResPass)
        val bSend = findViewById<Button>(R.id.bSend)

        bSend.setOnClickListener {
            val email = etEmailResPass.text.toString()

            if (email.isEmpty()){
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "check your email!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                }
            }

        }



    }
}