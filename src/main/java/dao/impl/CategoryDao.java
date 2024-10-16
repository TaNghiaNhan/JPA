package dao.impl;

import config.JPAConfig;
import dao.ICategoryDao;
import entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import service.impl.CategoryService;

import java.util.List;

public class CategoryDao implements ICategoryDao{

    @Override
    public void insert(Category category) throws Exception {
        // Tạo EntityManager từ cấu hình JPA
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            // Bắt đầu giao dịch
            trans.begin();
            enma.persist(category);
            trans.commit();
        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace();

            // Cuối cùng, hoàn tác giao dịch nếu có lỗi
            if (trans.isActive()) {
                trans.rollback();
            }

            // Ném lại ngoại lệ để thông báo lỗi
            throw e;
        } finally {
            // Đóng EntityManager
            if (enma.isOpen()) {
                enma.close();
            }
        }
    }

    public void update(Category category) {
        // Tạo EntityManager từ cấu hình JPA
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            // Bắt đầu giao dịch
            trans.begin();
            // Sử dụng merge để cập nhật đối tượng vào cơ sở dữ liệu
            enma.merge(category);
            // Cam kết giao dịch
            trans.commit();
        } catch (Exception e) {
            // Xử lý lỗi
            e.printStackTrace();
            // Hoàn tác giao dịch nếu có lỗi
            if (trans.isActive()) {
                trans.rollback();
            }
            // Ném lại ngoại lệ để thông báo lỗi
            throw e;
        } finally {
            // Đóng EntityManager
            if (enma.isOpen()) {
                enma.close();
            }
        }
    }

    public void delete(int cateid) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            // enma.remove(category);
            Category category = enma.find(Category.class, cateid);
            if (category != null) {
                enma.remove(category);
            } else {
                throw new Exception("Không tìm thấy");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    public Category findById(int cateid) {
        EntityManager enma = JPAConfig.getEntityManager();
        Category category = enma.find(Category.class, cateid);
        return category;
    }

    public List<Category> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        return query.getResultList();
    }

    public List<Category> findByCategoryname(String catname) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.categoryname like :catname";

        TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
        query.setParameter("catname", "%" + catname + "%");
        return query.getResultList();
    }

    public List<Category> findAll(int page, int pagesize) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        query.setFirstResult(page * pagesize);
        query.setMaxResults(pagesize);
        return query.getResultList();
    }

    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT count(c) FROM Category c";
        Query query = enma.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
    }
}
