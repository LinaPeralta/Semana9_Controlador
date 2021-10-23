package com.example.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import events.IObserver;
import model.Dato;

public class MainActivity extends AppCompatActivity implements IObserver {

    private Button granizadoBtn;
    private Button papitasBtn;
    private Button bowlBtn;
    private Button brownieBtn;

    private UDPconnection udp;

    private String json;
    Dato datos;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        granizadoBtn = findViewById(R.id.granizadoBtn);
        papitasBtn = findViewById(R.id.papitasBtn);
        bowlBtn = findViewById(R.id.bowlBtn);
        brownieBtn = findViewById(R.id.brownieBtn);


        datos =  new Dato();
        gson = new Gson();

        udp = new UDPconnection(this);
       // udp.setObserver(this);
        udp.start();


        granizadoBtn.setOnClickListener(
                (v) -> {
                    datos.setOrden("granizado");
                    json = gson.toJson(datos);
                    udp.sendMessage(json);
                });

        papitasBtn.setOnClickListener(
                (v) -> {
                    datos.setOrden("papitas");
                    json = gson.toJson(datos);
                    udp.sendMessage(json);
                });

        bowlBtn.setOnClickListener(
                (v) -> {
                    datos.setOrden("bowl");
                    json = gson.toJson(datos);
                    udp.sendMessage(json);
                });

        brownieBtn.setOnClickListener(
                (v) -> {
                    datos.setOrden("brownie");
                    json = gson.toJson(datos);
                    udp.sendMessage(json);
                });
    }

    /*public void recibir(String mensaje){
        runOnUiThread(()->{
            Log.d(">>>",mensaje);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        });
    }*/


    @Override
    public void recibirMensaje(String mensaje) {
        runOnUiThread(()->{
            Log.d(">>>",mensaje);
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        });
    }
}