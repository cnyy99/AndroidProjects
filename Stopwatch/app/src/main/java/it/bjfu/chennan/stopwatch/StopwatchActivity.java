package it.bjfu.chennan.stopwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {
    TelephonyManager manager;
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    private boolean iswasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        manager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);// 手动注册对PhoneStateListener中的listen_call_state状态进行监听  
        manager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onStop() {
        super.onStop();
//        wasRunning = running;
        running = false;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (iswasRunning) {
            wasRunning = true;
        } else {
            wasRunning = running;
        }
        running = false;
        iswasRunning = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    if (wasRunning) {
                        running = true;
                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    iswasRunning = running;
                    wasRunning = running;
                    running = false;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                default:
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }

    }

    public void onClickdialog(android.view.View view) {
        Intent intent = new Intent(this, CustomDialogActivity.class);
        startActivity(intent);
    }

    private void runTimer() {
        final TextView timeview = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });

    }

    //
    public void onClickStart(View view) {
        running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }
}
