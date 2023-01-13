package com.example.finalproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.finalproject.R
import com.example.finalproject.ToDoData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class addItemFragment : DialogFragment() {
    private lateinit var database : DatabaseReference
    private val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lateinit var database : DatabaseReference
        val auth = FirebaseAuth.getInstance()
        database = Firebase.database.getReference("users")
        val currentuser = auth.currentUser?.uid!!

        super.onViewCreated(view, savedInstanceState)

        // ცვლადები

        val saveBtn = view.findViewById<Button>(R.id.savebtn)
        val toDoItem = view.findViewById<EditText>(R.id.toDoItem)
        //----------------------------------------------------------------------------








        // ამატებს tasks დათაბეიზში
        saveBtn.setOnClickListener{
            val toDoItemText = toDoItem.text.toString()


            database.child(currentuser).push().setValue(toDoItemText)
                .addOnSuccessListener {
                    // Write was successful!
                    // ...
                    Toast.makeText(context,"succesfully added",Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                .addOnFailureListener {
                    // Write failed
                    // ...
                    Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show()
                }



        }
        // ----------------------------------------------------------


    }

}