package com.example.clipboardlink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, ClipboardMonitorService.class));
        WebView webView;

        final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        String pasteData="";
        ClipData clipData=clipboardManager.getPrimaryClip();
        boolean b= clipboardManager.hasPrimaryClip();
        if(b==false)
        {
            Toast.makeText(this, "Clipboard Empty", Toast.LENGTH_SHORT).show();
        }

        else
        {
            webView=findViewById(R.id.web_view);
            ClipData.Item item=clipData.getItemAt(0);
            String urlOnclipboard=item.getText().toString();
            Toast.makeText(this, ""+item.getText().toString(), Toast.LENGTH_SHORT).show();
            boolean ans= URLUtil.isValidUrl(urlOnclipboard);
            if(ans==true)
            {
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                webView.loadUrl(urlOnclipboard);
            }
            else
            {
                Toast.makeText(this, "NOt a url" +urlOnclipboard, Toast.LENGTH_SHORT).show();
            }
        }
    }
}