package pe.japstones.zk.combobox.model;

public class SubCategoryModel {
    private int subCategoryId;
    private String subCategoryName;
    private int weight;

    public SubCategoryModel() {
    }

    public SubCategoryModel(final int subCategoryId, final String subCategoryName, final int weight) {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.weight = weight;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
