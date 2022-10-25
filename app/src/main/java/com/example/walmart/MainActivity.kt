package com.example.walmart

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var email: EditText
    private lateinit var password: EditText
    var listOfUsers = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result->

            if(result.resultCode == Activity.RESULT_OK){
                val data: Intent? = result.data
                val user = data?.getSerializableExtra("newUser") as User?
                println(user?.firstName)
                println(user?.lastName)
                println(user?.username)
                println(user?.password)

                if(user!=null){
                    listOfUsers.add(user)
                    Toast.makeText(this, "User ${user.username}  has been registered", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this, "Account could not be created", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Failed to get result.", Toast.LENGTH_SHORT).show()
            }
        }


        listOfUsers.add(User("Ugur Can", "Guleviz","ugurcan.guleviz@gmail.com","12345"))
        listOfUsers.add(User("Hasan Burak", "Namli","hasan.namli@gmail.com","54321"))
        listOfUsers.add(User("Tugbanur", "Yucel","tugbanur.yucel@gmail.com","11111"))
        listOfUsers.add(User("Gizem", "Ozmen","gizem.ozmen@gmail.com","22222"))
        listOfUsers.add(User("Alper", "Sirin","alper.sirin@gmail.com","33333"))

        var signInButton : Button = findViewById(R.id.activity_main_bt_signIn)
        var createAccButton : Button = findViewById(R.id.activity_main_bt_createAcc)
        var forgetPass : TextView = findViewById(R.id.activity_main_tv_forgotPass)

        signInButton.setOnClickListener { signInClick() }
        createAccButton.setOnClickListener {
            val intent = Intent(this,CreateAccountActivity::class.java)
            resultContracts.launch(intent)
        }
        forgetPass.setOnClickListener { forgetPassClick() }
        email = findViewById(R.id.activity_main_et_mailbox)
        password = findViewById(R.id.activity_main_et_password)
    }

    private fun signInClick(){
        var signedIn = false;
        for(u : User in listOfUsers){
            if(email.text.toString().equals(u.username) && password.text.toString().equals(u.password)){
                val intent = Intent(this,ShoppingCategoryActivity::class.java)
                intent.putExtra("username", u.username)
                signedIn = true;
                startActivity(intent)
            }
        }
        if(!signedIn)
            Toast.makeText(this, "Try again to sign-in, or create a new account.", Toast.LENGTH_SHORT).show()
    }
    private fun forgetPassClick(){
        val username = email.text.toString()
        val user = listOfUsers.find { it.username == username }
        if (user != null) {
            val mailIntent = Intent(Intent.ACTION_SENDTO)
            mailIntent.data = Uri.parse("mailto:")
            mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user.username))
            mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password")
            mailIntent.putExtra(Intent.EXTRA_TEXT, "Your password is ${user.password}")
            startActivity(mailIntent)
        }else{
            Toast.makeText(this, "No such user with email found", Toast.LENGTH_SHORT).show()
        }

    }

}