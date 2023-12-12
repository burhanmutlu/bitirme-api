package com.burhanmutlu.ws.logins;

import com.burhanmutlu.ws.company.Company;
import com.burhanmutlu.ws.shared.BaseEntity;
import com.burhanmutlu.ws.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "logins")
public class Logins extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company companyId;
}
