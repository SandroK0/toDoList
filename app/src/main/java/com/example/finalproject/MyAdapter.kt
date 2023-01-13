package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase



// ეს ადაპტერი რესაიქლ ვიუს ჭირდება ბოლომდე მეც ვერ გავიგე როგორ მუშაობს
class MyAdapter(private val toDoList : MutableList<ToDoData>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    //Firebase

    val auth = FirebaseAuth.getInstance()
    val currentuser = auth.currentUser?.uid!!

    var database = Firebase.database.getReference("users").child(currentuser)
    //-------------------------------


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)

//        itemView.setOnClickListener{
//            val context = parent.context as FragmentActivity
//            val showPopUp = addItemFragment()
//            showPopUp.show(context.supportFragmentManager, "showPopUp")
//        }


        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = toDoList[position]





        holder.toDoItem.text = currentitem.task



        val deleteTask = holder.itemView.findViewById<ImageView>(R.id.deleteTask)
//        val editBtn = holder.itemView.findViewById<ImageView>(R.id.editTask)

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
