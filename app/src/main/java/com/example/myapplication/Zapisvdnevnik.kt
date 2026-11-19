package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.Data.DataBase
import com.example.myapplication.Data.Medic
import com.example.myapplication.Data.Medic_Helper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Zapisvdnevnik : AppCompatActivity() {
    private lateinit var createbutton : AppCompatButton
    private lateinit var savebutton : AppCompatButton
    private lateinit var deletebutton : AppCompatButton
    private lateinit var searchbutton : AppCompatButton

    private lateinit var timeedittext : AppCompatEditText
    private lateinit var tempedittext : AppCompatEditText
    private lateinit var davedittext : AppCompatEditText
    private lateinit var idsearch : AppCompatEditText
    private lateinit var thisobj : Medic
    private lateinit var dao: Medic_Helper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zapisvdnevnik)
        thisobj = Medic(id = -1, time="_",temp = 0.1,dav=0.1);
        createbutton = findViewById(R.id.createbutton)
        savebutton = findViewById(R.id.savebutton)
        deletebutton = findViewById(R.id.deletebutton)
        searchbutton = findViewById(R.id.search_button)
        timeedittext = findViewById(R.id.timeedittext)
        tempedittext = findViewById(R.id.tempedittext)
        davedittext = findViewById(R.id.davedittext)
        idsearch = findViewById(R.id.idsearch)
        dao = DataBase.getInstance(this).medic_Helper()

         createbutton.setOnClickListener{
            if(tempedittext.text.toString().length == 0 || davedittext.text.toString().length == 0)
            {
                showAlert("Пустые строки","Ошибка")
                return@setOnClickListener
            }
            var text1 = LocalDateTime.now().toString();
            var text2 = tempedittext.text.toString().toDouble();
            var text3 = davedittext.text.toString().toDouble();
            lifecycleScope.launch {
                withContext(Dispatchers.IO)
                {
                    dao.insert(Medic(time = text1, temp = text2, dav = text3))
                }
            }
            showAlert("Запись в дневник успешно создана", "Выполнено")
            timeedittext.setText("")
            tempedittext.setText("")
            davedittext.setText("")

        }
        searchbutton.setOnClickListener{
            if(idsearch.text.toString().length == 0)
            {
                showAlert("Пустая строка ID","Ошибка")
                return@setOnClickListener
            }
            lifecycleScope.launch {
                var obj = withContext(Dispatchers.IO) {dao.getById(idsearch.text.toString().toLong())}
                if(obj != null) {
                    timeedittext.setText(obj.time)
                    tempedittext.setText(obj.temp.toString())
                    davedittext.setText(obj.dav.toString())
                    showAlert("Данные успешно загружены", "Выполнено")
                    thisobj = obj;
                }
                else
                {
                    showAlert("Записи с данным ID нету", "Ошибка")
                }
            }
        }
        deletebutton.setOnClickListener{
            if(thisobj.id != -1.toLong())
            {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO){
                        dao.delete(thisobj)
                    }
                }
                showAlert("Данные успешно удаленны", "Выполнено")
                thisobj = Medic(id = -1, time="_",temp = 0.1,dav=0.1);
                timeedittext.setText("")
                tempedittext.setText("")
                davedittext.setText("")
            }
        }
        savebutton.setOnClickListener{
            if(timeedittext.text.toString().length == 0 || tempedittext.text.toString().length == 0 || davedittext.text.toString().length == 0)
            {
                showAlert("Пустые строки","Ошибка")
                return@setOnClickListener
            }
            if(thisobj.id != -1.toLong())
            {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        dao.update(
                            Medic(
                                thisobj.id,
                                timeedittext.text.toString(),
                                tempedittext.text.toString().toDouble(),
                                davedittext.text.toString().toDouble()
                            )
                        )
                    }
                }


            }
        }




    }
    fun showAlert(message: String,message2:String) {
        AlertDialog.Builder(this)
            .setTitle(message2)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}
