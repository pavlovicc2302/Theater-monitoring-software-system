/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.ClientThread;

/**
 *
 * @author Ana
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    private boolean end = false;

    public Server() {
        try {
            serverSocket = new ServerSocket(9001);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        while (!end) {
            try {
                System.out.println("Server ceka konekciju.");
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket, this);
                System.out.println("Klijent se povezao");
                clientThread.start();
            } catch (IOException ex) {
                System.out.println("Server je zaustavljen");
            }

        }
    }

    public void stopServer() {
        try {
            serverSocket.close();
            end = true;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
