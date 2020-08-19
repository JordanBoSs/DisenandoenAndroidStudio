package com.example.diseandoenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private RadioButton rbSaludo, rbDespedida;
    private SeekBar seekBarEdad;
    private TextView textViewEdad;
    private Button btnthirdActivity;

    private int edad = 18;
    private String name = "";
    private final int edadMax = 60, edadMin = 16;

    public static final int saludo = 1, despedida = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //activar boton atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rbSaludo = (RadioButton) findViewById(R.id.radioButtonSaludo);
        rbDespedida = (RadioButton) findViewById(R.id.radioButtonDespedida);
        seekBarEdad = (SeekBar) findViewById(R.id.seekBarEdad);
        textViewEdad = (TextView) findViewById(R.id.textViewEdad);
        btnthirdActivity = (Button) findViewById(R.id.buttonThirdActivity);

        //recibir de Main activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            name = bundle.getString("name");
        }

        seekBarEdad.setProgress(edad);
        textViewEdad.setText(edad+"");

        //change para seekbar
        seekBarEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean b) {
                edad = currentAge;
                textViewEdad.setText(edad+"");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                edad = seekBar.getProgress();
                textViewEdad.setText(edad+"");

                if (edad > edadMax){
                    btnthirdActivity.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "Eres muy viejo :(", Toast.LENGTH_SHORT).show();
                }else if (edad < edadMin){
                    btnthirdActivity.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "Eres un bebé :(", Toast.LENGTH_SHORT).show();

                }else{
                    btnthirdActivity.setVisibility(View.VISIBLE);
                }

            }
        });

        btnthirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGoThirdActivity = new Intent(SecondActivity.this, ThirdActivity.class);
                intentGoThirdActivity.putExtra("name", name);
                intentGoThirdActivity.putExtra("edad", edad);
                int option = (rbSaludo.isChecked()) ? saludo : despedida;
                intentGoThirdActivity.putExtra("option", option);
                startActivity(intentGoThirdActivity);
            }
        });
    }
}