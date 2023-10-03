package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.DashboardPageValuesTest;


@SelectClasses({DashboardPageValuesTest.class})

@Suite
@SuiteDisplayName("Dashboard Test Suite")
public class DashboardTestsuite {
}