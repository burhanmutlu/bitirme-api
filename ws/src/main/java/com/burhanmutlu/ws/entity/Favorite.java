package com.burhanmutlu.ws.entity;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite extends BaseEntity {
    //Concidanteyi  column kullanılabilir ama bence saçma

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne // her dosya sadece bir kullanıcıya özel o yüzden böyle.
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "file_id", nullable = false)
    private File fileId;

    @ManyToOne
    @JoinColumn(name = "logins_id", nullable = false)
    private Logins loginsId;

}
