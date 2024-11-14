package tests;

import org.junit.jupiter.api.Test;
import utils.RandomUtils;


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
            hobbies = random.Hobbies(),
            picName = "bag.png",
            currentAddress = random.CurrentAddress(),
            state = random.State(),
            city = random.City(state);

    @Test
    void fillFormTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .setSubjects(subjects)
                .setHobbies(hobbies)
                .setPicture(picName)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .Submit();

        registrationPage
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbies)
                .checkResult("Picture", picName)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    void minimumAmountDataTest() {
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

    @Test
    void incorrectPhoneNumberTest() {
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .Submit();

        registrationPage.negativeCheck();

    }
}










