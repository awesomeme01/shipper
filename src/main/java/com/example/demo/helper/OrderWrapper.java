package com.example.demo.helper;

public class OrderWrapper {
    private String trackNumber;
    private Double priceFromInvoice;
    private String description;

    public OrderWrapper() {
    }

    public OrderWrapper(String trackNumber, Double priceFromInvoice, String description) {
        this.trackNumber = trackNumber;
        this.priceFromInvoice = priceFromInvoice;
        this.description = description;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public Double getPriceFromInvoice() {
        return priceFromInvoice;
    }

    public String getDescription() {
        return description;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setPriceFromInvoice(Double priceFromInvoice) {
        this.priceFromInvoice = priceFromInvoice;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}