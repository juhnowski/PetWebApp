/*
 * Тестовое задание для Вадима
 */
package petweb;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Вспомогательный класс для работы с БД посредством Hibernate
 * @author Илья Юхновский
 */
public class PetsHelper {
    Session session = null;
    
    public PetsHelper() {
        this.session = NewHibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    /**
     * Получить список питомцев хозяина
     * @param owner хозяин
     * @return список питомцев
     */
    public List getPetsByOwner(String owner){
        
        List<Pets> petsList = null;
        
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Pets as pet where pet.owner='"+owner+"'");
            petsList = (List<Pets>) q.list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return petsList;
    }

    /**
     * Получить детали по питомцу по кличке
     * @param nickname кличка
     * @return питомец
     */
    public Pets getPetsByNickname(String nickname){
        
        Pets pets = null;
        
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Pets as pet where pet.nickname='"+nickname+"'");
            pets = (Pets) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return pets;
    }
    
    /**
     * Получить список питомцев по виду
     * @param speciesName вид
     * @return список питомцев данного вида
     */
    public List getPetsBySpecies(String speciesName){
        
        List<Pets> petsList = null;
        
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Pets as pet where pet.speciesName='"+speciesName+"'");
            petsList = (List<Pets>) q.list();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return petsList;
    }
    
    
    public Users getUserByLogin(String login){
        
        Users users = null;
        
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            
            Query q = session.createQuery("from Users as user where user.login='"+login+"'");
            users = (Users) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        return users;
    }
    
    public boolean validateUserByLogin(String login){
        
        Users users = null;
        boolean result = false;
        
        try{
            org.hibernate.Transaction tx = session.beginTransaction();
            
            Query q = session.createQuery("from Users as user where user.login='"+login+"'");
            users = (Users) q.uniqueResult();
            if (users != null) {
                result=true;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return result;
    }
    
    public boolean addUser(String login, String password){
        Users u = null;
        try {
            session.beginTransaction();
            u = new Users(login, password);
            session.save(u);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace(System.out);
            return false;
        } finally {
            session.flush();
        }
        return true;
    }
    
    public boolean addPet(String nickname, String speciesName, Date birthdate, boolean male, String owner){
        Pets p = null;
        try {
            session.beginTransaction();
            p = new Pets(nickname, speciesName, birthdate, male, owner);
            session.save(p);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace(System.out);
            return false;
        } finally {
            session.flush();
        }
        return true;
    }
    
    public boolean addSpecies(String name){
        Species s = null;
        try {
            session.beginTransaction();
            s = new Species(name);
            session.save(s);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace(System.out);
            return false;
        } finally {
            session.flush();
        }
        return true;
    }
    
    
}
