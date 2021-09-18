package com.example.firebasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity3 extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;
    Button bt4;
    Button bt3;
    Button bts;
    Button btt;
    ImageView act3;
    EditText ntx;
    MainActivity2 ma2;

    String name1 = "Rele1";
    String name2 = "Rele2";
    String name3 = "Rele3";
    String name4 = "Rele4";

    static String tema1 = "Vermelho";
    static String tema2= "Azul";
    static String tema3 = "Amarelo";
    static String tema4= "Verde";
    static String custom= "Galeria";
    static String custombytes= "";
    static String mdc= "";
    String spinnerstring= "";
    String spinnerstring2= "";
    Context context = this;
    static Boolean salvar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        if (salvar == true) {
//            savenome1();readnome1();
//            savenome2();readnome2();
//            savenome3();readnome3();
//            savenome4();readnome4();
            savetema1();readtema1();
            savetema2();readtema2();
            savetema3();readtema3();
            savetema4();readtema4();
            savecustom();readcustom();
            savecustombytes();readcustombytes();
        }else{
//            readnome1();
//            readnome2();
//            readnome3();
//            readnome4();
            readtema1();
            readtema2();
            readtema3();
            readtema4();
            readcustom();
            readcustombytes();

        }

        bt4 = (Button) findViewById(R.id.button4);
        bt3 = (Button) findViewById(R.id.button3);
        bts = (Button) findViewById(R.id.button6);
        btt = (Button) findViewById(R.id.button5);
        ntx = findViewById(R.id.editTextTextPersonName3);
        act3 = findViewById(R.id.imageView);

        if(!MainActivity2.nm1.equals("")){
            name1=MainActivity2.nm1;
        }
        if(!MainActivity2.nm2.equals("")){
            name2=MainActivity2.nm2;
        }
        if(!MainActivity2.nm3.equals("")){
            name3=MainActivity2.nm3;
        }
        if(!MainActivity2.nm4.equals("")){
            name4=MainActivity2.nm4;
        }

        spinner = findViewById(R.id.spinner);
        String [] names = {name1,name2,name3,name4};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,names);
        spinner.setAdapter(adapter);

        spinner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinner.getSelectedView()).setTextColor(Color.WHITE);
            }
        });

        spinner2 = findViewById(R.id.spinner2);
        String [] temas = {tema1,tema2 ,tema3,tema4,custom};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,temas);
        spinner2.setAdapter(adapter2);

        spinner2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ((TextView) spinner2.getSelectedView()).setTextColor(Color.WHITE);
            }
        });

        if(tema1.equals("Azul")){
            act3.setBackgroundResource(R.drawable.pretoblue);
        }
        else if(tema1.equals("Vermelho")){
            act3.setBackgroundResource(R.drawable.pretored3);
        }
        else if(tema1.equals("Verde")){
            act3.setBackgroundResource(R.drawable.verde1);
        }
        else if(tema1.equals("Amarelo")){
            act3.setBackgroundResource(R.drawable.amarelo3);
        }
        else if(tema1.equals("Galeria")){
            try {
                byte[] encodeByte = Base64.decode(custombytes, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//                act3.setImageBitmap(bitmap);
                Drawable image2 = new BitmapDrawable(BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length));
                act3.setForeground(image2);
            } catch (Exception e) {
                e.getMessage();
            }
        }




        bts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            spinnerstring = (String)spinner.getSelectedItem();

                if(name1.equals(spinnerstring) && !ntx.getText().toString().equals("") && spinner.getSelectedItemPosition() == 0 ){
                    String [] names = {name1,name2,name3,name4};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,names);
                    spinner.setAdapter(adapter);
                    ma2.nm1 = ntx.getText().toString();
                    ma2.salvar = true;
                    Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(i);
                }
                if(name2.equals(spinnerstring)&& !ntx.getText().toString().equals("") && spinner.getSelectedItemPosition() == 1){
                    String [] names = {name1,name2,name3,name4};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,names);
                    spinner.setAdapter(adapter);
                    ma2.nm2 = ntx.getText().toString();
                    ma2.salvar = true;
                    Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(i);
                }
                if(name3.equals(spinnerstring)&& !ntx.getText().toString().equals("") && spinner.getSelectedItemPosition() == 2){
                    String [] names = {name1,name2,name3,name4};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,names);
                    spinner.setAdapter(adapter);
                    ma2.nm3 = ntx.getText().toString();
                    ma2.salvar = true;
                    Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(i);
                }
                if(name4.equals(spinnerstring)&& !ntx.getText().toString().equals("") && spinner.getSelectedItemPosition() == 3){
                    String [] names = {name1,name2,name3,name4};
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,names);
                    spinner.setAdapter(adapter);
                    ma2.nm4 = ntx.getText().toString();
                    ma2.salvar = true;
                    Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(i);
                }
                salvar = true;

            }
        });

        btt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                spinnerstring2 = (String)spinner2.getSelectedItem();
                if(spinnerstring2.equals(tema2)){
                    mdc = tema1;
                    tema1=tema2;
                    tema2= mdc;
                }
                if(spinnerstring2.equals(tema3)){
                    mdc = tema1;
                    tema1=tema3;
                    tema3= mdc;
                }
                if(spinnerstring2.equals(tema4)){
                    mdc = tema1;
                    tema1=tema4;
                    tema4= mdc;
                }
                if(spinnerstring2.equals(custom)){
                    mdc = tema1;
                    tema1=custom;
                    custom= mdc;
                }

                salvar = true;

                savetema1();readtema1();
                savetema2();readtema2();
                savetema3();readtema3();
                savetema4();readtema4();
                savecustom();readcustom();

                if(tema1.equals("Azul")){
                    act3.setBackgroundResource(R.drawable.pretoblue);
                    ma2.salvar = true;
                    Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(i);
                }
                if(tema1.equals("Vermelho")){
                    act3.setBackgroundResource(R.drawable.pretored3);
                    ma2.salvar = true;
                    Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(i);
                }
                if(tema1.equals("Verde")){
                    act3.setBackgroundResource(R.drawable.verde1);
                    ma2.salvar = true;
                    Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(i);
                }
                if(tema1.equals("Amarelo")){
                    ma2.salvar = true;
                    act3.setBackgroundResource(R.drawable.amarelo3);
                    Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(i);
                }
                if(tema1.equals("Galeria")){
                    ma2.salvar = true;
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 123);
                }

            }
        });

        bt4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(i);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 123) {
                Uri imagemSelecionada = data.getData();


                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagemSelecionada);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();

                Bitmap image = (BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
//                act3.setImageBitmap(image);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 100, baos);

                byte[] b = baos.toByteArray();
                custombytes = Base64.encodeToString(b, Base64.DEFAULT);
                MainActivity2.lytbytes = Base64.encodeToString(b, Base64.DEFAULT);
                savecustombytes();

                Drawable image2 = new BitmapDrawable(BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));

                act3.setForeground(image2);

                Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(i);
            }
        }
    }

    //save temas
    private void savecustombytes() {
        SharedPreferences sharedPreferences = getSharedPreferences("custombytes", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("custombytes", custombytes);
        editor.commit();
    }
    private void readcustombytes() {
        SharedPreferences sharedPreferences = getSharedPreferences("custombytes", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("custombytes", "");
        if (var != "") {
            custombytes = var;
        }
    }
    private void savecustom() {
        SharedPreferences sharedPreferences = getSharedPreferences("custom", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("custom", custom);
        editor.commit();
    }
    private void readcustom() {
        SharedPreferences sharedPreferences = getSharedPreferences("custom", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("custom", "");
        if (var != "") {
            custom = var;
        }
    }
    private void savetema1() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema1", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tema1", tema1);
        editor.commit();
    }

    private void readtema1() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema1", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tema1", "");
        if (var != "") {
            tema1 = var;
        }
    }
    private void savetema2() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema2", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tema2", tema2);
        editor.commit();
    }

    private void readtema2() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema2", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tema2", "");
        if (var != "") {
            tema2 = var;
        }
    }
    private void savetema3() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema3", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tema3", tema3);
        editor.commit();
    }

    private void readtema3() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema3", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tema3", "");
        if (var != "") {
            tema3 = var;
        }
    }
    private void savetema4() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema4", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tema4", tema4);
        editor.commit();
    }

    private void readtema4() {
        SharedPreferences sharedPreferences = getSharedPreferences("tema4", Context.MODE_PRIVATE);
        String var = sharedPreferences.getString("tema4", "");
        if (var != "") {
            tema4 = var;
        }
    }
}





















    //save nomes
