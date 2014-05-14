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
        UserChat u = new UserChat();
        UserChatDAO udb = new UserChatDAO();
        
//        u.setLogin("arigarcia");
//        u.setPassword("123456");
//        udb.persist(u);
//        
//        u.setLogin("fernan");
//        u.setPassword("123456");
//        udb.persist(u);
//        
//        u.setLogin("magdidiel");
//        u.setPassword("123456");
//        udb.persist(u);
        
        u.setLogin("filipe");
        u.setPassword("jhv654");
        udb.persist(u);
    }
}
