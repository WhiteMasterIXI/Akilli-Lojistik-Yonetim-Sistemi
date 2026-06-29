package dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import Utils.JPAUtil;
import ShoppingSystem.Products.InventoryProduct; // senin package'e göre düzelt

public class InventoryProductDao {

    // 🔹 SAVE (stok ekle / kayıt oluştur)
    public static boolean save(InventoryProduct ip) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(ip);
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

    // 🔹 UPDATE (stok güncelle)
    public static void update(InventoryProduct ip) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(ip);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // 🔹 DELETE
    public static boolean delete(long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            InventoryProduct ip = em.find(InventoryProduct.class, id);
            if (ip != null) {
                em.remove(ip);
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

    // 🔹 FIND BY ID
    public static InventoryProduct findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(InventoryProduct.class, id);
        } finally {
            em.close();
        }
    }

    public static List<InventoryProduct> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<InventoryProduct> query =
                    em.createQuery("FROM InventoryProduct", InventoryProduct.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 🔹 FIND BY PRODUCT
    public static List<InventoryProduct> findByProduct(long productId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT i FROM InventoryProduct i WHERE i.product.id = :pid",
                    InventoryProduct.class)
                    .setParameter("pid", productId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public static List<InventoryProduct> findByDepoId(long depotId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT i FROM InventoryProduct i WHERE i.depo.id = :did",
                    InventoryProduct.class)
                    .setParameter("did", depotId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}