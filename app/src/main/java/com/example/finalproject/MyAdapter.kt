package com.example.finalproject

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MyAdapter(private val toDoList : MutableList<ToDoData>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {




        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)








        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //Firebase
        lateinit var database: DatabaseReference
        val auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser?.uid!!

        database = Firebase.database.getReference("users").child(currentuser)
        //-------------------------------


        val currentitem = toDoList[position]
        holder.toDoItem.text = currentitem.task


        val deleteTask = holder.itemView.findViewById<ImageView>(R.id.deleteTask)

        deleteTask.setOnClickListener {
            database.child(currentitem.taskId).removeValue()


        }


    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){



        val toDoItem : TextView = itemView.findViewById(R.id.toDoItem)


    }
}
