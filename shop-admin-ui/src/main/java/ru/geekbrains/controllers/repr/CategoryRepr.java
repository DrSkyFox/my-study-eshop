package ru.geekbrains.controllers.repr;

import ru.geekbrains.persist.model.goods.Category;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryRepr implements Serializable {

    private long id;

    @NotEmpty
    private String name;

    private long productCount;

    public CategoryRepr(long id, String name, long productCount) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
    }

    public CategoryRepr(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }
}
