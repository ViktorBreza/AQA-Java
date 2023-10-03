package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.CreateNewManagerAndCompareUIWithBDTest;


@SelectClasses({CreateNewManagerAndCompareUIWithBDTest.class})

@Suite
@SuiteDisplayName("Managers Test Suite")
public class ManagersTestsuite {
}
