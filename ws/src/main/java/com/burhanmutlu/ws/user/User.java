package com.burhanmutlu.ws.user;

import javax.persistence.*;

import com.burhanmutlu.ws.company.Company;
import com.burhanmutlu.ws.creditcard.CreditCard;
import com.burhanmutlu.ws.favorite.Favorite;
import com.burhanmutlu.ws.file.File;
import com.burhanmutlu.ws.logins.Logins;
import com.burhanmutlu.ws.shared.BaseEntity;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "surname", length = 30)
    private String surname;

    @Column(name = "email", unique = true, nullable = false, length = 60)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    private boolean isActive;

    private String activationToken;

    private String passwordResetToken;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> company;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> file;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CreditCard> creditCard;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Logins> logins;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorite;

    @PrePersist
    @PreUpdate
    private void beforeSaveOrUpdate() {
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
        surname = Character.toUpperCase(surname.charAt(0)) + surname.substring(1).toLowerCase();
    }
}
