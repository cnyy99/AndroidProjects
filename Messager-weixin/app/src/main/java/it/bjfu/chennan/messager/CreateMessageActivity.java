package it.bjfu.chennan.messager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

    }
    public void onSendMessage(View view){
        //TODO
        EditText messageView = (EditText)findViewById(R.id.message);
        String messageText = messageView.getText().toString();

        Intent intent1 = new Intent(Intent.ACTION_SEND);
        intent1.setType("text/plain");
        intent1.putExtra(intent1.EXTRA_TEXT,messageText);

        Intent intent = new Intent(this,ReceiveMessageActivity.class);
        intent.putExtra(ReceiveMessageActivity.EXTRA_MESSAGE,messageText);
        startActivity(intent1);

    }
}
