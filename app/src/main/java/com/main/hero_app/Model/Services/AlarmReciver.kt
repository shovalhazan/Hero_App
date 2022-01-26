package com.main.hero_app.Model.Services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.bumptech.glide.Glide

class AlarmReciver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p0 != null) {
            Glide.get(p0).clearMemory()
        };
    }
}