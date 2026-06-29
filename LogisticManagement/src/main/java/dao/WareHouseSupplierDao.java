package dao;

import java.util.List;

import BuildingSystem.Supplier;
import BuildingSystem.WarehouseSupplier;
import BuildingSystem.CompanyDepartments.WareHouse;
import Utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class WareHouseSupplierDao {

    public static boolean save(WarehouseSupplier ws) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(ws);
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

    public static boolean update(WarehouseSupplier ws) {

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(ws);
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
            WarehouseSupplier ws =
                    em.find(WarehouseSupplier.class, id);
            if(ws != null) {
                em.remove(ws);
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

    public static WarehouseSupplier findById(long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(WarehouseSupplier.class, id);
        } finally {

            em.close();
        }
    }

    public static List<WarehouseSupplier> findAll() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            TypedQuery<WarehouseSupplier> query =
                    em.createQuery(
                            "FROM WarehouseSupplier",
                            WarehouseSupplier.class
                    );
            return query.getResultList();

        } finally {
            em.close();
        }
    }

    // depoya ait tedarikçiler

    public static List<Supplier>
    findByWarehouseId(long warehouse_id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Supplier> query =
                    em.createQuery(
                            """
                            SELECT ws.tedarikci
                            FROM WarehouseSupplier ws
                            WHERE ws.saglayici_deposu.id = :warehouse_id
                            """,
                            Supplier.class
                    );

            query.setParameter("warehouse_id", warehouse_id);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // tedarikçiye ait depolar

    public static List<WareHouse>
    findBySupplierId(long supplier_id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<WareHouse> query =
                    em.createQuery(
                            """
                            SELECT ws.saglayici_deposu
                            FROM WarehouseSupplier ws
                            WHERE ws.tedarikci.id = :supplier_id
                            """,
                            WareHouse.class
                    );
            query.setParameter("supplier_id", supplier_id);
            return query.getResultList();

        } finally {
            em.close();
        }
    }
}