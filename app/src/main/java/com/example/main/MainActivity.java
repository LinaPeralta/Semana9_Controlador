package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button granizadoBtn;
    private Button papitasBtn;
    private Button bowlBtn;
    private Button brownieBtn;

    private UDPconnection udp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        granizadoBtn = findViewById(R.id.granizadoBtn);
        papitasBtn = findViewById(R.id.papitasBtn);
        bowlBtn = findViewById(R.id.bowlBtn);
        brownieBtn = findViewById(R.id.brownieBtn);



        udp = new UDPconnection();
        udp.setObserver(this);
        udp.start();


        granizadoBtn.setOnClickListener(
                (v) -> {
                    udp.sendMessage("Hola desde android");

                });
    }

    public void recibir(String mensaje){
        runOnUiThread(()->{
            Log.d(">>>",mensaje);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        });


    }

}