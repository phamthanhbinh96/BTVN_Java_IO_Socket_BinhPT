/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2;

/**
 *
 * @author P.T.B
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	
	
    public static void main(String[] args)  {
        ServerSocket server;
        Socket socket=null;
        try {
            server = new ServerSocket(9669);
            System.out.println("Waiting for client on port 9669");
            //accept multi-client connect to this serversocket
            while(true) {
                socket = server.accept();
                System.out.println("Client accepted");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                Thread thread= new ClientHandler(dis,dos,socket);
                thread.start();
            }
        } catch (IOException e) {
            try {
                    socket.close();
            } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }	
}
