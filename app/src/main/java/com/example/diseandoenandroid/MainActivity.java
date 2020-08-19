package com.example.diseandoenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private Button buttonSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //forzar y cargar icono en el action bar
        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        etName = (EditText) findViewById(R.id.etName);
        buttonSecondActivity = (Button) findViewById(R.id.buttonSecondActivity);

        buttonSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                if (name != null && !name.isEmpty()){
                    Intent intentSA = new Intent(MainActivity.this, SecondActivity.class);
                    intentSA.putExtra("name", name);
                    startActivity(intentSA);
                }else{
                    Toast.makeText(MainActivity.this, "No reconozco tu nombre :(", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}