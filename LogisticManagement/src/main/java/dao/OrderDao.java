package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import ShoppingSystem.Order;
import ShoppingSystem.orderStatus;
import Utils.JPAUtil;

public class OrderDao {

    public static boolean save(Order order) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(order);
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

    public static boolean update(Order order) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(order);
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

    public static boolean delete(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Order order = em.find(Order.class, id);
            if (order != null) {
                em.remove(order);
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

    public static Order findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Order.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Order> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Order> query =
                    em.createQuery("FROM Order", Order.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Order> findByUser(long userId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT o FROM Order o WHERE o.user.id = :uid",
                    Order.class)
                    .setParameter("uid", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Order> findByStatus(orderStatus status) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT o FROM Order o WHERE o.status = :st",
                    Order.class)
                    .setParameter("st", status)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}