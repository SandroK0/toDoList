

package com.example.finalproject.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import com.example.finalproject.Profile
import com.example.finalproject.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Variables
        val auth = Firebase.auth

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val bLogin = findViewById<Button>(R.id.bLogin)
        val tvRegister = findViewById<TextView>(R.id.tvResgister)
        val tvForgotPassword = findViewById<TextView>(R.id.tvForgotPassword)
//        val googleBtn = findViewById<ImageButton>(R.id.googleBtn)


        //
        //LOGIN
        //
        bLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "email or password is empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task->
                if (task.isSuccessful){
                    startActivity(Intent(this, Profile::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "something's wrong", Toast.LENGTH_SHORT).show()
                }
            }




        }




        tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
        }
        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterUser::class.java))
        }





    }

    override fun onBackPressed() {
        super.finish()
    }
}