/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mbmi.com.chat.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mbmi.com.chat.model.UserChat;

/**
 *
 * @author magdiel-bruno
 */
public class UserChatDAO {

    public void persist(UserChat u) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChatPOD-MagdielPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public UserChat searchUserChat(String login, String password){
        UserChat user = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ChatPOD-MagdielPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select u from UserChat u where u.login = :login and u.password = :password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            user = (UserChat) query.getSingleResult();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Chat-POD-Server-Message> Invalid username or password");
        } finally {
            em.close();
        }
        return user;
    }
    
}
