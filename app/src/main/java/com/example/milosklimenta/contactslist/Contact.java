package com.example.milosklimenta.contactslist;

import java.io.Serializable;

public class Contact implements Serializable {

    private String contactImage, contactName, ContactNumber;
    private String id;

    public Contact(String contactImage, String contactName, String contactNumber, String id) {
        this.contactImage = contactImage;
        this.contactName = contactName;
        this.id = id;
        ContactNumber = contactNumber;
    }

    public String getContactImage() {
        return contactImage;
    }

    public void setContactImage(String contactImage) {
        this.contactImage = contactImage;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
