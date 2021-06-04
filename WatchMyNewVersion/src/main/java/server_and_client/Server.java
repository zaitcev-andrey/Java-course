/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_and_client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author megaa
 */
public class Server
{
    int port = 3124;
    InetAddress host;
    
    Model m; // Она будет связывать между собой несколько сокетов. И в модели задаётся начальное время, которое начинает идти
    
    public Server()
    {
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try
        {
            ServerSocket ss = new ServerSocket(port, 0, host);
            System.out.println("Server is started");
            
            m = new Model();
            while(true)
            {
                Socket cs = ss.accept();
                System.out.println("Client is connected");
                
                WCS wcs = new WCS(cs, m);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args)
    {
        new Server();
    }
}
