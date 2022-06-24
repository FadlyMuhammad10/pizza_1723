package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //hide title bar
        getSupportActionBar()?.hide()

        //instance text
        val txtEmail: EditText = findViewById(R.id.registerEmail)
        val txtUsername: EditText = findViewById(R.id.registerPersonName)
        val txtLevel: EditText = findViewById(R.id.registerLevel)
        val txtPassword: EditText = findViewById(R.id.registerPassword )

        //instance button register
        val btnRegister: Button = findViewById(R.id.buttonRegister)

        //event button register
        btnRegister.setOnClickListener{

            //object class databaseHelper
            val databaseHelper = DatabaseHelper(this)

            val email = txtEmail.text.toString().trim()
            val name = txtUsername.text.toString().trim()
            val level = txtLevel.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            //check data
            val data:String = databaseHelper.checkData(email)
            if(data == null){
                //insert data
                databaseHelper.addAccount(email,
                    name, level, password)
                //show LoginActivity
                val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                startActivity(intentLogin)
            } else {
                //jika email sudah terdaftar
                Toast.makeText(this@RegisterActivity, "Register Failed." + "Your email already registered",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }
}