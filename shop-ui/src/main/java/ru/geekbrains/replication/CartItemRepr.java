package ru.geekbrains.replication;

public class CartItemRepr {

    private Long productId;

    private Integer qty;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }


    public CartItemRepr() {
    }

    public CartItemRepr(Long productId, Integer qty) {
        this.productId = productId;
        this.qty = qty;
    }
}
