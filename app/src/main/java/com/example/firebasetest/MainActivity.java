package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tx;
    Button confirmar;
    EditText idtext,senhatext;
    Context context = this;
    MainActivity2 tlp;
    String numb = "";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    MainActivity3 lyt;
    ImageView act1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            confirmar = findViewById(R.id.button);
            idtext = findViewById(R.id.editTextTextPersonName);
            senhatext = findViewById(R.id.editTextTextPersonName2);
            tx = (TextView) findViewById(R.id.textView8);
            myRef = FirebaseDatabase.getInstance().getReference();
            act1 = findViewById(R.id.imageView3);

        savetema1();
        readtema1();

        switch (lyt.tema1) {
            case "Azul":
                act1.setBackgroundResource(R.drawable.pretoblue01);
                break;
            case "Vermelho":
                act1.setBackgroundResource(R.drawable.pretored);
                break;
            case "Verde":
                act1.setBackgroundResource(R.drawable.verde1);
                break;
            case "Amarelo":
                act1.setBackgroundResource(R.drawable.amarelo1);
                break;
            case "Galeria":
                try {
                    byte[] encodeByte = Base64.decode(lyt.custombytes, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//                    act1.setImageBitmap(bitmap);
                    Drawable image2 = new BitmapDrawable(BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length));
                    act1.setForeground(image2);
                } catch (Exception e) {
                    e.getMessage();
                }
                break;
        }



           confirmar.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

final String iddig = idtext.getText().toString();
final String senhadig = senhatext.getText().toString();

        if (!iddig.equals(null)&&!iddig.equals("")){
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot snapshot) {
//extensions
    if (snapshot.child("extensions").exists()) {
        if (snapshot.child("extensions").child(iddig).exists()) {
            if (snapshot.child("extensions").child(iddig).child("senha").exists()) {
                String pass = snapshot.child("extensions").child(iddig).child("senha").getValue().toString();
                if (pass.equals(senhadig)) {
                    tlp.salvar = true;
                }else {
                    tx.setText("Senha incorreta");
                }
                if (snapshot.child("extensions").child(iddig).child("idclient").exists()) {
                    tlp.idclient = snapshot.child("extensions").child(iddig).child("idclient").getValue().toString();
                }
    if (snapshot.child("constantes").exists()) {
        if (snapshot.child("constantes").child("mqtthost").exists()) {
            tlp.mqtthost = snapshot.child("constantes").child("mqtthost").getValue().toString();
        }
        if (snapshot.child("constantes").child("topico1").exists()) {
            tlp.topico1 = snapshot.child("constantes").child("topico1").getValue().toString() + tlp.idclient;
        }
        if (snapshot.child("constantes").child("topico2").exists()) {
            tlp.topico2 = snapshot.child("constantes").child("topico2").getValue().toString() + tlp.idclient;
        }
        if (snapshot.child("constantes").child("topico3").exists()) {
            tlp.topico3 = snapshot.child("constantes").child("topico3").getValue().toString() + tlp.idclient;
        }
        if (snapshot.child("constantes").child("topico4").exists()) {
            tlp.topico4 = snapshot.child("constantes").child("topico4").getValue().toString() + tlp.idclient;
        }
        if (snapshot.child("constantes").child("topicost").exists()) {
            tlp.topicost = snapshot.child("constantes").child("topicost").getValue().toString() + tlp.idclient;
        }
        if (snapshot.child("constantes").child("username").exists()) {
            tlp.username = snapshot.child("constantes").child("username").getValue().toString();
        }
        if (snapshot.child("constantes").child("userpass").exists()) {
            tlp.userpass = snapshot.child("constantes").child("userpass").getValue().toString();
        }
        if (snapshot.child("constantes").child("mqttcontrol").exists()) {
            tlp.mqttcontrol = snapshot.child("constantes").child("mqttcontrol").getValue().toString();
        }
//        if (snapshot.child("extensions").child(iddig).child("inscritos").exists()) {
//            numb = snapshot.child("constantes").child("mqttcontrol").getValue().toString();
//        }

        Random rand = new Random(); //instance of random class
        int upperbound = 10000;
        int int_random = rand.nextInt(upperbound);
        tlp.idclient = tlp.idclient + "/" + int_random;
        int_random =0;

        if (pass.equals(senhadig)) {
            telaprincipal(); }
    }else {
        Toast.makeText(context, "Erro no banco de dados 1", Toast.LENGTH_SHORT).show();
    }
            }
        }else {
            tx.setText("ID não encontrado");
        }
    }else {
        Toast.makeText(context, "Erro no banco de dados 2", Toast.LENGTH_SHORT).show();
    }
    //
}
        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
        });
        }
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

public  void telaprincipal(){
        Intent i = new Intent(this,MainActivity2.class);
        startActivity(i);
    }
}

//        bt.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                id = ed1.getText().toString();
//                senha = ed2.getText().toString();
//
//                myRef.child("testfire").setValue(id);
//
//                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        str = snapshot.child("testfire").getValue().toString();  //deu certo
//                        tx.setText(str);
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {     //
//
//                    }
//                });
//
//
//            }
//        });