package ru.geekbrains.persist.model.order;

import ru.geekbrains.persist.model.accounts.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creationdate", nullable = false)
    private Date creationDate;

    @Column(name = "totalcost", nullable = false)
    private BigDecimal totalCost;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status",  nullable = false)
    private StatusOrder status;

    @OneToMany(
            mappedBy = "orderitems",
            cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    public Order(Date creationDate, BigDecimal totalCost, StatusOrder status, List<OrderItem> orderItems, User user) {
        this.creationDate = Calendar.getInstance().getTime();
        this.totalCost = calcTotalCost();
        this.status = status;
        this.orderItems = orderItems;
        this.user = user;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private BigDecimal calcTotalCost() {
        BigDecimal bigDecimal =BigDecimal.ONE;

        for (OrderItem item: orderItems
             ) {
            bigDecimal = bigDecimal.multiply(item.getTotalCost());
        }
        return bigDecimal;

    }
}
