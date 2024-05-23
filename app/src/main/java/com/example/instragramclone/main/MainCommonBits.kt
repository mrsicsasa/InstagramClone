package com.example.instragramclone.main

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.instragramclone.IgViewModel

@Composable
fun NotificationMessage(vm:IgViewModel){
    val notifState=vm.popupNotification.value
    val notifMessage=notifState?.getContentOrNull()
    println("radi zadata")
    if(notifMessage!=null){
        println("radi zadata u")
        Toast.makeText(LocalContext.current,notifMessage,Toast.LENGTH_LONG).show()
    }
}