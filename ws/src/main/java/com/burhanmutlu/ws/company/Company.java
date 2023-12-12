package com.burhanmutlu.ws.company;

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
@Table(name = "companies")
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "company_name", unique = true)
    private String companyName;

    @Column(name = "company_logo")
    private String companyLogo;

    @Column(name = "company_web_page", unique = true)
    private String companyWebPage;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

}
