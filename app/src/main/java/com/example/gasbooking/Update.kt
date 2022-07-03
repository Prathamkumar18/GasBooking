package com.example.gasbooking

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        var upass = findViewById<EditText>(R.id.upass)
        var uid = findViewById<EditText>(R.id.uid)
        var ubtn = findViewById<Button>(R.id.ubtn)
        ubtn.setOnClickListener {
            var uidd=uid.text.toString()
            var upasss=upass.text.toString()
            var helper = Myhelper(applicationContext)
            var db = helper.writableDatabase
            var rs = db.rawQuery("SELECT * FROM CUSTOMER WHERE _id='$uidd'", null)
            if(rs.count>0){
                var cv= ContentValues()
                cv.put("PASSWORD",upasss)
                db.update("CUSTOMER",cv,"_id = ?", arrayOf(uidd))
                rs.requery()
                var ad= AlertDialog.Builder(this)
                ad.setTitle("update Record")
                ad.setMessage("Password updated succesfully")
                ad.setPositiveButton("OK",null)
                ad.show()
        }
        else{
                Toast.makeText(this,"Invalid c.id", Toast.LENGTH_SHORT).show()
            }
        }
        var dbtn=findViewById<Button>(R.id.dbtn)
        dbtn.setOnClickListener {
            var uidd=uid.text.toString()
            if(uidd.equals("")){
                Toast.makeText(this,"Enter c.id",Toast.LENGTH_SHORT).show()
            }
            else{
                var helper = Myhelper(applicationContext)
                var db = helper.writableDatabase
                var rs = db.rawQuery("SELECT * FROM CUSTOMER WHERE _id='$uidd'", null)
                db.delete("CUSTOMER","_id =?",arrayOf(uidd))
                rs.requery()
                Toast.makeText(this,"Account Deleted Succesfully",Toast.LENGTH_SHORT).show()
               var intent= Intent(this,Login::class.java)
                startActivity(intent)
            }
        }
    }
}



