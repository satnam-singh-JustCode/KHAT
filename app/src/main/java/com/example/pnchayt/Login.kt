package com.example.pnchayt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pnchayt.databinding.ActivityLoginBinding
import com.example.pnchayt.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lateinit var auth: FirebaseAuth
        binding.textView.setOnClickListener {
            startActivity(Intent(this, signup::class.java))
            finish()
        }
        binding.buttonlogin.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            auth = Firebase.auth
            var email = binding.emaillogin.editText?.text.toString()
            var pass = binding.password1.editText?.text.toString()
            if (email.isEmpty()) {
                var toast = Toast.makeText(applicationContext, "Email Required!!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.INVISIBLE
                return@setOnClickListener
            }
            if (pass.isEmpty()) {
                var toast = Toast.makeText(applicationContext, "Password Required!!", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.INVISIBLE
                return@setOnClickListener
            }
            if (pass.length < 7) {
                var toast = Toast.makeText(applicationContext, "Seven character needed", Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility = View.INVISIBLE
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this,hellouser::class.java)
                        intent.putExtra("name",email)
                        startActivity(intent)
                        finish()
                    } else {
                        val toast = Toast.makeText(this, "Error", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
        }
    }


}