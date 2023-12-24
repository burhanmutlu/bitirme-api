package com.burhanmutlu.ws.data;

import com.burhanmutlu.ws.company.dto.req.CompanyRequest;
import com.burhanmutlu.ws.company.dto.resp.CompanyResponse;
import com.burhanmutlu.ws.user.dto.req.RegistrationRequest;
import com.burhanmutlu.ws.company.CompanyService;
import com.burhanmutlu.ws.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

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
       // List<CompanyResponse> val =  companyService.getAllCompaniesByUserId(1L);
        //if(val == null) {
            createUser();
            createCompany("Instagram", "logo", "https://instagram.com");
            createCompany("Facebook", "logo", "https://facebook.com");
            createCompany("LinkedIn", "logo", "https://linkedin.com");
            createCompany("GitHub", "logo", "https://github.com");
            createCompany("Google", "logo", "https://google.com");
            createCompany("Twitter", "logo", "https://twitter.com");
       // }
    }

    public void createUser() throws MessagingException, IOException {
        RegistrationRequest registrationRequest = RegistrationRequest.builder()
                .name("test")
                .surname("test")
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
