package ru.geekbrains.model;

;

public class MessageModel {
    private String recipientAddress;
    private String subject;
    private  String text;

    /**
     * @param recipientAddress string email
     * @param subject email
     * @param text email
     */
    public MessageModel(String recipientAddress, String subject, String text) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.text = text;
    }

    /**
     * @param recipientAddress string email
     */
    public MessageModel(String recipientAddress) {
        this(recipientAddress, "Test", "test");
    }

    public MessageModel() {
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
