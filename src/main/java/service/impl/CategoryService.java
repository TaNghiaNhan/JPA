package service.impl;

import dao.impl.CategoryDao;
import entity.Category;
import service.ICategoryService;


import java.util.List;

public class CategoryService implements ICategoryService {

    CategoryDao dao = new CategoryDao();

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        return dao.findAll(page, pagesize);
    }

    @Override
    public List<Category> findByCategoryname(String catname) {
        return dao.findByCategoryname(catname);
    }

    @Override
    public List<Category> findAll() {
        return dao.findAll();
    }

    @Override
    public Category findById(int cateid) {
        return dao.findById(cateid);
    }

    @Override
    public void delete(int cateid) throws Exception {
        dao.delete(cateid);
    }

    @Override
    public void update(Category category) {
        dao.update(category);
    }

    @Override
    public void insert(Category category) throws Exception {
        dao.insert(category);
    }

    public static void main(String[] args) {
        CategoryService service = new CategoryService();
        Category category = new Category();
        category.setCategoryname("Iphone14ProMax");
        category.setImages("Iphone14ProMax.jpg");
        category.setStatus(0);
        try {
            service.insert(category);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
