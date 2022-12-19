package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    boolean isPageOpen = true;
    EditText editText;
    Button button1, button2;
    WebView webView;

    Animation translateUp;
    Animation translateDown;

    LinearLayout page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        webView = findViewById(R.id.webView);

        page = findViewById(R.id.page);

        translateUp = AnimationUtils.loadAnimation(this, R.anim.translate_up);
        translateDown = AnimationUtils.loadAnimation(this, R.anim.translate_down);
        SlidingPageAnimationListener animationListener = new SlidingPageAnimationListener();
        translateUp.setAnimationListener(animationListener);
        translateDown.setAnimationListener(animationListener);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(editText.getText().toString());
                editText.setText("");
                page.startAnimation(translateDown);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page.setVisibility(View.VISIBLE);
                page.startAnimation(translateUp);
            }
        });
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {
            if(isPageOpen){
                isPageOpen = false;
            }else{
                isPageOpen = true;
            }
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                page.setVisibility(View.GONE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}