//    private void savenome1() {
//        SharedPreferences sharedPreferences = getSharedPreferences("name1", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("name1", name1);
//        editor.commit();
//    }
//
//    private void readnome1() {
//        SharedPreferences sharedPreferences = getSharedPreferences("name1", Context.MODE_PRIVATE);
//        String var = sharedPreferences.getString("name1", "");
//        if (var != "") {
//            name1 = var;
//        }
//    }
//    private void savenome2() {
//        SharedPreferences sharedPreferences = getSharedPreferences("name2", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("name2", name2);
//        editor.commit();
//    }
//
//    private void readnome2() {
//        SharedPreferences sharedPreferences = getSharedPreferences("name2", Context.MODE_PRIVATE);
//        String var = sharedPreferences.getString("name2", "");
//        if (var != "") {
//            name2 = var;
//        }
//    }
//    private void savenome3() {
//        SharedPreferences sharedPreferences = getSharedPreferences("name3", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("name3", name3);
//        editor.commit();
//    }
//
//    private void readnome3() {
//        SharedPreferences sharedPreferences = getSharedPreferences("name3", Context.MODE_PRIVATE);
//        String var = sharedPreferences.getString("name3", "");
//        if (var != "") {
//            name3 = var;
//        }
//    }
//    private void savenome4() {
//        SharedPreferences sharedPreferences = getSharedPreferences("name4", Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("name4", name4);
//        editor.commit();
//    }
//
//    private void readnome4() {
//        SharedPreferences sharedPreferences = getSharedPreferences("name4", Context.MODE_PRIVATE);
//        String var = sharedPreferences.getString("name4", "");
//        if (var != "") {
//            name4 = var;
//        }
//    }