package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.AddNewCompanyTest;


@SelectClasses({AddNewCompanyTest.class})

@Suite
@SuiteDisplayName("Companies Test Suite")
public class CompaniesTestsuite {
}
