package it.bjfu.chennan.stopwatch;

import android.app.Activity;
import android.os.Bundle;

public class CustomDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //////设置为true点击区域外消失
        setFinishOnTouchOutside(true);
        setContentView(R.layout.activity_custom_dialog);
    }
}
