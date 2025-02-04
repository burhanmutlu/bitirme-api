package com.burhanmutlu.ws.company;

import com.burhanmutlu.ws.logins.Logins;
import com.burhanmutlu.ws.shared.BaseEntity;
import com.burhanmutlu.ws.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "companies")
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_logo")
    private String companyLogo;

    @Column(name = "company_web_page")
    private String companyWebPage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @OneToMany(mappedBy = "companyId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Logins> logins;

}
