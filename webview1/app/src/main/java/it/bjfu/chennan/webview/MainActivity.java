package it.bjfu.chennan.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    private WebView webView;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview1);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://192.168.1.102:8080/index");//http://115.28.165.3:8080/app/UserPage.jsp
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        Button bn = (Button) findViewById(R.id.button);
        bn.setOnClickListener(new Button.OnClickListener() {//创建监听
            public void onClick(View v) {
                webView.goBack();
            }

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (this.webView.canGoBack()) {
                //需要处理
                this.webView.goBack();
                return true;
            } else {
                if (!mBackKeyPressed) {
                    mBackKeyPressed = true;
                    Toast toast= Toast.makeText(this,"再次按返回键退出程序",Toast.LENGTH_SHORT);
                    toast.show();
                    new Timer().schedule(new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mBackKeyPressed = false;
                        }//延时两秒，如果超出则擦错第一次按键记录

                    }, 2000);
                    return true;

                } else {//退出程序
                    this.finish();
                    System.exit(0);
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
