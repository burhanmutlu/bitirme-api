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
        List<CompanyResponse> val =  companyService.getAllCompaniesByUserId(1L);
        if(val == null) {
            createUser();
            createCompany("Google", "https://static-00.iconduck.com/assets.00/google-icon-2048x2048-czn3g8x8.png", "https://google.com");
            createCompany("Instagram", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Instagram_icon.png/600px-Instagram_icon.png", "https://instagram.com");
            createCompany("Facebook", "https://cdn-icons-png.flaticon.com/256/124/124010.png", "https://facebook.com");
            createCompany("X", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5a/X_icon_2.svg/2048px-X_icon_2.svg.png", "https://twitter.com");
            createCompany("LinkedIn", "https://cdn1.iconfinder.com/data/icons/logotypes/32/circle-linkedin-512.png", "https://linkedin.com");
            createCompany("GitHub", "https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png", "https://github.com");
            createCompany("Telegram", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Telegram_logo.svg/2048px-Telegram_logo.svg.png", "https://telegram.org");
            createCompany("Twitch", "https://cdn-icons-png.flaticon.com/512/5968/5968819.png", "https://twitch.com");
            createCompany("Discord", "https://static-00.iconduck.com/assets.00/discord-icon-2048x2048-o5mluhz2.png", "https://discord.com");
            createCompany("Spotify", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/84/Spotify_icon.svg/1982px-Spotify_icon.svg.png", "https://spotify.com");
            createCompany("Tiktok", "https://cdn4.iconfinder.com/data/icons/social-media-flat-7/64/Social-media_Tiktok-512.png", "https://tiktok.com");
            createCompany("Pinterest", "https://cdn-icons-png.flaticon.com/512/174/174863.png", "https://pinterest.com");
            createCompany("Snapchat", "https://play-lh.googleusercontent.com/KxeSAjPTKliCErbivNiXrd6cTwfbqUJcbSRPe_IBVK_YmwckfMRS1VIHz-5cgT09yMo", "https://snapchat.com");
            createCompany("Skype", "https://static-00.iconduck.com/assets.00/skype-icon-512x511-wzcq22vz.png", "https://skype.com");
            createCompany("Getir", "https://cdn.getir.com/marketing/Getir_Logo_1621812382342.png", "https://getir.com");
            createCompany("Dolap", "https://play-lh.googleusercontent.com/_KaK87fJtYRNE6wdZq8DjBJqkv9iYXF_SpNeI-3L1yRiNlugOfGKZYJJFZUkR6B1L9o", "https://dolap.com");
            createCompany("Obilet", "https://logowik.com/content/uploads/images/obiletcom6498.jpg", "https://obilet.com");
            createCompany("Mhrs", "https://play-lh.googleusercontent.com/IoKK6j7-Li_ssHuZsD8K3io0cnj_6LbkCE7WCe0oxczulSK8xjMSDpOjqwBSZMt0VSI", "https://mhrs.com");
            createCompany("E-nabız", "https://play-lh.googleusercontent.com/sywoLpNmf1Ekyb3wY4Cxoc87cPkpOrFQqbhmJ0W7eGq_QIe3MsX_6GIO1WOsC4Z1SQ", "https://twitch.com");
            createCompany("Trendyol", "https://play-lh.googleusercontent.com/6Z4D_Qb1s6ZxNIp4hSi38ATABo_df4gl0WX-1bCxaFoj8sOH0ExcrQa3naLkP0_Rp-Id", "https://trendyol.com");
            createCompany("Yemeksepeti", "https://seeklogo.com/images/Y/yemek-sepeti-logo-14A9709947-seeklogo.com.png", "https://yemeksepeti.com");
            createCompany("Tv+", "https://logowik.com/content/uploads/images/turkcell-tv1559.jpg", "https://tvplus.com");
            createCompany("Fizy", "https://play-lh.googleusercontent.com/82cBRmwDq5SlIiqcyHhle9m55-zJjGKPvDE-JSyTaDhKqDDjLH5uzpXfQmQTxQsMPzs", "https://fizy.com");
            createCompany("Youtube", "https://cdn-icons-png.flaticon.com/256/1384/1384060.png", "https://youtube.com");
            createCompany("Vk", "https://cdn-icons-png.flaticon.com/512/3938/3938067.png", "https://vk.com");
            createCompany("Outlook", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Outlook.com_icon_%282012-2019%29.svg/2028px-Outlook.com_icon_%282012-2019%29.svg.png", "https://outlook.com");
            createCompany("Icloud", "https://upload.wikimedia.org/wikipedia/tr/6/68/Icloud_logo.png", "https://icloud.com");
            createCompany("Chatgpt", "data:image/png;base64,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", "https://chat.openapi.com");
            createCompany("Udemy", "data:image/png;base64,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", "https://udemy.com");
        }
    }

    public void createUser() throws MessagingException, IOException {
        RegistrationRequest registrationRequest = RegistrationRequest.builder()
                .name("test")
                .surname("test")
                .email("burhackmutlu@gmail.com")
                .password("1234")
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
