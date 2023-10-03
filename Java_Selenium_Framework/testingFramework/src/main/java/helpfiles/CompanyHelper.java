package helpfiles;

import database.DataBase;
import org.apache.commons.text.RandomStringGenerator;
import pageobjects.CompaniesPage;
import pageobjects.CreateCompanyPage;
import utils.MyDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CompanyHelper {

    private CompaniesPage companiesPage;
    private CreateCompanyPage createCompanyPage;
    private Map<String, String> companyData;

    public CompanyHelper(MyDriver myDriver) {
        companiesPage = new CompaniesPage(myDriver);
        createCompanyPage = new CreateCompanyPage(myDriver);
    }

    public String generateRandomCompanyName() {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(Character::isLetterOrDigit)
                .build();

        int length = new Random().nextInt(6) + 3; // Generate a random length between 3 and 8

        String companyName = randomStringGenerator.generate(length);
        return companyName;
    }

    public void createNewCompany(String companyName) {
        companyData = new HashMap<>();
        companiesPage.openCreateCompanyPage();
        createCompanyPage.enterCompanyName(companyName);
        createCompanyPage.clickOnSubmitButton();
        companyData.put("companyName", companyName);
    }

    public Map<String, String> retrieveDataFromUI(String companyName) {
        companiesPage.searchCompanyTitleInputField(companyName);
        companiesPage.clickOnFilterButton();
        companiesPage.clickOnCompanyTitle(companyName);
        return companiesPage.createCompanyTableData();
    }


    public Map<String, String> retrieveDataFromDB(String companyName) {
        DataBase db = DataBase.getInstance();
        db.connect();

        String query = "SELECT name, ticket_prefix, edrpou, plan, phone, skype, website, email, country, " +
                "city, street, building, zipcode, room_number, creation_timestamp, update_timestamp FROM company WHERE name = '" + companyName + "'";
        List<Map<String, Object>> result = db.executeQueryWithResultList(query);

        return result.isEmpty() ? null : convertMapToStringValues(result.get(0));
    }

    // Helper method to convert map values to String type
    public Map<String, String> convertMapToStringValues(Map<String, Object> map) {
        Map<String, String> convertedMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String stringValue = value != null ? value.toString() : "";
            convertedMap.put(key, stringValue);
        }

        return convertedMap;
    }

}
