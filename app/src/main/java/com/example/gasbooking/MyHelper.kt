package com.example.gasbooking

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Myhelper (context: Context): SQLiteOpenHelper(context,"BOOKGAS",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CUSTOMER(_id integer primary key autoincrement,NAME TEXT,AGE INTEGER,PHONE INTEGER,ADDRESS TEXT,PASSWORD TEXT)")
        db?.execSQL("Insert into CUSTOMER(name,age,phone,address,password) values('prats',20,9523,'bihar','123')")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}