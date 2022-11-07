package com.example.aidl_service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemoteService : Service() {

    val operations = mapOf<String, String>(
        "あ" to "あるく",
        "い" to "いねむりする",
        "う" to "うんどうする",
        "え" to "えびになる",
        "お" to "おとうさんになる"
    )

    private val binder = object : IMyAidlInterface.Stub() {
        override fun getOperation(operation: String): String? {
            return operations[operation]
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

}