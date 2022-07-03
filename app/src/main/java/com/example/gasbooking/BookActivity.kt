package com.example.gasbooking

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class BookActivity : AppCompatActivity() {

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        var etupi=findViewById<EditText>(R.id.etupi)
        var upi=findViewById<RadioButton>(R.id.rg21)
        var cod=findViewById<RadioButton>(R.id.rg20)
        //Marquee Code here
        var marquee=findViewById<TextView>(R.id.marqueeText)
        marquee.isSelected = true;
        cod.setOnClickListener{
            etupi.visibility=View.GONE
        }
        upi.setOnClickListener{
            etupi.visibility= View.VISIBLE
        }

        //Notification
        var placeorder=findViewById<Button>(R.id.placeorder)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        placeorder.setOnClickListener {
            var et=etupi.text.toString()
            if(upi.isChecked && !et.endsWith("@booktm")){
                Toast.makeText(this,"Invalid UPI",Toast.LENGTH_SHORT).show()
            }
            else{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(this, channelId)
                        .setContentTitle("Booking Status")
                        .setContentText("Gas Booked Successfully")
                        .setSmallIcon(R.drawable.icon)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                } else {
                    builder = Notification.Builder(this)
                        .setContentTitle("Booking Status")
                        .setContentText("Gas Booked Successfully")
                        .setSmallIcon(R.drawable.icon)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                }
                notificationManager.notify(1234, builder.build())
                var intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }

        //Total Amount
        var total=findViewById<TextView>(R.id.price)
        var rg10=findViewById<RadioButton>(R.id.rg10)
        var save=findViewById<Button>(R.id.save)
        var cb0=findViewById<CheckBox>(R.id.cb0)
        var cb1=findViewById<CheckBox>(R.id.cb1)
        var cb2=findViewById<CheckBox>(R.id.cb2)
        var tot=0
        var c=0
        save.setOnClickListener {
            var x=rg10.isChecked.toString()
            if(x == "true") tot+=920
            else tot+=2200
            var c1=cb0.isChecked.toString()
            var c2=cb1.isChecked.toString()
            var c3=cb2.isChecked.toString()
            if(c1=="true") tot+=600
            if(c2=="true") tot+=350
            if(c3=="true") tot+=220
            total.text = "Total Amount: Rs.$tot"
            tot=0
        }
    }
}