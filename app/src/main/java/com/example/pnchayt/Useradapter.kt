package com.example.pnchayt

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class Useradapter(val context: Context, val userList: ArrayList<Userlist>): RecyclerView.Adapter<Useradapter.viewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var view : View = LayoutInflater.from(context).inflate(R.layout.userlistlayout,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
      var curuser = userList[position]
        holder.name.text = curuser.name

        // intent to chat application

        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatAnonymous::class.java)
             intent.putExtra("name",curuser.name)
            intent.putExtra("uid",curuser.uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
       var name = itemView.findViewById<TextView>(R.id.nameid)
    }

}