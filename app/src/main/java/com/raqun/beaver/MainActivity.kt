package com.raqun.beaver

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.raqun.beaverlib.Beaver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            Beaver.build(context = this)
        }

        GlobalScope.launch {
            val metadata = Beaver.load("https://www.medium.com").await()
            Log.e("metadata", metadata?.toString())
        }
    }

}