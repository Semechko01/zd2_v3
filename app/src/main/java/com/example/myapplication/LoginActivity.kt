package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    private lateinit var button : AppCompatButton
    private lateinit var editText: AppCompatEditText
    private lateinit var editText2 : AppCompatEditText
    private lateinit var  pref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        pref = getSharedPreferences("user", Context.MODE_PRIVATE)
        if(pref.getString("userdata",null) != null) {
            var user = Gson().fromJson(pref.getString("userdata", null), User::class.java)
            if (user.exit) {
                startActivity(Intent(this, MainMenu::class.java))
            }
        }
        button = findViewById(R.id.buttonlogin)
        editText = findViewById(R.id.edittextlogin)
        editText2 = findViewById(R.id.edittextpassword)
        button.setOnClickListener{
            if(editText.text.toString().length == 0 || editText2.text.toString().length == 0)
            {
                showAlert("Пустые строки");
                return@setOnClickListener
            }
            var user = User(editText.text.toString(),editText2.text.toString(),true)
            var json = Gson().toJson(user)
            pref.edit().putString("userdata", json).apply()
            startActivity(Intent(this,MainMenu::class.java))

        }
    }
    fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}