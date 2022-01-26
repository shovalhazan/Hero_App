package com.main.hero_app.Model.Services
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        var alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        lateinit var alarmIntent: Intent
        lateinit var padingIntent: PendingIntent

        alarmIntent = Intent(this, AlarmReciver::class.java)
        padingIntent=PendingIntent.getBroadcast(this, 0, alarmIntent, 0)

        alarmManager?.setInexactRepeating (
            AlarmManager.RTC_WAKEUP,
            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HOUR,
            AlarmManager.INTERVAL_DAY,
            padingIntent
        )
    }
}