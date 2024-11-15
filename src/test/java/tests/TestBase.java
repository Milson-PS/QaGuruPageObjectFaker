package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import pages.RegistrationPage;
import utils.RandomUtils;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TestBase {
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH);
    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();

    @BeforeAll
    static void BeforeALL() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";

    }


}
