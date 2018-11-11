package it.bjfu.chennan.messager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText

class CreateMessageActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_message)

    }

    fun onSendMessage(view: View) {
        //TODO
        val messageView = findViewById<View>(R.id.message) as EditText
        val messageText = messageView.text.toString()
        val intent = Intent(this, ReceiveMessageActivity::class.java)
        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE, messageText)
        startActivity(intent)

    }
}
