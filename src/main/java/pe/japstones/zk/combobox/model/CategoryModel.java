package pe.japstones.zk.combobox.model;

import java.util.List;

public class CategoryModel {
    private int categoryId;
    private String categoryName;
    private List<SubCategoryModel> subCategories;

    public CategoryModel() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SubCategoryModel> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryModel> subCategories) {
        this.subCategories = subCategories;
    }
}
