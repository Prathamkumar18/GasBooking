package com.example.gasbooking

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import soup.neumorphism.NeumorphCardView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cardname=findViewById<TextView>(R.id.tvcardname)
        var cardage=findViewById<TextView>(R.id.tvcardage)
        var cardadd=findViewById<TextView>(R.id.tvcardadd)
        var cardphone=findViewById<TextView>(R.id.cardphone)
        var cardid=findViewById<TextView>(R.id.cardid)
        cardname.movementMethod = ScrollingMovementMethod()

        var card=findViewById<NeumorphCardView>(R.id.card)
        cardphone.movementMethod = ScrollingMovementMethod()
        cardadd.movementMethod = ScrollingMovementMethod()

        val intent = intent
        val uname = intent.extras!!.getString("u_name")
        val pass=intent.extras!!.getString("p_word")

        var helper = Myhelper(applicationContext)
        var db = helper.writableDatabase
        var rs = db.rawQuery("SELECT * FROM CUSTOMER WHERE NAME='$uname'and PASSWORD='$pass'",null)
        if (rs.moveToFirst()) {
            cardname.text ="NAME -"+"  "+ rs.getString(1).toUpperCase();
            cardid.text="ID -"+"  "+ rs.getString(0)+"-"+rs.getString(2)+"9999";
            cardage.text ="AGE -"+"  "+ rs.getString(2);
            cardadd.text="ADDRESS -"+"  "+rs.getString(4);
            cardphone.text="PHONE NO. -"+"  "+ rs.getString(3);
        }

        var c1=findViewById<CardView>(R.id.c1)
        c1.setOnClickListener {
            var i=Intent(this,BookActivity::class.java)
            startActivity(i)
        }

        var c2=findViewById<CardView>(R.id.c2)
        c2.setOnClickListener{
            var i=Intent(this,Update::class.java)
            startActivity(i)
        }

        var c3=findViewById<CardView>(R.id.c3)
        c3.setOnClickListener {
            var i3=Intent(this,faqs::class.java)
            startActivity(i3)
        }

        //POP UP MENU
        var c4=findViewById<CardView>(R.id.c4)
        c4.setOnClickListener {
            val popupMenu= PopupMenu(this,c4)
            popupMenu.menuInflater.inflate(R.menu.menu_item,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.contact1 ->{
                        var intent1=Intent(Intent.ACTION_DIAL)
                        intent1.data = Uri.parse("tel:9523611582")
                        startActivity(intent1)
                    }
                    R.id.contact2 ->{
                        var e=Uri.fromParts("mailto","prathamkr1188@gmail.com",null)
                        var intent2=Intent(Intent.ACTION_SENDTO,e)
                        startActivity(Intent.createChooser(intent2,"Send Email"))
                    }
                    R.id.contact31 ->{
                        var m=Uri.parse("geo: 12.955546 77.541170")
                        var intent3=Intent(Intent.ACTION_VIEW,m)
                        startActivity(intent3)
                    }
                    R.id.contact32 ->{
                        var m=Uri.parse("geo: 12.935145853058376, 77.5606348201274")
                        var intent3=Intent(Intent.ACTION_VIEW,m)
                        startActivity(intent3)
                    }

                }
                true
            })
            popupMenu.show()
        }

    }
}