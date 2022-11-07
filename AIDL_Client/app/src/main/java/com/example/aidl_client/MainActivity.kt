package com.example.aidl_client

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.example.aidl_client.databinding.ActivityMainBinding
import com.example.aidl_service.IMyAidlInterface

class MainActivity : Activity() {
    lateinit var binding: ActivityMainBinding
    private var bond = true
    private var isFather = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var text = ""
        if (bond) {
            binding.a.setOnClickListener {
                try {
                    text = iMyAidlInterface?.getOperation("あ").toString()
                    binding.operation.text = text
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
            binding.i.setOnClickListener {
                try {
                    text = iMyAidlInterface?.getOperation("い").toString()
                    binding.operation.text = text
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
            binding.u.setOnClickListener {
                try {
                    text = iMyAidlInterface?.getOperation("う").toString()
                    binding.operation.text = text
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
            binding.e.setOnClickListener {
                try {
                    isFather = false
                    text = iMyAidlInterface?.getOperation("え").toString()
                    binding.operation.text = text
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
            binding.o.setOnClickListener {
                try {
                    isFather = true
                    text = iMyAidlInterface?.getOperation("お").toString()
                    binding.operation.text = text
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val it = Intent("MyRemoteService")
        it.setPackage("com.example.aidl_service")
        bindService(it, connection, Context.BIND_AUTO_CREATE)
        Log.d("い", bindService(it, connection, Context.BIND_AUTO_CREATE).toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    private var iMyAidlInterface: IMyAidlInterface? = null

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(binder)
            bond = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            iMyAidlInterface = null
            bond = false
        }
    }
}