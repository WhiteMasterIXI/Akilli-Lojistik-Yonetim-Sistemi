package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import ShoppingSystem.Products.RequestProduct;
import Utils.JPAUtil;

public class ReqProductDao {

    public static boolean save(RequestProduct req) {
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

    public static boolean update(RequestProduct req) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(req);
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
        
        System.out.println("request_product_id : " + id);

        try {
            tx.begin();

            RequestProduct req = em.find(RequestProduct.class, id);
            if (req != null) {
                em.remove(req);
                System.out.println("urun bulundu");
            }

            tx.commit();
            System.out.println("islem tamamlandi");
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public static RequestProduct findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(RequestProduct.class, id);
        } finally {
            em.close();
        }
    }

    public static List<RequestProduct> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<RequestProduct> query =
                    em.createQuery("FROM RequestProduct", RequestProduct.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<RequestProduct> findByProduct(long productId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT r FROM RequestProduct r WHERE r.urun.id = :pid",
                    RequestProduct.class)
                    .setParameter("pid", productId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    public static List<RequestProduct> findByCartId(long cart_id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT r FROM RequestProduct r WHERE r.cart.id = :pid",
                    RequestProduct.class)
                    .setParameter("pid", cart_id)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    
    
}