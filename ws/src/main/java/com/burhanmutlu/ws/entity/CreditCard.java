package com.burhanmutlu.ws.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "credit_cards")
public class CreditCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "card_expiration_date")
    private String expirationDate;

    @Column(name = "cvv")
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User userId;

}
