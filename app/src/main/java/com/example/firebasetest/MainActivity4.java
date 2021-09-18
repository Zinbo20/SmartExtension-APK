package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    Button bt1;
    EditText ed1;
    EditText ed2;
    TextView av1;

    Context context = this;

    String ssid = "";
    String senha =  "";

    WebView webView;

    MainActivity3 lyt;
    ImageView act4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        webView = (WebView) findViewById(R.id.webview);
        bt1 = (Button) findViewById (R.id.button7);
        ed1 = (EditText) findViewById(R.id.editTextTextPersonName4);
        ed2 = (EditText) findViewById(R.id.editTextTextPersonName5);
        av1 = (TextView) findViewById(R.id.textView5);
        act4 = findViewById(R.id.imageView4);

        savetema1();
        readtema1();
        readSsid();
        readSenha();

        ed1.setText(ssid);
        ed2.setText(senha);

        switch (lyt.tema1) {
            case "Azul":
                act4.setBackgroundResource(R.drawable.pretoblue);
                break;
            case "Vermelho":
                act4.setBackgroundResource(R.drawable.pretored3);
                break;
            case "Verde":
                act4.setBackgroundResource(R.drawable.verde3);
                break;
            case "Amarelo":
                act4.setBackgroundResource(R.drawable.amarelo3);
                break;
            case "Galeria":
                try {
                    byte[] encodeByte = Base64.decode(lyt.custombytes, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//                    act4.setImageBitmap(bitmap);
                    Drawable image2 = new BitmapDrawable(BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length));
                    act4.setForeground(image2);
                } catch (Exception e) {
                    e.getMessage();
                }
                break;
        }

        av1.setText("Conecte na Rede Smart Extension.");
        webView.loadUrl("http://192.168.4.1");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                av1.setText("");
                bt1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ssid = ed1.getText().toString();
                        senha = ed2.getText().toString();
                        saveSsid();
                        saveSenha();
                        webView.loadUrl("http://192.168.4.1/get?input1="+ssid+"&input2="+senha+"&input3="+ "" +"&input4="+ "" +"&input5="+ "" +"&input6="+ "");
                        webView.setWebViewClient(new WebViewClient(){
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);

                            }
                        });
                    }
                });

            }
        });

    }

    private void readtema1() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema1", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tema1", "");
        if (var != "") {
            lyt.tema1 = var;
        }
    }
    private void savetema1() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tema1", lyt.tema1);
        editor.commit();
    }

    private void readSsid() {
        SharedPreferences sharedPreferences = getSharedPreferences("readSsid", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("readSsid", "");
        if (var != "") {
            ssid = var;
        }
    }
    private void saveSsid() {
        SharedPreferences sharedPreferences = getSharedPreferences("readSsid", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("readSsid", ssid);
        editor.commit();
    }

    private void readSenha() {
        SharedPreferences sharedPreferences = getSharedPreferences("readSenha", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("readSenha", "");
        if (var != "") {
            senha = var;
        }
    }
    private void saveSenha() {
        SharedPreferences sharedPreferences = getSharedPreferences("readSenha", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("readSenha", senha);
        editor.commit();
    }

}