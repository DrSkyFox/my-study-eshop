package ru.geekbrains.controller.repr;

import ru.geekbrains.persist.model.Product;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

//DTO Product
public class ProductRepr {


    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    private BigDecimal cost;

    public ProductRepr(String name, String description, BigDecimal cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.cost = product.getCost();
    }

    public ProductRepr() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
