package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import NotifySystem.Notification;
import Utils.JPAUtil;

public class NotificationDao {

    public static boolean save(Notification n) {
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

    public static boolean update(Notification n) {
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

            Notification n = em.find(Notification.class, l);
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


    public static Notification findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Notification.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Notification> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Notification> query =
                    em.createQuery("FROM Notification", Notification.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Notification> findByUser(long userId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT n FROM Notification n WHERE n.alici.id = :uid",
                    Notification.class)
                    .setParameter("uid", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Notification> findUnreadNotifyByUser(long user_id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT n FROM Notification n WHERE n.durum = :st AND n.alici.id = :user_id",
                    Notification.class)
                    .setParameter("st", NotifySystem.notificationStatus.OKUNMADI)
                    .setParameter("user_id", user_id)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}