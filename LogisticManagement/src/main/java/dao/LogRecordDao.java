package dao;

import java.util.List;

import NotifySystem.LogRecord;
import NotifySystem.Notification;
import Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class LogRecordDao {
	
    public static boolean save(LogRecord n) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(n);
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

    public static boolean update(LogRecord n) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(n);
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

    public static boolean delete(long l) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            LogRecord n = em.find(LogRecord.class, l);
            if (n != null) {
                em.remove(n);
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


    public static LogRecord findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(LogRecord.class, id);
        } finally {
            em.close();
        }
    }

    public static List<LogRecord> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<LogRecord> query =
                    em.createQuery("FROM LogRecord", LogRecord.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<LogRecord> findByUser(long userId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT n FROM LogRecord n WHERE n.actor.id = :uid",
                    LogRecord.class)
                    .setParameter("uid", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
