package com.example.gasbooking

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var helper = Myhelper(applicationContext)
        var db = helper.writableDatabase
        var rs = db.rawQuery("SELECT * FROM CUSTOMER", null)
        var etrname = findViewById<EditText>(R.id.etrname)
        var etrage = findViewById<EditText>(R.id.etrage)
        var btnregister = findViewById<Button>(R.id.btnregister)
        var etrphone = findViewById<EditText>(R.id.etrphone)
        var etraddress = findViewById<EditText>(R.id.etraddress)
        var etrpassword = findViewById<EditText>(R.id.etrpassword)
        btnregister.setOnClickListener {
            var cv = ContentValues()
            cv.put("NAME", etrname.text.toString())
            cv.put("AGE", etrage.text.toString().toInt())
            cv.put("PHONE", etrphone.text.toString().toInt())
            cv.put("ADDRESS", etraddress.text.toString())
            cv.put("PASSWORD", etrpassword.text.toString())
            db.insert("CUSTOMER", null, cv)
            rs.requery()
            Toast.makeText(this, "Registered Succesfully", Toast.LENGTH_SHORT).show()
            var intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}

