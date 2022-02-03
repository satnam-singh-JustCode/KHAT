package com.example.pnchayt

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Vibrator
import android.text.method.TextKeyListener.clear
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pnchayt.databinding.ActivityChatAnonymousBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.RepoManager.clear
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.scottyab.aescrypt.AESCrypt

class ChatAnonymous : AppCompatActivity() {
    private lateinit var binding: ActivityChatAnonymousBinding
    private lateinit var msgadapter: messageadapter
    private lateinit var messagelist: ArrayList<sent>
    var recieverroom: String?=null
    var senderroom: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatAnonymousBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = intent.getStringExtra("name")
        var recieverUid = intent.getStringExtra("uid")


        window.setStatusBarColor(ContextCompat.getColor(this,R.color.whatsapgrey));


        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1f2c34")))
        supportActionBar!!.title = name


        messagelist = ArrayList()
        msgadapter = messageadapter(this,messagelist)


        binding.crv.layoutManager = LinearLayoutManager(this)
        binding.crv.adapter = msgadapter



        var senderuid = FirebaseAuth.getInstance().currentUser?.uid
        senderroom = recieverUid + senderuid
        recieverroom = senderuid + recieverUid


//         logic for adding data to recyclerview
            var db = Firebase.database.getReference()
            db.child("chats").child(senderroom!!).child("messages").addValueEventListener(object : ValueEventListener{
              override fun onDataChange(snapshot: DataSnapshot) {
                 messagelist.clear()
                 for( x in snapshot.children){
                     var messages = x.getValue(sent::class.java)
                     var messgagegee = messages?.msg  // taking out msg from the array

                     // Decrypting the msg
                     var key = "#$@%@*&^$%%#%%#^^&&@%@%@&&"


                     var decryptedmsg = AESCrypt.decrypt(key,messgagegee)

                     // Decrypting the msg

                     messages?.msg=decryptedmsg.toString()


                     messagelist.add(messages!!) }
                     msgadapter.notifyDataSetChanged()}
                override fun onCancelled(error: DatabaseError) {}   })

          // Adding message to database.
        binding.cv.setOnClickListener {
            var key = "#$@%@*&^$%%#%%#^^&&@%@%@&&"
            var db = Firebase.database.getReference()
            var message = binding.email.editText!!.text.toString()

            // Encrypting the msg


            var encryptedmsg = AESCrypt.encrypt(key,message)


            // Encrypting the msg
            var messageObject = sent(encryptedmsg,senderuid)
            db.child("chats").child(senderroom!!).child("messages").push().setValue(messageObject).addOnSuccessListener {
                db.child("chats").child(recieverroom!!).child("messages").push().setValue(messageObject)
            }
            binding.email.editText!!.setText("")
        }
    }
}

