package com.lpdm.msstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Kybox
 * @version 1.0
 * @since 01/12/2018
 */

public class OrderedProduct {

    @JsonIgnore
    private Order oder;
    private Product product;
    private int quantity;
    private double price;

    public OrderedProduct() {
    }

    public Order getOder() {
        return oder;
    }

    public void setOder(Order oder) {
        this.oder = oder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
