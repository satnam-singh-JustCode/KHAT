package com.example.pnchayt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pnchayt.databinding.ActivityLoginBinding
import com.example.pnchayt.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class signup : AppCompatActivity() {


    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        supportActionBar?.hide()


        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


            binding.progressBar2.visibility = View.INVISIBLE


            binding.buttonlogin.setOnClickListener {
            binding.progressBar2.visibility = View.VISIBLE
            auth  = Firebase.auth
            var emails=binding.es.editText?.text.toString()
            var names=binding.name.editText?.text.toString()
            var pass = binding.ps.editText?.text.toString()
            if (emails.isEmpty()) {
                var toast = Toast.makeText(this,"empty email signup's",Toast.LENGTH_SHORT)
                toast.show()
                binding.progressBar2.visibility = View.INVISIBLE
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                var toast = Toast.makeText(this,"empty passss signuppp",Toast.LENGTH_SHORT)
                toast.show()
                binding.progressBar2.visibility = View.INVISIBLE
                return@setOnClickListener
            }
            if (pass.length < 7) {
                var toast = Toast.makeText(this,"empty passss signuppp",Toast.LENGTH_SHORT)
                toast.show()
                binding.progressBar2.visibility = View.INVISIBLE
                return@setOnClickListener
            }
            if (pass.length > 8) {
                var toast = Toast.makeText(this,"empty passss signuppp",Toast.LENGTH_SHORT)
                toast.show()
                binding.progressBar2.visibility = View.INVISIBLE
                return@setOnClickListener
            }
            if (binding.password2.editText?.text.toString() != pass) {
                var toast = Toast.makeText(this,"empty passss signuppp",Toast.LENGTH_SHORT)
                toast.show()
                binding.progressBar2.visibility = View.INVISIBLE
                return@setOnClickListener
            }
            else{
                auth.createUserWithEmailAndPassword(emails, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // storing to database
                            var uid = auth.currentUser?.uid!!
                        val database = Firebase.database
                        val myRef = database.getReference()
                        myRef.child("users").child(uid).setValue(Userlist(names,emails,uid))
                        // storing to database
                        var toast = Toast.makeText(this,"successful signup",Toast.LENGTH_SHORT)
                        toast.show()
                        binding.progressBar2.visibility = View.INVISIBLE
                        startActivity(Intent(this, Login::class.java))
                        finish()

                    } else {
                        var toast = Toast.makeText(this,"error signuppp",Toast.LENGTH_SHORT)
                        toast.show()
                        binding.progressBar2.visibility = View.INVISIBLE
                    }
                }}

        }
    }

    fun switch_login(view: android.view.View) {
        startActivity(Intent(this, Login::class.java))
        finish()
    }
}