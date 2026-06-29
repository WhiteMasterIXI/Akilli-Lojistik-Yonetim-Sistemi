package dao;

import java.util.List;

import PaymentSystem.Account;
import UserSystem.User;
import Utils.JPAUtil;
import jakarta.persistence.*;

public class AccountDao {

    public static boolean save(Account account) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(account); 
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public static boolean update(Account account) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(account);
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public static boolean delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Account account = em.find(Account.class, id);
            if (account != null) {
                em.remove(account);
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public static Account findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Account.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Account> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Account> query =
                    em.createQuery("SELECT a FROM Accounts a", Account.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Account> findByUser(User user) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Account> query = em.createQuery(
                "SELECT a FROM Accounts a WHERE a.user = :user", Account.class
            );
            query.setParameter("user", user);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}