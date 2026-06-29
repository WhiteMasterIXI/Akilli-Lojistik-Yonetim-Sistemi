package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import ShoppingSystem.Products.Product;
import Utils.JPAUtil;

public class ProductDao {

    public static boolean save(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(product);
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

    public static boolean update(Product product) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(product);
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

            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
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

    public static Product findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public static List<Product> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<Product> query =
                    em.createQuery("FROM Product", Product.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public static Product findByName(String name) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            List<Product> list = em.createQuery(
                    "SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:name)",
                    Product.class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();

            if (list.isEmpty()) return null;

            return list.get(0);

        } finally {
            em.close();
        }
    }
}