package com.example.finalproject.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.auth.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ChangePassword : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //Firebase

        val auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser?.uid!!

        var database = Firebase.database.getReference("users").child(currentuser)
        //-------------------------------


        val changePasswordButton = view.findViewById<Button>(R.id.changeBtn)
        val changePasswordCurrentPasswordEditText = view.findViewById<EditText>(R.id.etCurrentPass)
        val changePasswordNewPasswordEditText = view.findViewById<EditText>(R.id.etNewPass)
        val backBtn = view.findViewById<Button>(R.id.backBtn)






        changePasswordButton.setOnClickListener {
            val currentPassword = changePasswordCurrentPasswordEditText.text.toString()
            val newPassword = changePasswordNewPasswordEditText.text.toString()
            if (newPassword == currentPassword) {
                Toast.makeText(context, "use other password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (newPassword.isEmpty()) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "password updated", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context, MainActivity::class.java))
                } else {
                    Toast.makeText(context, "something's wrong", Toast.LENGTH_SHORT).show()
                }
            }





        }

        backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_changePassword_to_mainFragment)
        }


    }
}