package helpfiles;

import database.DataBase;
import org.apache.commons.text.RandomStringGenerator;
import pageobjects.ContactsPage;
import pageobjects.CreateContactPage;
import utils.MyDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactHelper {

    private CreateContactPage createContactPage;
    private ContactsPage contactsPage;

    public ContactHelper(MyDriver myDriver) {
        contactsPage = new ContactsPage(myDriver);
        createContactPage = new CreateContactPage(myDriver);
    }

    public void createNewContact(Map<String, String> contactData){
        contactsPage.openNewContactPage();
        fillNewContactForm(contactData);
        submitContactForm();
    }

    public void editExistedContact(String newFirstName){
        contactsPage.clickOnEditContactButton();
        createContactPage.enterFirstName(newFirstName);
        submitContactForm();
    }

    public Map<String, Object> retrieveDataFromDB(String name) {
        DataBase db = DataBase.getInstance();

        String ticketDataQuery = "SELECT first_name AS firstName, last_name AS lastName, login, email" +
                " FROM contact " +
                "JOIN email ON contact.first_name = email.person_id " +
                "WHERE first_name = '" + name + "'";
        List<Map<String, Object>> result = db.executeQueryWithResultList(ticketDataQuery);

        if (result.isEmpty()) {
            db.disconnect();
            throw new RuntimeException("No data found in the database for first_name: " + name);
        }

        Map<String, Object> ticketDBData = new HashMap<>();
        ticketDBData.putAll(result.get(0));
        db.disconnect();
        return ticketDBData;
    }

    public void fillNewContactForm(Map<String, String> contactData) {
        String firstName = contactData.get("firstName");
        String lastName = contactData.get("lastName");
        String email = contactData.get("email");
        String login = contactData.get("login");

        createContactPage.enterFirstName(firstName);
        createContactPage.enterLastName(lastName);
        createContactPage.enterEmail(email);
        createContactPage.enterLogin(login);
    }

    public void submitContactForm() {
        createContactPage.clickOnSubmitButton();
    }

    public Map<String, String> generateRandomContactData() {
        String firstName = generateRandomName(3, 5);
        String lastName = generateRandomName(2, 5);//Last name must be at least 2 characters long.
        String email = generateRandomEmail();
        String login = generateRandomName(4, 8);

        Map<String, String> contactData = new HashMap<>();
        contactData.put("firstName", firstName);
        contactData.put("lastName", lastName);
        contactData.put("email", email);
        contactData.put("login", login);

        return contactData;
    }

    public void checkInvalidEmailErrorMessage() {
        assertTrue(createContactPage.invalidEmailError.isDisplayed(), "Expected error message for invalid email, but it was not displayed.");

    }

    public void checkEmptyEmailErrorMessage() {
        assertTrue(createContactPage.emptyEmailError.isDisplayed(), "Expected error message for empty email, but it was not displayed.");

    }

    public void checkInvalidPrefixErrorMessage() {
        assertTrue(createContactPage.invalidTicketPrefixError.isDisplayed(), "Expected error message for invalid prefix, but it was not displayed.");

    }

    public void checkEmptyLoginMessage() {
        assertTrue(createContactPage.emptyLoginError.isDisplayed(), "Expected error message for empty login, but it was not displayed.");

    }

    public void checkInvalidLastNameErrorMessage() {
        assertTrue(createContactPage.invalidLastNameError.isDisplayed(), "Expected error message for invalid last name, but it was not displayed.");

    }

    private String generateRandomName(int minLength, int maxLength) {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .filteredBy(Character::isLetter)
                .build();

        int length = new Random().nextInt(maxLength - minLength + 1) + minLength;

        return randomStringGenerator.generate(length);
    }

    private String generateRandomEmail() {
        String randomName = generateRandomName(5, 8);
        String randomDomain = generateRandomName(4, 6) + ".com";

        return randomName + "@" + randomDomain;
    }

    private boolean isValidEmail(String email) {
        // Regular expression pattern for email validation
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

}
