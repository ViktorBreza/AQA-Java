package helpfiles;

import database.DataBase;
import org.apache.commons.text.RandomStringGenerator;
import pageobjects.CreateTicketPage;
import pageobjects.EditTicketPage;
import pageobjects.TicketsPage;
import utils.MyDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TicketHelper {

    private TicketsPage ticketsPage;
    private CreateTicketPage createTicketPage;
    private final MyDriver myDriver;
    private final EditTicketPage editTicketPage;


    public TicketHelper(MyDriver myDriver) {
        this.myDriver = myDriver;
        ticketsPage = new TicketsPage(myDriver);
        createTicketPage = new CreateTicketPage(myDriver);
        editTicketPage = new EditTicketPage(myDriver);
    }

    public String generateRandomTicketTitle() {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z').filteredBy(Character::isLetterOrDigit).build();

        int length = new Random().nextInt(6) + 3; // Generate a random length between 3 and 8

        return randomStringGenerator.generate(length);
    }

    public void createNewInnerTicket(String ticketTitle, MyDriver myDriver) {
        ticketsPage = new TicketsPage(myDriver);
        createTicketPage = new CreateTicketPage(myDriver);

        ticketsPage.clickOnNewTicketButton();
        createTicketPage.clickOnNewInnerTicketButton();

        createTicketPage.enterTitle(ticketTitle).clickOnSubmitButton();
    }

    public void createNewTicket(String ticketTitle) {
        ticketsPage.clickOnNewTicketButton();

        createTicketPage.enterTitle(ticketTitle).selectContactByVisibleText("Татьяна Алимова").clickOnSubmitButton();
    }

    public void editTicketTitle(String editedTicketTitle) throws InterruptedException {
        ticketsPage.clickOnEditButton();
        editTicketPage.enterEditedTicketTitle(editedTicketTitle);
        editTicketPage.clickOnEditedSubmitButton();
    }

    public Map<String, String> retrieveInnerTicketDataFromUI(MyDriver myDriver) {
        ticketsPage = new TicketsPage(myDriver);
        return ticketsPage.getFirstInnerTicketData();
    }

    public Map<String, String> retrieveDataFromUI() {
        return ticketsPage.getFirstTicketData();
    }

    public Map<String, Object> retrieveDataFromDB(String ticketTitle) {
        DataBase db = DataBase.getInstance();

        String ticketDataQuery = "SELECT t.title, stage_aud.name AS stage, category.name AS category "
                + "FROM ticket AS t " + "JOIN stage_aud ON t.stage_id = stage_aud.id "
                + "JOIN category ON t.category_id = category.id " + "WHERE t.title = '" + ticketTitle + "'";
        List<Map<String, Object>> result = db.executeQueryWithResultList(ticketDataQuery);
        Map<String, Object> ticketDBData = new HashMap<>();
        if (!result.isEmpty()) {
            ticketDBData.putAll(result.get(0));
        }
        return ticketDBData;
    }

    public Map<String, Object> retrieveEditedDataFromDB(String editedTicketTitle) {
        DataBase db = DataBase.getInstance();
        db.connect();

        String ticketDataQuery = "SELECT t.title, stage_aud.name AS stage, category.name AS category "
                + "FROM ticket AS t " + "JOIN stage_aud ON t.stage_id = stage_aud.id "
                + "JOIN category ON t.category_id = category.id " + "WHERE t.title = '" + editedTicketTitle + "'";
        List<Map<String, Object>> result = db.executeQueryWithResultList(ticketDataQuery);
        Map<String, Object> editedTicketDBData = new HashMap<>();
        if (!result.isEmpty()) {
            editedTicketDBData.putAll(result.get(0));
        } else {
            System.out.println("No data found for edited ticket: " + editedTicketTitle);
        }
        return editedTicketDBData;
    }


    public Map<String, String> convertMapToLowerCase(Map<String, ?> map) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey().toLowerCase();
            String value = entry.getValue() != null ? entry.getValue().toString().toLowerCase() : null;
            result.put(key, value);
        }
        return result;
    }

}
