package com.example.demo.model;

import com.example.demo.helper.Status;
import com.example.demo.helper.VolumeUnit;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "order_1")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "user_id")
    private User user;

    @Column (name = "date_time")
    private LocalDateTime dateTime;

    @Column (name = "status")
    private Status status;

    @Column (name = "track_number")
    private String trackNumber;

    @Column (name = "volume")
    private Double volume;

    @Column (name = "volume_unit")
    private VolumeUnit volumeUnit;

    @Column (name = "total")
    private Double total;

    @Column (name = "price_from_invoice")
    private Double priceFromInvoice;

    @Column(name = "description")
    private String description;

    public Order(){
        status = Status.AWAITING_CONFIRMATION;
        dateTime = LocalDateTime.now().plusHours(6);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public VolumeUnit getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(VolumeUnit volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPriceFromInvoice() {
        return priceFromInvoice;
    }

    public void setPriceFromInvoice(Double priceFromInvoice) {
        this.priceFromInvoice = priceFromInvoice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
