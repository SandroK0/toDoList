package com.example.finalproject.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.finalproject.Profile
import com.example.finalproject.R
import com.example.finalproject.auth.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class menuFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //Firebase

        val auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser?.uid!!

        var database = Firebase.database.getReference("users").child(currentuser)
        //-------------------------------




        //variables
        val changepassBtn = view.findViewById<TextView>(R.id.changepassBtn)
        val logoutBtn = view.findViewById<TextView>(R.id.logoutBtn)
        val clear = view.findViewById<TextView>(R.id.clearBtn)

        //--------------



        clear.setOnClickListener {
            database.removeValue()
        }

        logoutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context,MainActivity::class.java))
        }
        changepassBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_changePassword)
            dismiss()
        }



//        Toast.makeText(context,currentuser, Toast.LENGTH_SHORT).show()

    }



}