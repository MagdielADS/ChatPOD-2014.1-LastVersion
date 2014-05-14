/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbmi.com.chat.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mbmi.com.chat.dao.UserChatDAO;

/**
 *
 * @author magdiel-bruno
 */
public class ServerChat {
    ServerSocket server;
    Socket socket;
    
    public ServerChat(){
        try {
            server = new ServerSocket(10999);
            
            while(true){
                socket = server.accept();
                Thread t = new Thread(new ServerChatRunnable());
                t.start();
            }
        } catch (IOException ex) {
            return;
        }
    }
    
    private class ServerChatRunnable implements Runnable{

        @Override
        public void run() {
            try {
                String msg;

                while (true) {
                    PrintWriter write = new PrintWriter(socket.getOutputStream());
                    InputStreamReader input = new InputStreamReader(socket.getInputStream());
                    BufferedReader buff = new BufferedReader(input);
                    UserChatDAO udb = new UserChatDAO();

                    msg = buff.readLine();
                    if (msg != null) {
                        String[] resp = msg.split("@");

                        if (resp[0].equalsIgnoreCase("-l")) {
                            String[] log = resp[1].split("&");
                            if (udb.searchUserChat(log[0], log[1]) != null) {
                                write.println("OK");
                                write.flush();
                                System.out.println("Chat-POD-Server-Message> User " + log[0] + " connected");
                            } else {
                                write.println("NO OK");
                                write.flush();
                            }
                        } else if (resp[0].equalsIgnoreCase("-m")) {
                            String[] mens = resp[1].split("&");
                            System.out.println("--Chat-POD-"+mens[0]+"-Message> "+mens[1]);
                        }else{
                            System.out.println("Chat-POD-Server-Message> Invalid communication protocol");
                        }
                    }
                }
            } catch (IOException ex) {
                return;
            }
        }
        
    }
}
