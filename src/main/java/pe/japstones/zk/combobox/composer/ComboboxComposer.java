package pe.japstones.zk.combobox.composer;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import pe.japstones.zk.combobox.model.CategoryModel;
import pe.japstones.zk.combobox.model.SubCategoryModel;
import pe.japstones.zk.combobox.service.CategoryService;
import pe.japstones.zk.combobox.service.impl.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ComboboxComposer extends SelectorComposer<Window> {

    @Wire("#bd")
    private Bandbox bd;

    @Wire("#listbox")
    private Listbox listbox;

    @Wire("#btn")
    private Button button;

    private CategoryService categoryService;

    @Override
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        listbox.addEventListener("onSelect", onSelectListener());
        button.addEventListener("onClick", onClick());

        categoryService = new CategoryServiceImpl();

        final Bandpopup dropdown = bd.getDropdown();
        if (dropdown.getFirstChild() instanceof Listbox) {
            Listbox listbox = (Listbox) dropdown.getFirstChild();
            listbox.setModel(getSimpleGroup());
            listbox.setItemRenderer(getListitemRenderer());
        }
    }

    protected EventListener<? extends Event> onSelectListener() {
        return (event) -> {
            final Listitem selectedItem = listbox.getSelectedItem();
            if (selectedItem instanceof Listgroup) {
                Listgroup group = (Listgroup) selectedItem;
                group.setOpen(!group.isOpen());

                selectedItem.setSelected(false);
                listbox.getPagingChild().setTotalSize(listbox.getVisibleItemCount());

            } else {
                final SubCategoryModel subCategory = (SubCategoryModel) listbox.getModel().getElementAt(selectedItem.getIndex());
                bd.setValue(subCategory.getSubCategoryName() + "  [ " + subCategory.getWeight() + " ]");
                bd.close();
            }
            listbox.renderAll();
        };
    }

    protected EventListener<? extends Event> onClick(){
        return (event) -> {
            final int selectedIndex = listbox.getSelectedIndex();

            final ListModel<SubCategoryModel> model = listbox.getModel();

            final SubCategoryModel selected = model.getElementAt(selectedIndex);

            Messagebox.show(selected.getSubCategoryName() + " - " + selected.getWeight(), "Information", Messagebox.OK, Messagebox.INFORMATION);
        };
    }

    private SimpleGroupsModel getSimpleGroup() {
        final List<CategoryModel> heads = categoryService.getAllCategories();
        final List<List<SubCategoryModel>> data = getAllGroupedSubCategories(heads);
        return new SimpleGroupsModel(data, heads);
    }

    private ListitemRenderer getListitemRenderer() {
        return (listItem, value, index) -> {
            listItem.getChildren().clear();
            final Listcell cell = new Listcell();
            cell.setClass("z-row");

            if (listItem instanceof Listgroup) {
                final CategoryModel category = (CategoryModel) value;
                final String iconClass = ((Listgroup) listItem).isOpen() ? "z-icon-caret-down z-tree-open" : "z-icon-caret-right z-tree-close";
                final String iconStyle = "float: right;";
                cell.appendChild(new Html(category.getCategoryName() + "<span class='" + iconClass + "' style='"+ iconStyle+"'></span>"));
                listItem.setClass("z-category");
            } else {
                final SubCategoryModel subCategory = (SubCategoryModel) value;
                Html span = new Html("&nbsp;");
                span.setClass("z-tree-line z-tree-spacer");
                cell.appendChild(span);

                span = new Html("&nbsp;");
                span.setClass("z-tree-line z-tree-spacer");
                cell.appendChild(span);

                cell.appendChild(new Html(subCategory.getSubCategoryName() + "<span class='z-weight'>" + subCategory.getWeight() + "</span>"));
                listItem.setClass("z-subcategory");
            }

            listItem.appendChild(cell);
        };
    }

    private List<List<SubCategoryModel>> getAllGroupedSubCategories(final List<CategoryModel> categories) {
        final List<List<SubCategoryModel>> allSubCategories = new ArrayList<>();
        categories.stream().map(CategoryModel::getSubCategories).forEach(allSubCategories::add);
        return allSubCategories;
    }

}
