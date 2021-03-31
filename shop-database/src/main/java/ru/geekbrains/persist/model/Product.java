package ru.geekbrains.persist.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true, length = 256)
    private String name;

    @Column(length = 2048)
    private String description;


    @Column(nullable = false)
    private BigDecimal cost;


    @ManyToOne
    private KindProduct kindProduct;



    public Product(String name, String description, BigDecimal cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Product() {
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


    public KindProduct getKindProduct() {
        return kindProduct;
    }

    public void setKindProduct(KindProduct kindProduct) {
        this.kindProduct = kindProduct;
    }
}
