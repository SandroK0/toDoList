package com.example.finalproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.MyAdapter
import com.example.finalproject.R
import com.example.finalproject.ToDoData
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.logging.XMLFormatter
import androidx.constraintlayout.widget.ConstraintSet.Constraint as ConstraintlayoutWidgetConstraintSetConstraint
import androidx.constraintlayout.widget.ConstraintSet.Layout as ConstraintSetLayout


class MainFragment : Fragment() {



    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        lateinit var recyclerView: RecyclerView
        lateinit var toDoList: MutableList<ToDoData>

        lateinit var database: DatabaseReference
        val auth = FirebaseAuth.getInstance()
        val currentuser = auth.currentUser?.uid!!

        database = Firebase.database.getReference("users").child(currentuser)




        super.onViewCreated(view, savedInstanceState)

        //Variables
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        toDoList = mutableListOf<ToDoData>()



//        val editBtn =  view.context.resources(view.id.)


        val addbtn = view.findViewById<ImageView>(R.id.addBtn)

        //------------------------------------------------------

        val test = view.findViewById<TextView>(R.id.menuBtn)


        // იღებს Data-ს realtime database-დან  და ათავსებს recycler view-ში
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                toDoList.clear()
                for (taskSnapshot in snapshot.children){



                    val item = taskSnapshot.key?.let {
                        ToDoData(it, taskSnapshot.value.toString())
                    }
                    if (item != null){
                        toDoList.add(item)
                    }




                }
                recyclerView.adapter = MyAdapter(toDoList)




            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"Database Error", Toast.LENGTH_SHORT).show()
            }


        })
        //----------------------------------------------------------------------------






//        test.setOnClickListener {
//            var text = ""
//            if (toDoList.isEmpty()){
//                text = "clear"
//            }else{
//                text = toDoList[0].toString()
//            }
//
//                Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
//        }





        addbtn.setOnClickListener {
            val showPopUp = addItemFragment()
            showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }


    }
}