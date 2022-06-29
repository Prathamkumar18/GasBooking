package com.example.gasbooking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var etlname=findViewById<EditText>(R.id.etlname)
        var etpass=findViewById<EditText>(R.id.etlpassword)
        var btnlogin=findViewById<Button>(R.id.btnlogin)
        var tvdont=findViewById<TextView>(R.id.tvdont)

        btnlogin.setOnClickListener {
            var uname=etlname.text.toString()
            var pass=etpass.text.toString()
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("u_name", uname)
            i.putExtra("p_word",pass)
            var helper = Myhelper(applicationContext)
            var db = helper.writableDatabase
            var rs = db.rawQuery("SELECT * FROM CUSTOMER WHERE NAME='"+uname+"'and PASSWORD='"+pass+"'",null)
            if(rs.count>0){
                startActivity(i)
            }
            else{
                Toast.makeText(this,"invalid uname or password",Toast.LENGTH_SHORT).show()
            }
        }
        tvdont.setOnClickListener {
            var intent= Intent(this,Signup::class.java)
            startActivity(intent)
        }
    }

}