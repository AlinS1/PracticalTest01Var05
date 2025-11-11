package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Context
import android.content.Intent
import android.os.Process
import android.util.Log
import java.util.Date
import java.util.Random
import kotlin.math.sqrt

class ProcessingThread(private val context: Context, result: String) :
    Thread() {
    private var isRunning = true
    private var result = result

    private val random = Random()

    override fun run() {
        while (isRunning) {
            sendMessage()
            sleep()
        }
    }

    private fun sendMessage() {
        val splitted = result.split(' ')
        for (s in splitted){
            val intent = Intent()
            intent.setAction("Word")
            intent.putExtra(
                "String",
s
                )
            context.sendBroadcast(intent)

        }
    }

    private fun sleep() {
        try {
            sleep(1000)
        } catch (interruptedException: InterruptedException) {
            interruptedException.printStackTrace()
        }
    }

    fun stopThread() {
        isRunning = false
    }
}