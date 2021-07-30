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
    @JoinColumn(name = "user_id")
    private User user;

    @Column (name = "dateTime")
    private LocalDateTime dateTime;

    @Column (name = "status")
    private Status status;

    @Column (name = "track_number")
    private String trackNumber;

    @Column (name = "volume")
    private Double volume;

    @Column (name = "volumeUnit")
    private VolumeUnit volumeUnit;

    @Column (name = "total")
    private Double total;



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
}
