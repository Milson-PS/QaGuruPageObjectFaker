package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.RandomUtils;

@Tag("regress")
public class RegistrationFillFormTest extends TestBase {

    private final RandomUtils random = new RandomUtils();

    String
            firstName = random.FirstName(),
            lastName = random.LastName(),
            userEmail = random.UserEmail(),
            gender = random.Gender(),
            phoneNumber = random.PhoneNumber(),
            dayOfBirth = random.DayOfBirth(),
            monthOfBirth = random.MonthOfBirth(),
            yearOfBirth = random.YearOfBirth(),
            subjects = random.Subjects(),
            hobbies = random.Hobbies();

    @CsvSource(value = {
            "lastName, Male",
            "lastName, Female",
            "lastName, Other"
    })
    @ParameterizedTest(name = "Ввод части данных на странице с разным полом {1}")
    @Tag("WEB")
    @Tag("ParameterizedTest")
    void genderTest(String lastName, String gender) {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .Submit();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies);
    }

    @ValueSource(strings = {
            "....1",
            "Текст"
    })
    @ParameterizedTest(name = "Параметризованный тест с вводом данных {0} в поле с некорректными значениями")
    @Tag("WEB")
    @Tag("ParameterizedTest")
    void phoneNumberTest(String paraNumber) {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber(paraNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .Submit();

        registrationPage.negativeCheck();
    }

    @CsvFileSource(resources = "testData/RegistrationFillFormTest.csv")
    @ParameterizedTest(name = "Ввод хобби {1} в зависимости от имени {0}")
    @Tag("WEB")
    @Tag("ParameterizedTest")
    void hobbiesTest(String firstName, String hobbies) {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .Submit();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies);
    }
}