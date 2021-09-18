package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    ImageView cf;
    ToggleButton rl1, rl2, rl3, rl4;
    MqttAndroidClient client;
    TextView st;
    TextView st2;
    static String mqtthost;
    static String idclient;
    static String topico1;
    static String topico2;
    static String topico3;
    static String topico4;
    static String topicost;
    static String topicoref;
    static String username;
    static String userpass;
    static String mqttcontrol;

    String rlst = "";
    String status1="";
    String status2="";
    String status3="";
    String status4="";
    String tmd1="";
    String tmd2="";
    String tmd3="";
    String tmd4="";
    WebView webView;

    static String lytbytes="";

    boolean conect;
    static Boolean salvar = false;
    Context context = this;

    MainActivity3 lyt;
    ImageView act2;
    TextView tx1;
    TextView tx2;
    TextView tx3;
    TextView tx4;

    static String nm1 = "";
    static String nm2 = "";
    static String nm3 = "";
    static String nm4 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (salvar == true) {
            savemqqt();readmqtt();
            saveidclient();readidclinet();
            savetopicoref();readtopicoref();

            savetp1();readtp1();
            savetp2();readtp2();
            savetp3();readtp3();
            savetp4();readtp4();

            savest();readst();

            saveusername();readusername();
            saveuserpass();readuserpass();

            savemqttcontrol();readmqttcontrol();

            savetema1();readtema1();

            savenm1();readnm1();
            savenm2();readnm2();
            savenm3();readnm3();
            savenm4();readnm4();

            savesta1();readsta1();
            savesta2();readsta2();
            savesta3();readsta3();
            savesta4();readsta4();

            savecustom();readcustom();

        } else {
            readidclinet();
            readtopicoref();

            readtp1();readtp2();
            readtp3();readtp4();

            readmqttcontrol();

            readusername();
            readuserpass();

            readmqtt();

            readnm1();readnm2();
            readnm3();readnm4();

            readsta1();readsta2();
            readsta3();readsta4();

            readst();

            readtema1();

            readcustom();
        }

        webView = (WebView) findViewById(R.id.webview2);
        cf = findViewById(R.id.button2);
        st = findViewById(R.id.textView2);
        st2 = findViewById(R.id.textView12);
        tx1 = findViewById(R.id.textView9);
        tx2 = findViewById(R.id.textView7);
        tx3 = findViewById(R.id.textView10);
        tx4 = findViewById(R.id.textView11);
        rl1 = findViewById(R.id.toggleButton);
        rl2 = findViewById(R.id.toggleButton2);
        rl3 = findViewById(R.id.toggleButton3);
        rl4 = findViewById(R.id.toggleButton4);
        act2 = findViewById(R.id.imageView2);

        if (nm1 != "" && nm1 != null) {
            tx1.setText(nm1);
        }
        if (nm2 != "" && nm2 != null) {
            tx2.setText(nm2);
        }
        if (nm3 != "" && nm3 != null) {
            tx3.setText(nm3);
        }
        if (nm4 != "" && nm4 != null) {
            tx4.setText(nm4);
        }

        switch (lyt.tema1) {
            case "Azul":
                act2.setBackgroundResource(R.drawable.pretoblue2);
                break;
            case "Vermelho":
                act2.setBackgroundResource(R.drawable.pretored2);
                break;
            case "Verde":
                act2.setBackgroundResource(R.drawable.verde2);
                break;
            case "Amarelo":
                act2.setBackgroundResource(R.drawable.amarelo2);
                break;
            case "Galeria":
                try {
                    byte[] encodeByte = Base64.decode(lytbytes, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//                act2.setImageBitmap(bitmap);
                    Drawable image2 = new BitmapDrawable(BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length));
                    act2.setForeground(image2);
                } catch (Exception e) {
                    e.getMessage();
                }
                break;
        }


        if(status1.equals("led1on")){
            rl1.setChecked(true);
        }else{
            rl1.setChecked(false);
        }
        if(status2.equals("led2on")){
            rl2.setChecked(true);
        }else{
            rl2.setChecked(false);
        }
        if(status3.equals("led3on")){
            rl3.setChecked(true);
        }else{
            rl3.setChecked(false);
        }
        if(status4.equals("led4on")){
            rl4.setChecked(true);
        }else{
            rl4.setChecked(false);
        }


        cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuraçao();
            }
        });

        if (mqtthost != "" && mqtthost != null) {

            String clientId = MqttClient.generateClientId();
            MqttAndroidClient client =
                    new MqttAndroidClient(this.getApplicationContext(), "tcp://" + mqtthost,
                            idclient);
            MqttConnectOptions options = new MqttConnectOptions();
            if (mqttcontrol.equals("privado")) {
                options.setUserName(username);
                options.setPassword(userpass.toCharArray());
            }
            try {
                IMqttToken token = client.connect(options);
                token.setActionCallback(new IMqttActionListener() {

                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        // We are connected
                        st.setText("Online");
                        conect = true;

                        try {
                            client.subscribe(topicost, 0);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }

                        MyCallback myCallback = new MyCallback();
                        client.setCallback(myCallback);

                        String payload1 = "status";
                        byte[] encodedPayload1 = new byte[0];
                        try {
                            encodedPayload1 = payload1.getBytes("UTF-8");
                            MqttMessage message1 = new MqttMessage(encodedPayload1);
                            client.publish(topico1, message1);

                        } catch (UnsupportedEncodingException | MqttException e) {

                            Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        // Something went wrong e.g. connection timeout or firewall problems
                        st.setText("Offline");
                        conect = false;
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
                conect = false;
            }

            rl1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (conect == true) {
                        if (rl1.isChecked()) {
                            String payload1 = "ligado";
                            byte[] encodedPayload1 = new byte[0];
                            try {
                                encodedPayload1 = payload1.getBytes("UTF-8");
                                MqttMessage message1 = new MqttMessage(encodedPayload1);
                                client.publish(topico1, message1);

                            } catch (UnsupportedEncodingException | MqttException e) {

                                Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();

                            }

                        } else if (!rl1.isChecked()) {
                            String payload1 = "desligado";
                            byte[] encodedPayload1 = new byte[0];
                            try {
                                encodedPayload1 = payload1.getBytes("UTF-8");
                                MqttMessage message1 = new MqttMessage(encodedPayload1);
                                client.publish(topico1, message1);

                            } catch (UnsupportedEncodingException | MqttException e) {

                                Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();

                            }
                        }

                        String payload1 = "status";
                        byte[] encodedPayload1 = new byte[0];
                        try {
                            encodedPayload1 = payload1.getBytes("UTF-8");
                            MqttMessage message1 = new MqttMessage(encodedPayload1);
                            client.publish(topico1, message1);

                        } catch (UnsupportedEncodingException | MqttException e) {

                            Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();
                        }

                        if (status1.equals("led1off") && !rl1.isChecked() || status1.equals("led1on") && rl1.isChecked()) {
                            st2.setText("Sem Resposta");
                        }
//            else if(!st.getText().toString().equals("Online")){
//                st.setText("Online");
//            }




                    }
                }
            });







            rl2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (conect == true) {
                        if (rl2.isChecked()) {
                            String payload1 = "ligado";
                            byte[] encodedPayload1 = new byte[0];
                            try {
                                encodedPayload1 = payload1.getBytes("UTF-8");
                                MqttMessage message1 = new MqttMessage(encodedPayload1);
                                client.publish(topico2, message1);

                            } catch (UnsupportedEncodingException | MqttException e) {

                                Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();

                            }

                        } else if (!rl2.isChecked()) {
                            String payload1 = "desligado";
                            byte[] encodedPayload1 = new byte[0];
                            try {
                                encodedPayload1 = payload1.getBytes("UTF-8");
                                MqttMessage message1 = new MqttMessage(encodedPayload1);
                                client.publish(topico2, message1);

                            } catch (UnsupportedEncodingException | MqttException e) {

                                Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();
                            }
                        }

                        String payload1 = "status";
                        byte[] encodedPayload1 = new byte[0];
                        try {
                            encodedPayload1 = payload1.getBytes("UTF-8");
                            MqttMessage message1 = new MqttMessage(encodedPayload1);
                            client.publish(topico1, message1);

                        } catch (UnsupportedEncodingException | MqttException e) {

                            Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();
                        }

                        if (status2.equals("led2off") && !rl2.isChecked() || status2.equals("led2on") && rl2.isChecked()) {
                            st2.setText("Sem Resposta");
                        }
//                        else if(!st.getText().toString().equals("Online")){
//                            st.setText("Online");
//                        }

                    }
                }
            });

            rl3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (conect == true) {
                        if (rl3.isChecked()) {
                            String payload1 = "ligado";
                            byte[] encodedPayload1 = new byte[0];
                            try {
                                encodedPayload1 = payload1.getBytes("UTF-8");
                                MqttMessage message1 = new MqttMessage(encodedPayload1);
                                client.publish(topico3, message1);

                            } catch (UnsupportedEncodingException | MqttException e) {

                                Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();

                            }

                        } else if (!rl3.isChecked()) {
                            String payload1 = "desligado";
                            byte[] encodedPayload1 = new byte[0];
                            try {
                                encodedPayload1 = payload1.getBytes("UTF-8");
                                MqttMessage message1 = new MqttMessage(encodedPayload1);
                                client.publish(topico3, message1);

                            } catch (UnsupportedEncodingException | MqttException e) {

                                Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();
                            }
                        }

                        String payload1 = "status";
                        byte[] encodedPayload1 = new byte[0];
                        try {
                            encodedPayload1 = payload1.getBytes("UTF-8");
                            MqttMessage message1 = new MqttMessage(encodedPayload1);
                            client.publish(topico1, message1);

                        } catch (UnsupportedEncodingException | MqttException e) {

                            Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();
                        }

                        if (status3.equals("led3off") && !rl3.isChecked() || status3.equals("led3on") && rl3.isChecked()) {
                            st2.setText("Sem Resposta");
                        }
//                        else if(!st.getText().toString().equals("Online")){
//                            st.setText("Online");
//                        }

                    }
                }
            });

            rl4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (conect == true) {
                        if (rl4.isChecked()) {
                            String payload1 = "ligado";
                            byte[] encodedPayload1 = new byte[0];
                            try {
                                encodedPayload1 = payload1.getBytes("UTF-8");
                                MqttMessage message1 = new MqttMessage(encodedPayload1);
                                client.publish(topico4, message1);

                            } catch (UnsupportedEncodingException | MqttException e) {

                                Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();

                            }

                        } else if (!rl4.isChecked()) {
                            String payload1 = "desligado";
                            byte[] encodedPayload1 = new byte[0];
                            try {
                                encodedPayload1 = payload1.getBytes("UTF-8");
                                MqttMessage message1 = new MqttMessage(encodedPayload1);
                                client.publish(topico4, message1);

                            } catch (UnsupportedEncodingException | MqttException e) {

                                Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();
                            }
                        }

                        String payload1 = "status";
                        byte[] encodedPayload1 = new byte[0];
                        try {
                            encodedPayload1 = payload1.getBytes("UTF-8");
                            MqttMessage message1 = new MqttMessage(encodedPayload1);
                            client.publish(topico1, message1);

                        } catch (UnsupportedEncodingException | MqttException e) {

                            Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();
                        }

                        if (status4.equals("led4off") && !rl4.isChecked() || status4.equals("led4on") && rl4.isChecked() ) {
                            st2.setText("Sem Resposta");
                        }
//                        else if(!st.getText().toString().equals("Online")){
//                            st.setText("Online");
//                        }

                    }
                }
            });

        } else {
            st.setText("Offline");
            conect = false;
        }
        if (conect == false) {
            webView.loadUrl("http://192.168.4.1");
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);

                    rl1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (rl1.isChecked()) {
                                tmd1 = "ligado";
                                status1="led1on";
                                savesta1();
                                webView.loadUrl("http://192.168.4.1/get?input1="+""+"&input2="+""+"&input3="+ tmd1 +"&input4="+ tmd2 +"&input5="+ tmd3 +"&input6="+ tmd4);
                                webView.setWebViewClient(new WebViewClient(){
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                    }
                                });
                            } else if (!rl1.isChecked()) {
                                tmd1 = "desligado";
                                status1="led1off";
                                savesta1();
                                webView.loadUrl("http://192.168.4.1/get?input1="+""+"&input2="+""+"&input3="+ tmd1 +"&input4="+ tmd2 +"&input5="+ tmd3 +"&input6="+ tmd4);
                                webView.setWebViewClient(new WebViewClient(){
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                    }
                                });
                            }
                        }
                    });
                    rl2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (rl2.isChecked()) {
                                tmd2 = "ligado";
                                status2="led2on";
                                savesta2();
                                webView.loadUrl("http://192.168.4.1/get?input1="+""+"&input2="+""+"&input3="+ tmd1 +"&input4="+ tmd2 +"&input5="+ tmd3 +"&input6="+ tmd4);
                                webView.setWebViewClient(new WebViewClient(){
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                    }
                                });
                            } else if (!rl2.isChecked()) {
                                tmd2 = "desligado";
                                status2="led2off";
                                savesta2();
                                webView.loadUrl("http://192.168.4.1/get?input1="+""+"&input2="+""+"&input3="+ tmd1 +"&input4="+ tmd2 +"&input5="+ tmd3 +"&input6="+ tmd4);
                                webView.setWebViewClient(new WebViewClient(){
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                    }
                                });
                            }
                        }
                    });
                    rl3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (rl3.isChecked()) {
                                tmd3 = "ligado";
                                status3="led3on";
                                savesta3();
                                webView.loadUrl("http://192.168.4.1/get?input1="+""+"&input2="+""+"&input3="+ tmd1 +"&input4="+ tmd2 +"&input5="+ tmd3 +"&input6="+ tmd4);
                                webView.setWebViewClient(new WebViewClient(){
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                    }
                                });
                            } else if (!rl3.isChecked()) {
                                tmd3 = "desligado";
                                status3="led3off";
                                savesta3();
                                webView.loadUrl("http://192.168.4.1/get?input1="+""+"&input2="+""+"&input3="+ tmd1 +"&input4="+ tmd2 +"&input5="+ tmd3 +"&input6="+ tmd4);
                                webView.setWebViewClient(new WebViewClient(){
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                    }
                                });
                            }
                        }
                    });
                    rl4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (rl4.isChecked()) {
                                tmd4 = "ligado";
                                status4="led4on";
                                savesta4();
                                webView.loadUrl("http://192.168.4.1/get?input1="+""+"&input2="+""+"&input3="+ tmd1 +"&input4="+ tmd2 +"&input5="+ tmd3 +"&input6="+ tmd4);
                                webView.setWebViewClient(new WebViewClient(){
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                    }
                                });
                            } else if (!rl4.isChecked()) {
                                tmd4 = "desligado";
                                status4="led4off";
                                savesta4();
                                webView.loadUrl("http://192.168.4.1/get?input1="+""+"&input2="+""+"&input3="+ tmd1 +"&input4="+ tmd2 +"&input5="+ tmd3 +"&input6="+ tmd4);
                                webView.setWebViewClient(new WebViewClient(){
                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                    }
                                });
                            }
                        }
                    });

                }
            });
        }

    }

    public void ref() {

        String payload1 = "ref";
        byte[] encodedPayload1 = new byte[0];
        try {
            encodedPayload1 = payload1.getBytes("UTF-8");
            MqttMessage message1 = new MqttMessage(encodedPayload1);
            client.publish(topicoref, message1);


        } catch (UnsupportedEncodingException | MqttException e) {

            Toast.makeText(context, "Erro de comando", Toast.LENGTH_SHORT).show();

        }

    }


    public void telalogin() {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

    private void saveidclient() {
        SharedPreferences sharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", idclient);
        editor.commit();

    }

    private void readidclinet() {
        SharedPreferences sharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("id", "");
        if (var != "") {
            idclient = var;
        }
    }

    private void savetopicoref() {
        SharedPreferences sharedPreferences = getSharedPreferences("ref", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ref", topicoref);
        editor.commit();

    }

    private void readtopicoref() {
        SharedPreferences sharedPreferences = getSharedPreferences("ref", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("ref", "");
        if (var != "") {
            topicoref = var;
        }
    }

    private void savetp1() {
        SharedPreferences sharedPreferences = getSharedPreferences("tp1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tp1", topico1);
        editor.commit();

    }

    private void readtp1() {
        SharedPreferences sharedPreferences = getSharedPreferences("tp1", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tp1", "");
        if (var != "") {
            topico1 = var;
        }
    }

    private void savetp2() {
        SharedPreferences sharedPreferences = getSharedPreferences("tp2", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tp2", topico2);
        editor.commit();

    }

    private void readtp2() {
        SharedPreferences sharedPreferences = getSharedPreferences("tp2", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tp2", "");
        if (var != "") {
            topico2 = var;
        }
    }

    private void savetp3() {
        SharedPreferences sharedPreferences = getSharedPreferences("tp3", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tp3", topico3);
        editor.commit();

    }

    private void readtp3() {
        SharedPreferences sharedPreferences = getSharedPreferences("tp3", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tp3", "");
        if (var != "") {
            topico3 = var;
        }
    }

    private void savetp4() {
        SharedPreferences sharedPreferences = getSharedPreferences("tp4", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tp4", topico4);
        editor.commit();

    }

    private void readtp4() {
        SharedPreferences sharedPreferences = getSharedPreferences("tp4", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tp4", "");
        if (var != "") {
            topico4 = var;
        }
    }

    private void saveusername() {
        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.commit();
    }

    private void readusername() {
        SharedPreferences sharedPreferences = getSharedPreferences("username", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("username", "");
        if (var != "") {
            username = var;
        }
    }

    private void saveuserpass() {
        SharedPreferences sharedPreferences = getSharedPreferences("userpass", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userpass", userpass);
        editor.commit();
    }

    private void savemqttcontrol() {
        SharedPreferences sharedPreferences = getSharedPreferences("mqttcontrol", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mqttcontrol", mqttcontrol);
        editor.commit();
    }

    private void readuserpass() {
        SharedPreferences sharedPreferences = getSharedPreferences("userpass", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("userpass", "");
        if (var != "") {
            userpass = var;
        }
    }

    private void readmqttcontrol() {
        SharedPreferences sharedPreferences = getSharedPreferences("mqttcontrol", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("mqttcontrol", "");
        if (var != "") {
            mqttcontrol = var;
        }
    }

    private void savemqqt() {
        SharedPreferences sharedPreferences = getSharedPreferences("mqtt", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mqtthost", mqtthost);
        editor.commit();
    }

    private void readmqtt() {
        SharedPreferences sharedPreferences = getSharedPreferences("mqtt", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("mqtthost", "");
        if (var != "") {
            mqtthost = var;
        }
    }


    //save tema
    private void readcustom() {
        SharedPreferences sharedPreferences = getSharedPreferences("customB", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("customB", "");
        if (var != "") {
            lytbytes = var;
        }
    }
    private void savecustom() {
        SharedPreferences sharedPreferences = getSharedPreferences("customB", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("customB", lytbytes);
        editor.commit();
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


    //save name
    private void readnm2() {
        SharedPreferences sharedPreferences = getSharedPreferences("nm2", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("nm2", "");
        if (var != "") {
            nm2 = var;
        }
    }

    private void savenm2() {
        SharedPreferences sharedPreferences = getSharedPreferences("nm2", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nm2", nm2);
        editor.commit();
    }

    private void readnm3() {
        SharedPreferences sharedPreferences = getSharedPreferences("nm3", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("nm3", "");
        if (var != "") {
            nm3 = var;
        }
    }

    private void savenm3() {
        SharedPreferences sharedPreferences = getSharedPreferences("nm3", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nm3", nm3);
        editor.commit();
    }

    private void readnm4() {
        SharedPreferences sharedPreferences = getSharedPreferences("nm4", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("nm4", "");
        if (var != "") {
            nm4 = var;
        }
    }

    private void savenm4() {
        SharedPreferences sharedPreferences = getSharedPreferences("nm4", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nm4", nm4);
        editor.commit();
    }

    private void readnm1() {
        SharedPreferences sharedPreferences = getSharedPreferences("nm1", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("nm1", "");
        if (var != "") {
            nm1 = var;
        }
    }

    private void savenm1() {
        SharedPreferences sharedPreferences = getSharedPreferences("nm1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nm1", nm1);
        editor.commit();
    }

    private void readst() {
        SharedPreferences sharedPreferences = getSharedPreferences("st", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("st", "");
        if (var != "") {
            topicost = var;
        }
    }

    private void savest() {
        SharedPreferences sharedPreferences = getSharedPreferences("st", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("st", topicost);
        editor.commit();
    }
    private void readsta1() {
        SharedPreferences sharedPreferences = getSharedPreferences("sta1", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("sta1", "");
        if (var != "") {
            status1 = var;
        }
    }

    private void savesta1() {
        SharedPreferences sharedPreferences = getSharedPreferences("sta1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sta1", status1);
        editor.commit();
    }
    private void readsta2() {
        SharedPreferences sharedPreferences = getSharedPreferences("sta2", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("sta2", "");
        if (var != "") {
            status2 = var;
        }
    }

    private void savesta2() {
        SharedPreferences sharedPreferences = getSharedPreferences("sta2", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sta2", status2);
        editor.commit();
    }
    private void readsta3() {
        SharedPreferences sharedPreferences = getSharedPreferences("sta3", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("sta3", "");
        if (var != "") {
            status3 = var;
        }
    }

    private void savesta3() {
        SharedPreferences sharedPreferences = getSharedPreferences("sta3", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sta3", status3);
        editor.commit();
    }
    private void readsta4() {
        SharedPreferences sharedPreferences = getSharedPreferences("sta4", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("sta4", "");
        if (var != "") {
            status4 = var;
        }
    }

    private void savesta4() {
        SharedPreferences sharedPreferences = getSharedPreferences("sta4", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sta4", status4);
        editor.commit();
    }

    public void Configuraçao() {
        Intent i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }


    public class MyCallback implements MqttCallback {

        @Override
        public void connectionLost(Throwable cause) {
        }

        public void messageArrived(String topic, MqttMessage message) throws Exception {
            if(st2.getText().toString().equals("Sem Resposta")){
            st2.setText("");}
            int y = 0;
            char[] messagechar = message.toString().toCharArray();
            for (int i = 0; i < message.toString().length(); i++) {
                if (messagechar[i] == '1') {
                    if (messagechar[i + 4] == 'e') {
                        y = 3;
                    } else {
                        y = 4;
                    }
                    for (int x = 0; x < i + y; x++) {
                        rlst = rlst + messagechar[x];
                    }
                    status1 = rlst;
                    savesta1();
                    readsta1();
                    rlst = "";
                }
                if (messagechar[i] == '2') {
                    if (messagechar[i + 4] == 'e') {
                        y = 3;
                    } else {
                        y = 4;
                    }
                    for (int x = status1.length(); x < i + y; x++) {
                        rlst = rlst + messagechar[x];
                    }
                    status2 = rlst;
                    savesta2();
                    readsta2();
                    rlst = "";
                }
                if (messagechar[i] == '3') {
                    if (messagechar[i + 4] == 'e') {
                        y = 3;
                    } else {
                        y = 4;
                    }
                    for (int x = status1.length() + status2.length(); x < i + y; x++) {
                        rlst = rlst + messagechar[x];
                    }
                    status3 = rlst;
                    savesta3();
                    readsta3();
                    rlst = "";
                }
                    for (int x = status1.length() + status2.length() + status3.length(); x < message.toString().length(); x++) {
                        rlst = rlst + messagechar[x];
                    }
                    status4 = rlst;
                    savesta4();
                    readsta4();
                    rlst = "";

                if(status1.equals("led1on")){
                    rl1.setChecked(true);
                }else{
                    rl1.setChecked(false);
                }
                if(status2.equals("led2on")){
                    rl2.setChecked(true);
                }else{
                    rl2.setChecked(false);
                }
                if(status3.equals("led3on")){
                    rl3.setChecked(true);
                }else{
                    rl3.setChecked(false);
                }
                if(status4.equals("led4on")){
                    rl4.setChecked(true);
                }else{
                    rl4.setChecked(false);
                }
            }

        }
        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }
    }
}