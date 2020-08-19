package com.example.diseandoenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private ImageButton imageButtonOk, imageButtonShare;
    private String name;
    private int edad, option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        //activar boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //recibe de second activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            name = bundle.getString("name");
            edad = bundle.getInt("edad");
            option = bundle.getInt("option");
        }

        imageButtonOk = (ImageButton) findViewById(R.id.imageButtonOk);
        imageButtonShare = (ImageButton) findViewById(R.id.imageButtonShare);

        imageButtonShare.setVisibility(View.INVISIBLE);

        imageButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageButtonOk.setVisibility(View.INVISIBLE);
                imageButtonShare.setVisibility(View.VISIBLE);
                String mensaje = crearMensaje(name, edad, option);
                Toast.makeText(ThirdActivity.this, mensaje, Toast.LENGTH_SHORT).show();

            }
        });

        imageButtonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(intent.EXTRA_TEXT, crearMensaje(name, edad, option));
                startActivity(intent);

            }
        });


    }

    private String crearMensaje(String name, int edad, int option){
        if (option == SecondActivity.saludo){
            return "Hola " + name+", ¿Cómo llevas esos " + edad + " años?";
        }else{
            return "Espero verte pronto " + name+", antes de que cumplas " + edad + " años?";
        }
    }
}