package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.Data.DataBase
import com.example.myapplication.Data.Medic_Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Dnevnik : AppCompatActivity() {
    private lateinit var dao: Medic_Helper
    private lateinit var lists: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dnevnik)
        dao = DataBase.getInstance(this).medic_Helper()
        lists = findViewById(R.id.dnevniklist)
        lifecycleScope.launch {
            val list = withContext(Dispatchers.IO) { dao.getAll() }
            for (i in list) {
                val textView = TextView(this@Dnevnik).apply {
                    text = "id:" + i.id
                    textSize = 15f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    width = 300
                    height = 50

                }
                val textView2 = TextView(this@Dnevnik).apply {
                    text = "Время:" + i.time
                    textSize = 12f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    width = 300
                    height = 40
                }
                val textView3 = TextView(this@Dnevnik).apply {
                    text = "Температура:" + i.temp
                    textSize = 12f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    width = 300
                    height = 40
                }
                val textView4 = TextView(this@Dnevnik).apply {
                    text = "Давление:" + i.dav
                    textSize = 12f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    width = 300
                    height = 40
                }
                lists.addView(textView)
                lists.addView(textView2)
                lists.addView(textView3)
                lists.addView(textView4)
            }
        }
    }
}