package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ShoppingSystem.Products.OrderedProduct;
import ShoppingSystem.Products.RequestProduct;
import Utils.JPAUtil;

public class OrderedProductDao {

    public static boolean save(OrderedProduct req) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(req);
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

    public static boolean update(OrderedProduct ord) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(ord);
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

            OrderedProduct req = em.find(OrderedProduct.class, id);
            if (req != null) {
                em.remove(req);
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

    public static OrderedProduct findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(OrderedProduct.class, id);
        } finally {
            em.close();
        }
    }

    public static List<OrderedProduct> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<OrderedProduct> query =
                    em.createQuery("FROM ReqProduct", OrderedProduct.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<OrderedProduct> findByUser(long userId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT r FROM OrderedProduct r WHERE r.user.id = :uid",
                    OrderedProduct.class)
                    .setParameter("uid", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public static List<OrderedProduct> findByProduct(long productId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT r FROM OrderedProduct r WHERE r.urun.id = :pid",
                    OrderedProduct.class)
                    .setParameter("pid", productId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public static List<OrderedProduct> findByOrderId(long order_id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT r FROM OrderedProduct r WHERE r.order.id = :pid",
                    OrderedProduct.class)
                    .setParameter("pid", order_id)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}