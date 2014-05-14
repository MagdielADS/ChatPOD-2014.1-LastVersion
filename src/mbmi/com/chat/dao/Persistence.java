/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbmi.com.chat.dao;

import mbmi.com.chat.model.UserChat;

/**
 *
 * @author magdiel-bruno
 */
public class Persistence {
    public static void main(String[] args) {
        
        
        //Nome da base de Dados: 
        
        
        UserChat u = new UserChat();
        UserChatDAO udb = new UserChatDAO();
        
        u.setLogin("arigarcia");
        u.setPassword("123456");
        udb.persist(u);
//        
        u.setLogin("fernan");
        u.setPassword("123456");
        udb.persist(u);
//        
        u.setLogin("magdidiel");
        u.setPassword("123456");
        udb.persist(u);
        
        u.setLogin("filipe");
        u.setPassword("jhv654");
        udb.persist(u);
        
        u.setLogin("dudu");
        u.setPassword("123456");
        udb.persist(u);
        
        u.setLogin("joao");
        u.setPassword("64527765");
        udb.persist(u);
        
        u.setLogin("elis");
        u.setPassword("123456");
        udb.persist(u);
        
        u.setLogin("samuel");
        u.setPassword("123456");
        udb.persist(u);
        
        u.setLogin("kelsonn");
        u.setPassword("0987654");
        udb.persist(u);
        
        u.setLogin("lulu");
        u.setPassword("64527765");
        udb.persist(u);
        
        u.setLogin("izabel");
        u.setPassword("8764432");
        udb.persist(u);
        
        u.setLogin("maciel");
        u.setPassword("ksrjhgf785");
        udb.persist(u);
        
        u.setLogin("ezequiel");
        u.setPassword("27364587");
        udb.persist(u);
        
        u.setLogin("wagner");
        u.setPassword("89274563");
        udb.persist(u);
        
        u.setLogin("antoin");
        u.setPassword("2394866");
        udb.persist(u);
        
        u.setLogin("joelanio");
        u.setPassword("123456");
        udb.persist(u);
    }
}
