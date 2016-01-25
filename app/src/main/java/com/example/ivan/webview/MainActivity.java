package com.example.ivan.webview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ivan.webview.bd.ConexionBD;
import com.example.ivan.webview.bd.GestorLlamadas;
import com.example.ivan.webview.pojo.Llamada;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText et;
    private Button bt;
    private GestorLlamadas gl;
    private int[] a = new int[]{10,14,28,13,17,20,25};
    private List<Llamada> listll = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        gl = new GestorLlamadas(this);

        this.webView = (WebView) findViewById(R.id.webView);
        this.bt = (Button) findViewById(R.id.bt);
        this.et = (EditText) findViewById(R.id.et);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/canvas/pruebagraficos.html");

        webView.addJavascriptInterface(this, "InterfazAndroid");


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:recibirValorAndroid('" + et.getText().toString() + "')");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gl.open();
        listll = new ArrayList<>();
        listll = gl.select();



    }

    @Override
    protected void onPause() {
        super.onPause();
        gl.close();
    }

    public void consultarDia(int x){

        String where = ConexionBD.TablaLlamada.DIAM + " = ?";
        String[] parametros = new String[] { x+"" };
        listll = gl.select(where,parametros);

    }

    @JavascriptInterface
    public void recibirValorDeJavascript(final String str) {
        Toast.makeText(this, "Valor recibido:" + str, Toast.LENGTH_SHORT).show();
        et.post(new Runnable() {
            @Override
            public void run() {
                et.setText(str);
            }
        });
    }

    @JavascriptInterface
    public String enviarValorAJavascript() {
        return et.getText().toString();
    }

    @JavascriptInterface
    public int enviarDia(int x){
        consultarDia(x+1);
        if(listll==null){
            return 0;
        }
        return listll.size();
    }
}
