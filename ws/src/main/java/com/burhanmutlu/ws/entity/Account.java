package com.burhanmutlu.ws.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "number_of_open_sessions")
    private int numberOfOpenSessions;

}
