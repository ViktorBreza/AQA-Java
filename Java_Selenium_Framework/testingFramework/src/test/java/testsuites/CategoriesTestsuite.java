package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.CreateNewCategoryAndCheckUIAndDB;


@SelectClasses({CreateNewCategoryAndCheckUIAndDB.class})

@Suite
@SuiteDisplayName("A Categories Test Suite")
public class CategoriesTestsuite {
}
