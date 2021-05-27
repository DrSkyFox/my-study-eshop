package ru.geekbrains.persist.model.order;

public enum StatusOrder {

    PREORDER("preorder", 1),
    CHECKWAITING("wait check", 2),
    CHECKED("checked", 3),
    REGISTRAION("registration", 4),
    SENDED("SENDED", 5),
    DELIVERING("delivering", 6),
    DELIVERED("delivered", 7),
    RETURNED("returned", 8),
    CANCELED("canceled", 9);


    private String status;
    private Integer id;


    StatusOrder(String status, Integer id) {
        this.status = status;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

;    public Integer getId() {
        return id;
    }


}
