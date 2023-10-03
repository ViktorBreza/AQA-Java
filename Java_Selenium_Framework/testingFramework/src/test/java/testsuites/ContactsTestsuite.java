package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.CreateNewContactNegativeTest;
import tests.DeleteContactTest;
import tests.EditContactTest;


@SelectClasses({CreateNewContactNegativeTest.class, EditContactTest.class, DeleteContactTest.class})

@Suite
@SuiteDisplayName("Contacts Test Suite")
public class ContactsTestsuite {
}
