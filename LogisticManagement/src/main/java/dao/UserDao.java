package dao;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import UserSystem.User;
import Utils.JPAUtil;

public class UserDao {

    public static Boolean save(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(user);
            tx.commit();
            
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return false;
    }

    public static boolean update(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return false;
    }

    public static boolean delete(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return false;
    }

    public static User findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public static List<User> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<User> query =
                    em.createQuery("FROM User", User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static User findByEmail(String email) {
    	
    	email.trim();
    	System.out.println(email);
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email",
                    User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static List<User> findByRoleId(long role_id) {
    	
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT u FROM User u WHERE u.role.id = :role_id",
                    User.class)
                    .setParameter("role_id", role_id)
                    .getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
}
