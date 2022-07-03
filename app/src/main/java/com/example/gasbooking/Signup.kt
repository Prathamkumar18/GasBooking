package com.example.gasbooking

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
            var n=etrname.text.toString()
            var a=etrage.text.toString()
            var p=etrphone.text.toString()
            var add=etraddress.text.toString()
            var pass= etrpassword.text.toString()
            if(n == "")etrname.error="Enter Name"
            else if(a=="") etrage.error="Enter Age"
            else if(Integer.parseInt(a)<18) etrage.error="Age should not be <18"
            else if(p=="") etrphone.error="Enter Phone No."
            else if(add=="") etraddress.error="Enter Address"
            else if(pass=="") etrpassword.error="Enter password"
            else{
                var cv = ContentValues()
                cv.put("NAME",n)
                cv.put("AGE",a.toInt())
                cv.put("PHONE",p.toInt())
                cv.put("ADDRESS", add)
                cv.put("PASSWORD",pass)
                db.insert("CUSTOMER", null, cv)
                rs.requery()
                Toast.makeText(this, "Registered Succesfully", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }
    }
}

