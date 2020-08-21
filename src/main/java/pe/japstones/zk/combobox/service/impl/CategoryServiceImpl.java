package pe.japstones.zk.combobox.service.impl;

import pe.japstones.zk.combobox.model.CategoryModel;
import pe.japstones.zk.combobox.model.SubCategoryModel;
import pe.japstones.zk.combobox.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<CategoryModel> getAllCategories() {

        CategoryModel c1 = new CategoryModel();
        c1.setCategoryId(100);
        c1.setCategoryName("Category 100");
        List<SubCategoryModel> sc1 = new ArrayList<>();
        sc1.add(new SubCategoryModel(101, "Sub category 101", 20));
        c1.setSubCategories(sc1);

        CategoryModel c2 = new CategoryModel();
        c2.setCategoryId(100);
        c2.setCategoryName("Category 200");
        List<SubCategoryModel> sc2 = new ArrayList<>();
        sc2.add(new SubCategoryModel(201, "Sub category 201", 50));
        sc2.add(new SubCategoryModel(202, "Sub category 202", 70));
        c2.setSubCategories(sc2);

        CategoryModel c3 = new CategoryModel();
        c3.setCategoryId(100);
        c3.setCategoryName("Category 300");
        List<SubCategoryModel> sc3 = new ArrayList<>();
        sc3.add(new SubCategoryModel(301, "Sub category 301", 40));
        sc3.add(new SubCategoryModel(302, "Sub category 302", 50));
        sc3.add(new SubCategoryModel(303, "Sub category 303", 90));
        c3.setSubCategories(sc3);

        CategoryModel c4 = new CategoryModel();
        c4.setCategoryId(100);
        c4.setCategoryName("Category 400");
        List<SubCategoryModel> sc4 = new ArrayList<>();
        sc4.add(new SubCategoryModel(401, "Sub category 401", 20));
        sc4.add(new SubCategoryModel(402, "Sub category 402", 20));
        sc4.add(new SubCategoryModel(403, "Sub category 403", 50));
        sc4.add(new SubCategoryModel(404, "Sub category 404", 40));
        c4.setSubCategories(sc4);

        return List.of(c1, c2, c3, c4);
    }
}
