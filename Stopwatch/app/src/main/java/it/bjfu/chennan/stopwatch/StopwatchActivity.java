package it.bjfu.chennan.stopwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState!=null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putInt("seconds",seconds);
        savedInstanceState.putBoolean("running",running);
        savedInstanceState.putBoolean("wasRunning",wasRunning);
    }
    @Override
    public void onStop(){
        super.onStop();
//        wasRunning = running;
        running = false;
    }
    @Override
    public void onStart(){
        super.onStart();
        if (wasRunning)
        {
            running = true;
        }
    }
    @Override
    public void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }
    @Override
    public void onResume(){
        super.onResume();
        if (wasRunning)
        {
            running = true;
        }
    }
//    class MyRunable implements Runnable{
//        @Override
//        public void run()
//        {
//            int hours = seconds/3600;
//            int minutes = (seconds%3600)/60;
//            int secs = seconds%60;
//            String time = String .format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
//            timeview.setText(time);
//            if(running)
//            {
//                seconds++;
//            }
//            handler.postDelayed(this ,1000);
//        }
//    }
    public void onClickdialog(android.view.View view)
    {
        Intent intent = new Intent(this,CustomDialogActivity.class);
        startActivity(intent);
    }
    private void runTimer()
    {
        final TextView timeview = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String .format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeview.setText(time);
                if(running)
                {
                    seconds++;
                }
                handler.postDelayed(this ,1000);
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
