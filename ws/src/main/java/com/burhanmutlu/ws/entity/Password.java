package com.burhanmutlu.ws.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "passwords")
public class Password extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "website_url")
    private String websiteUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

}
