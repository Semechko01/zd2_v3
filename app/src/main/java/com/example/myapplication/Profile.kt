package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.google.gson.Gson

class Profile : AppCompatActivity() {
    private lateinit var pref : SharedPreferences
    private lateinit var textlogin : TextView
    private lateinit var textpassword : TextView
    private lateinit var buttonexit : AppCompatButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        pref = getSharedPreferences("user", Context.MODE_PRIVATE)
        var json = Gson().fromJson(pref.getString("userdata",null),User::class.java)
        textlogin = findViewById(R.id.logintext)
        textpassword = findViewById(R.id.passwordtext)
        buttonexit = findViewById(R.id.buttonleave)
        textlogin.setText(json.name.toString())
        textpassword.setText(json.password.toString())
        buttonexit.setOnClickListener{
            var user = User("","",false)
            var jsons = Gson().toJson(user)
            pref.edit().putString("userdata",jsons).apply()
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}