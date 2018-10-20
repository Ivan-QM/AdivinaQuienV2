/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Alumno
 */
public class chat extends Thread{
    private static JTextArea Output;
    private static String IP;
    public chat(JTextArea Output, String IP){
        this.Output = Output;
        this.IP = IP;
    }
    public static void send(String message) throws SocketException, UnknownHostException, IOException{
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(IP);
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = message;
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        if (Output.getText().equalsIgnoreCase("")) {
           Output.setText("Me: "+modifiedSentence);
        }
        else{
            Output.setText(Output.getText()+"\n"+"Me: "+modifiedSentence);
        }
        clientSocket.close();
    }
    @Override
    public void run(){
        DatagramSocket serverSocket = null;
        try {
            serverSocket = new DatagramSocket(9876);
        } catch (SocketException ex) {
            Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(true){
            try {
                byte[] receiveData = new byte[2048];
                byte[] sendData = new byte[2048];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                if (this.Output.getText().equalsIgnoreCase("")) {
                    this.Output.setText(receivePacket.getAddress()+": "+sentence);
                }
                else{
                    this.Output.setText(this.Output.getText()+"\n"+receivePacket.getAddress()+": "+sentence);
                }
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
