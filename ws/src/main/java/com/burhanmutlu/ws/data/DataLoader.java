package com.burhanmutlu.ws.data;

import com.burhanmutlu.ws.dto.req.CompanyRequest;
import com.burhanmutlu.ws.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.service.CompanyService;
import com.burhanmutlu.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    private final CompanyService companyService;

    @Autowired
    public DataLoader(UserService userService, CompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
    }

    @Override
    public void run(String... args) throws Exception {
        createUser();
        createCompany("Instagram", "logo", "https://instagram.com");
        createCompany("Facebook", "logo", "https://facebook.com");
        createCompany("LinkedIn", "logo", "https://linkedin.com");
        createCompany("GitHub", "logo", "https://github.com");
        createCompany("Google", "logo", "https://google.com");
        createCompany("Twitter", "logo", "https://twitter.com");
    }

    public void createUser() {
        RegistrationRequest registrationRequest = RegistrationRequest.builder()
                .name("burhack")
                .surname("mutlu")
                .email("burhackmutlu@gmail.com")
                .password("1234")
                .phoneNumber("05459606598")
                .build();
        userService.createUser(registrationRequest);
    }

    public void createCompany(String cN, String cL, String cW) {
        CompanyRequest companyRequest = CompanyRequest.builder()
                .companyName(cN)
                .companyLogo(cL)
                .companyWebPage(cW)
                .build();
        companyService.addCompanyByUserId(companyRequest, Integer.toUnsignedLong(1));
    }

}
