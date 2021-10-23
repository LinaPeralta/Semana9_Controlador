package com.example.main;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import events.IObserver;

public class UDPconnection extends Thread {
	
	private DatagramSocket socket;
	private String mensaje;
	private MainActivity mainActivity;
    private IObserver observer;



	public UDPconnection (IObserver observer){
		this.observer = observer;
	}
	
	public void run() {
		
		
		try {

			socket = new DatagramSocket(9000);
			
			while(true) {

				
				byte [] buffer = new byte[100];
				DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
				//System.out.println("Esperando datagrama");
				try {

					socket.receive(packet);


					mensaje = new String(packet.getData()).trim();
					System.out.println(mensaje);
					observer.recibirMensaje(mensaje);

				}catch (Exception e){
					e.printStackTrace();
				}



			}
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void sendMessage(String mensaje) {
		
		new Thread(()->{
			
			InetAddress IP;
			try {
				
				IP = InetAddress.getByName("192.168.0.252");
				DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length,IP,5000);
				socket.send(packet);
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}).start();
		
	}



}
