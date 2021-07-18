package com.example.demo.model;

import com.example.demo.helper.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "order1")
public class Order {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private User user;
//    @Column(name = "client")
//    private User client;
    @Column (name = "dateTime")
    private LocalDateTime dateTime;

    @Column (name = "status")
    private Status status;





    public Order(){
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

//    public User getClient() {
//        return client;
//    }
//
//    public void setClient(User client) {
//        this.client = client;
//    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
