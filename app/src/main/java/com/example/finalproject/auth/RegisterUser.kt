package com.example.finalproject.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.finalproject.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        //Variables
        val auth = Firebase.auth

        val tvBackToLogin = findViewById<TextView>(R.id.tvBackToLogin)
        val etEmailReg = findViewById<EditText>(R.id.etEmailReg)
        val etPasswordReg = findViewById<EditText>(R.id.etPasswordReg)
        val etPasswordRegRep = findViewById<EditText>(R.id.etPasswordRegRep)
        val bRegister = findViewById<Button>(R.id.bRegister)


        //Registration
        bRegister.setOnClickListener {

            val email = etEmailReg.text.toString()
            val password = etPasswordReg.text.toString()
            val repeatPassword = etPasswordRegRep.text.toString()

            if(repeatPassword!=password){
                Toast.makeText(this, "wrong password!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Something is missing!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (password.length < 7 || password.contains(" ")){
                Toast.makeText(this,"Password is too short or containts 'space' character!",Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "congrats! now login!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                    } else {
                        Toast.makeText(this, "something's wrong, try again", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }







        tvBackToLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}