package it.bjfu.chennan.experiment1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CharSequence text = "Hello, I'm a Toast";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this,text,duration);
        toast.show();
    }
}
