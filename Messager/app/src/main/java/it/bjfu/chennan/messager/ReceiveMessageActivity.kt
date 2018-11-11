package it.bjfu.chennan.messager

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ReceiveMessageActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receive_message)
        val intent = this.intent
        val messageText = intent.getStringExtra(EXTRA_MESSAGE)
        val messageView = findViewById<View>(R.id.message) as TextView
        messageView.text = messageText

    }

    companion object {
        val EXTRA_MESSAGE = "message"
    }
}
