package testsuites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import tests.CreateNewDeviceTest;


@SelectClasses({CreateNewDeviceTest.class})

@Suite
@SuiteDisplayName("Devices Test Suite")
public class DevicesTestsuite {
}
