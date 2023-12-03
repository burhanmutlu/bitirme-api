package com.burhanmutlu.ws.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "favorites")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "favorite_type", discriminatorType = DiscriminatorType.STRING)
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne // her dosya sadece bir kullanıcıya özel o yüzden böyle.
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

}
