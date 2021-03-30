package ru.geekbrains.persist.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kindproduct")
public class KindProduct {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true, length = 256)
    private String name;


    @OneToMany(mappedBy = "kindproduct")
    private List<Product> products;


    public KindProduct(String name) {
        this.name = name;
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




    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "KindProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
