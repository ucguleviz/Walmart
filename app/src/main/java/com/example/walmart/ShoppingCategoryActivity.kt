package com.example.walmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class ShoppingCategoryActivity : AppCompatActivity() {
    private lateinit var usernameTV: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_category)

        var intent =getIntent()
        var username = intent.getStringExtra("username")
        usernameTV = findViewById(R.id.activity_shopping_category_tv_email)
        usernameTV.setText(username)
        var electronicsIB : ImageButton = findViewById(R.id.activity_shopping_category_ib_electronics)
        var clothingIB : ImageButton = findViewById(R.id.activity_shopping_category_ib_clothing)
        var beautyIB : ImageButton = findViewById(R.id.activity_shopping_category_ib_beauty)
        var foodIB : ImageButton = findViewById(R.id.activity_shopping_category_ib_food)

        electronicsIB.setOnClickListener{
            Toast.makeText(this, "You are going to be directed to Electronics category", Toast.LENGTH_SHORT).show()
            println("eet")
        }
        clothingIB.setOnClickListener{
            Toast.makeText(this, "You are going to be directed to Clothing category", Toast.LENGTH_SHORT).show()
        }
        beautyIB.setOnClickListener{
            Toast.makeText(this, "You are going to be directed to Beauty category", Toast.LENGTH_SHORT).show()
        }
        foodIB.setOnClickListener{
            Toast.makeText(this, "You are going to be directed to Food category", Toast.LENGTH_SHORT).show()
        }
    }
}