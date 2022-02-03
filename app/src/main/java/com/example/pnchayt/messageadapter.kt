package com.example.pnchayt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class messageadapter(val context:Context, var messagelist: ArrayList<sent>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECIEVE = 1
    val ITEM_SENT = 2

    class sentmessviewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var sentmsg = itemView.findViewById<TextView>(R.id.textView5)
    }

    class recievemessviewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var recievemsg = itemView.findViewById<TextView>(R.id.textView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==1){
            // iteam recieved
            var view : View = LayoutInflater.from(context).inflate(R.layout.sentlayout,parent,false)
            return recievemessviewholder(view)
        }
        else{
            // item sent
            var view : View = LayoutInflater.from(context).inflate(R.layout.recievemsg,parent,false)
            return sentmessviewholder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var currentmessage = messagelist[position]
        if(holder.javaClass == sentmessviewholder::class.java){
            // for recieving message
            val viewholder = holder as sentmessviewholder
            holder.sentmsg.text = currentmessage.msg
        }else{
            // for sending messages
            val viewholder = holder as recievemessviewholder
            holder.recievemsg.text = currentmessage.msg
        }
    }


    override fun getItemViewType(position: Int): Int {
        var currentMessage = messagelist[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderid)){
            return ITEM_SENT
        }
        else{
            return ITEM_RECIEVE
        }
    }


    override fun getItemCount(): Int {
        return messagelist.size
    }

}