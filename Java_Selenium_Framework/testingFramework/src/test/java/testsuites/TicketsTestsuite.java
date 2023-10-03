package testsuites;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.CreateNewInnerTicketAndCompareUIWithBDTest;
import tests.CreateNewTicketAndCompareUIWithBDTest;
import tests.EditTicketTest;
import tests.TicketsTableValuesTest;


@Suite
@DisplayName("Tickets Test Suite")
@SelectClasses({CreateNewTicketAndCompareUIWithBDTest.class, CreateNewInnerTicketAndCompareUIWithBDTest.class, TicketsTableValuesTest.class, EditTicketTest.class})
public class TicketsTestsuite {

}
