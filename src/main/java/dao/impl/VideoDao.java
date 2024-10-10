package dao.impl;

import dao.IVideoDao;
import entity.Video;
import config.JPAConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class VideoDao implements IVideoDao {

    @Override
    public void insert(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            enma.persist(video); // Lưu video vào cơ sở dữ liệu
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            throw e;
        } finally {
            if (enma.isOpen()) {
                enma.close(); // Đóng EntityManager
            }
        }
    }

    @Override
    public void delete(Video video) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            if (video != null) {
                video = enma.merge(video); // Hợp nhất video vào EntityManager
                enma.remove(video); // Xóa video
            } else {
                throw new Exception("Không tìm thấy video");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            throw e;
        } finally {
            if (enma.isOpen()) {
                enma.close(); // Đóng EntityManager
            }
        }
    }

    @Override
    public void update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            enma.merge(video); // Cập nhật video
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback(); // Hoàn tác giao dịch nếu có lỗi
            }
            throw e;
        } finally {
            if (enma.isOpen()) {
                enma.close(); // Đóng EntityManager
            }
        }
    }

    @Override
    public Video findById(String id) {
        EntityManager enma = JPAConfig.getEntityManager();
        return enma.find(Video.class, id); // Tìm video theo ID
    }

    @Override
    public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList(); // Trả về danh sách tất cả video
    }

    @Override
    public List<Video> findListById(String categoryId) {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createQuery("SELECT v FROM Video v WHERE v.category.categoryID = :cateId", Video.class);
        /*
        * Cái Video là tên class
        * v.category.categoryID v. tên class entity á. atttribute
        * này biến truyền vào cateId trùng với cái này query.setParameter("cateId" set giá trị Integer.parseInt(categoryId)
        * */
        query.setParameter("cateId", Integer.parseInt(categoryId)); // Chuyển đổi categoryId từ String sang int
        return query.getResultList(); // Trả về danh sách video theo categoryId
    }

}
