package ru.geekbrains.persist.model.order;


import ru.geekbrains.persist.model.accounts.User;
import ru.geekbrains.persist.model.goods.Product;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orderitems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Order order;

    @OneToOne(targetEntity =User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @Column(name = "count")
    private Short count;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "totalCost")
    private BigDecimal totalCost;

    public OrderItem(Long id, Order order, Product product, Short count, BigDecimal discount, BigDecimal totalCost) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.count = count;
        this.discount = discount;
        this.totalCost = calcTotalCost();
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Short getCount() {
        return count;
    }

    public void setCount(Short count) {
        this.count = count;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    private BigDecimal calcTotalCost() {
        return (getProduct().getPrice().multiply(BigDecimal.valueOf(count)).multiply(BigDecimal.valueOf(1).subtract(discount)));
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", count=" + count +
                ", discount=" + discount +
                ", totalCost=" + totalCost +
                '}';
    }
}
