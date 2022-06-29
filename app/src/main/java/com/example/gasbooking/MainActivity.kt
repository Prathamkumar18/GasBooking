package com.example.gasbooking

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cardname=findViewById<TextView>(R.id.tvcardname)
        var cardage=findViewById<TextView>(R.id.tvcardage)
        var cardadd=findViewById<TextView>(R.id.tvcardadd)
        var cardphone=findViewById<TextView>(R.id.cardphone)
        var cardid=findViewById<TextView>(R.id.cardid)

        val intent = intent
        val uname = intent.extras!!.getString("u_name")
        val pass=intent.extras!!.getString("p_word")

        var helper = Myhelper(applicationContext)
        var db = helper.writableDatabase
        var rs = db.rawQuery("SELECT * FROM CUSTOMER WHERE NAME='$uname'and PASSWORD='$pass'",null)
        if (rs.moveToFirst()) {
            cardname.text ="NAME -"+"  "+ rs.getString(1).toUpperCase();
            cardid.text="ID -"+"  "+ rs.getString(0)+rs.getString(2)+"9999";
            cardage.text ="AGE -"+"  "+ rs.getString(2);
            cardadd.text="ADDRESS -"+"  "+rs.getString(4);
            cardphone.text="PHONE NO. -"+"  "+ rs.getString(3);
        }

    }
}