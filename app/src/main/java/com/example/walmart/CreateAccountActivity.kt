package com.example.walmart

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CreateAccountActivity : AppCompatActivity() {
    private lateinit var firstNameET : EditText
    private lateinit var lastNameET : EditText
    private lateinit var passwordET : EditText
    private lateinit var emailET : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        var createAccButton : Button = findViewById(R.id.createAccButton)
        firstNameET = findViewById(R.id.firstNameET)
        lastNameET = findViewById(R.id.lastNameET)
        passwordET = findViewById(R.id.passwordET)
        emailET = findViewById(R.id.emailET)
        createAccButton.setOnClickListener {
            if(!firstNameET.text.toString().equals("") &&
                !lastNameET.text.toString().equals("") &&
                !passwordET.text.toString().equals("") &&
                !emailET.text.toString().equals("") ){

                val newUser = User(firstNameET.text.toString(),lastNameET.text.toString(),emailET.text.toString(),passwordET.text.toString())
                val rintent = intent
                rintent.putExtra("newUser", newUser)
                setResult(Activity.RESULT_OK,rintent)
                finish()
            }
            else{
                Toast.makeText(this, "Please fill all the fields required!", Toast.LENGTH_SHORT).show()
            }
        }




    }
}