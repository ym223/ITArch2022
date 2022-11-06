package com.example.aidl_service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemoteService : Service() {

    private val binder = object : IMyAidlInterface.Stub() {
        override fun getMessage(msg: String): String {
            return "Hello World"
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

}