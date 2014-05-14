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
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author magdiel-bruno
 */
public class Chat {
    String login, password;
    List<String> ips = new ArrayList<>();

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void startServer(){
        ServerChat sc = new ServerChat();
    }
    
    public boolean existIp(String ipReceived){
        boolean exist = false;
        for(String ip : this.ips){
            if(ip.equalsIgnoreCase(ipReceived)){
                exist = true;
            }
        }
        return exist;
    }
    
    public void sendMessage(String message){
        try {
            String[] messages = message.split("#");
            Socket s = new Socket(messages[0], 10999);
            PrintWriter write = new PrintWriter(s.getOutputStream());
            InputStreamReader input = new InputStreamReader(s.getInputStream());
            BufferedReader buff = new BufferedReader(input);
            
            if(!existIp(messages[0])){
                write.println("-l@"+getLogin()+"&"+getPassword());
                write.flush();
                String msg = buff.readLine();
                if(msg.equalsIgnoreCase("OK")){
                    System.out.println("Chat-POD-User-Message> Successful connection");
                    ips.add(messages[0]);
                    write.println("-m@" + getLogin() + "&" + messages[1]);
                    write.flush();
                    System.out.println("--Chat-POD-User-Message> You say: " + messages[1]);
                }else{
                    System.out.println("Chat-POD-User-Message> Invalid username or password");
                }
            }else{
                write.println("-m@"+getLogin()+"&"+messages[1]);
                write.flush();
                System.out.println("--Chat-POD-User-Message> You say: "+messages[1]);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    private static class ChatRunnable implements Runnable{

        @Override
        public void run() {
            Chat c = new Chat();
            c.startServer();
        }
        
    }
    
    public static void main(String[] args) {
        Chat c = new Chat();
        System.out.println("Chat-POD-Server-Message> Waiting for connection...");
        Scanner in = new Scanner(System.in);
        String msg = "";

        System.out.print("Chat-POD-User-Message> Enter your username: ");
        String login = in.nextLine();
        System.out.print("Chat-POD-User-Message> Enter your password: ");
        String pass = in.nextLine();
        c.setLogin(login);
        c.setPassword(pass);
        
        Thread t = new Thread(new ChatRunnable());
        t.start();
        
        System.out.println("--------------------------------------------------");
        System.out.println("Chat-POD-User-Message> Starting communication...");
        System.out.println("--------------------------------------------------");
        
        
        //
        //Comando para enviar mensagem:
        //messagefrom-ipDoServidor#mensagem
        //
        
        
        
        
        
        while (!msg.equalsIgnoreCase("CLOSE")) {
            msg = in.nextLine();
            String[] controls = msg.split("-");
            if (controls[0].equalsIgnoreCase("MESSAGEFROM")) {
                c.sendMessage(controls[1]);
            }else{
                System.out.println("'"+msg+"'"+" is not recognized as a internal or external command.");
            }
        }
    }
}
