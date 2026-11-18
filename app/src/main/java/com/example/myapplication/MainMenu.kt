package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import java.sql.DatabaseMetaData

class MainMenu : AppCompatActivity() {
    private lateinit var buttonD : AppCompatButton
    private lateinit var buttonS : AppCompatButton
    private lateinit var buttonL : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        buttonD = findViewById(R.id.buttonDnevnik)
        buttonS = findViewById(R.id.buttonStatic)
        buttonL = findViewById(R.id.buttonLekar)
        buttonD.setOnClickListener{
            startActivity(Intent(this,Dnevnik::class.java))
        }
        buttonS.setOnClickListener{
            startActivity(Intent(this,Zapisvdnevnik::class.java))
        }
        buttonL.setOnClickListener{
            startActivity(Intent(this,Profile::class.java))
        }
    }
}