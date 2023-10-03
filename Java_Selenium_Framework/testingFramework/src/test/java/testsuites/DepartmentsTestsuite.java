package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.CreateNewDepartmentTest;


@SelectClasses({CreateNewDepartmentTest.class})

@Suite
@SuiteDisplayName("Departments Test Suite")
public class DepartmentsTestsuite {
}
